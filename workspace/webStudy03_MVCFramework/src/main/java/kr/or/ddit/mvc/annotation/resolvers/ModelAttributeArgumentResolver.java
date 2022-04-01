package kr.or.ddit.mvc.annotation.resolvers;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.ClassUtils;

public class ModelAttributeArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean isSupported(Parameter parameter) {
		ModelAttribute annotation = parameter.getAnnotation(ModelAttribute.class);
		Class<?> parameterType = parameter.getType();
		
		boolean supported = annotation != null;
		if(supported) {
			supported = !(
							String.class.equals(parameterType)
							|| ClassUtils.isPrimitiveOrWrapper(parameterType)
							|| (
								parameterType.isArray() && ClassUtils.isPrimitiveOrWrapper(parameterType.getComponentType())	
							)
						);
		}
		return supported;
	}

	@Override
	public Object argumentResolve(Parameter parameter, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		ModelAttribute annotation = parameter.getAnnotation(ModelAttribute.class);
//		ProdVO prod = new ProdVO(); // command object, model 
		Class<?> parameterType = parameter.getType();
		try {
			Object argumentValue = parameterType.newInstance();
	//		req.setAttribute("prod", prod);
			req.setAttribute(annotation.value(), argumentValue);
			BeanUtils.populate(argumentValue, req.getParameterMap());
			return argumentValue;
		}catch (Exception e) {
			throw new ServletException(e);
		}
	}

}











