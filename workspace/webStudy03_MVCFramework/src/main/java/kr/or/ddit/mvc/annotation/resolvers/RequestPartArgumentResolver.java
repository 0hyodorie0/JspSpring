package kr.or.ddit.mvc.annotation.resolvers;

import java.io.IOException;
import java.lang.reflect.Parameter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.mvc.fileupload.MultipartFile;
import kr.or.ddit.mvc.fileupload.StandardMultipartHttpServletRequest;
import kr.or.ddit.mvc.annotation.RequestMappingHandlerAdapter.BadRequestException;;

public class RequestPartArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean isSupported(Parameter parameter) {
		RequestPart annotation = parameter.getAnnotation(RequestPart.class);
		Class<?> parameterType = parameter.getType();
		boolean supported = annotation!=null;
		if(supported) {
			supported = MultipartFile.class.equals(parameterType)
					|| (
						parameterType.isArray()
						&& parameterType.getComponentType().equals(MultipartFile.class)
					);
		}
		return supported;
	}

	@Override
	public Object argumentResolve(Parameter parameter, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if(!(req instanceof StandardMultipartHttpServletRequest)) {
			throw new BadRequestException("현재 요청은 멀티파트 요청이 아님.");
		}
		StandardMultipartHttpServletRequest wrapper = (StandardMultipartHttpServletRequest) req;
		RequestPart annotation = parameter.getAnnotation(RequestPart.class);
		Class<?> parameterType = parameter.getType();
		String partName = annotation.value();
		boolean required = annotation.required();
		
		List<MultipartFile> files = wrapper.getFiles(partName);
		Object argumentValue = null;
		if(files!=null && !files.isEmpty()) {
			if(parameterType.isArray()) {
				MultipartFile[] array = new MultipartFile[files.size()];
				argumentValue = files.toArray(array);
			}else {
				argumentValue = files.get(0);
			}
		}else if(required){
			throw new BadRequestException(partName+" 에 해당하는 파일이 업로드 되지 않았음.");
		}
		return argumentValue;
	}

}
