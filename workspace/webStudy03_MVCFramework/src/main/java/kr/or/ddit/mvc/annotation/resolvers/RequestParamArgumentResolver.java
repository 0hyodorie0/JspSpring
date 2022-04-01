package kr.or.ddit.mvc.annotation.resolvers;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Parameter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.mvc.annotation.RequestMappingHandlerAdapter.BadRequestException;

public class RequestParamArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean isSupported(Parameter parameter) {
		RequestParam annotation = parameter.getAnnotation(RequestParam.class);
		Class<?> parameterType = parameter.getType();
		boolean supported = annotation != null;
		if(supported) {
			supported = String.class.equals(parameterType)
						|| ClassUtils.isPrimitiveOrWrapper(parameterType)
						|| (
							parameterType.isArray() && ClassUtils.isPrimitiveOrWrapper(parameterType.getComponentType())	
						);
		}
		return supported;
	}

	@Override
	public Object argumentResolve(Parameter parameter, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		RequestParam annotation = parameter.getAnnotation(RequestParam.class);
		Class<?> parameterType = parameter.getType();
		
		String reqParamName = annotation.value();
		boolean required = annotation.required();
		String defaultValue = annotation.defaultValue();
		
		String[] parameterValues = req.getParameterValues(reqParamName);
		
		if(required && (parameterValues==null || parameterValues.length==0)) {
			throw new BadRequestException(String.format("필수 파라미터[%s] 누락", reqParamName));
		}else if(!required && (parameterValues==null 
								||  parameterValues.length==0
								|| StringUtils.isBlank(parameterValues[0]))) {
			parameterValues = new String[1];
			parameterValues[0] = defaultValue;
		}
		
		Object argumentValue = null;
		if(parameterType.isArray()) {
//			Object[] tmpArray = new Object[parameterValues.length];
			Class<?> elementType = parameterType.getComponentType();
			argumentValue = Array.newInstance(elementType, parameterValues.length);
			for(int idx=0; idx<parameterValues.length; idx++) {
				Object singleValue = makeSignleValue(elementType, parameterValues[idx]);
				Array.set(argumentValue, idx, singleValue);
			}
		}else {
			argumentValue = makeSignleValue(parameterType, parameterValues[0]);
		}
		return argumentValue;
	}
	
	private Object makeSignleValue(Class<?> singleValueType, String singleReqParam){
		Object singleValue = null;
		if(byte.class.equals(singleValueType) || Byte.class.equals(singleValueType)) {
			singleValue = Byte.parseByte(singleReqParam);
		}else if(short.class.equals(singleValueType) || Short.class.equals(singleValueType)) {
			singleValue = Short.parseShort(singleReqParam);
		}else if(int.class.equals(singleValueType) || Integer.class.equals(singleValueType)) {
			singleValue = Integer.parseInt(singleReqParam);
		}else if(long.class.equals(singleValueType) || Long.class.equals(singleValueType)) {
			singleValue = Long.parseLong(singleReqParam);
		}else if(float.class.equals(singleValueType) || Float.class.equals(singleValueType)) {
			singleValue = Float.parseFloat(singleReqParam);
		}else if(double.class.equals(singleValueType) || Double.class.equals(singleValueType)) {
			singleValue = Double.parseDouble(singleReqParam);
		}else if(boolean.class.equals(singleValueType) || Boolean.class.equals(singleValueType)) {
			singleValue = Boolean.parseBoolean(singleReqParam);
		}else if(char.class.equals(singleValueType) || Character.class.equals(singleValueType)) {
			singleValue = singleReqParam.charAt(0);
		}else {
			singleValue = singleReqParam;
		}
		return singleValue;
	}

}














