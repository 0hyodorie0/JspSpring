package kr.or.ddit.mvc.annotation;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.mvc.annotation.resolvers.HandlerMethodArgumentResolver;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttributeArgumentResolver;
import kr.or.ddit.mvc.annotation.resolvers.RequestParamArgumentResolver;
import kr.or.ddit.mvc.annotation.resolvers.RequestPartArgumentResolver;
import kr.or.ddit.mvc.annotation.resolvers.ServletSpecArgumentResolver;

public class RequestMappingHandlerAdapter implements HandlerAdapter {
	private List<HandlerMethodArgumentResolver> argumentResolvers;
	{
		argumentResolvers = new ArrayList<>();
		argumentResolvers.add(new ServletSpecArgumentResolver());
		argumentResolvers.add(new RequestParamArgumentResolver());
		argumentResolvers.add(new ModelAttributeArgumentResolver());
		argumentResolvers.add(new RequestPartArgumentResolver());
	}
	
	public void addHandlerMethodArgumentResolver(HandlerMethodArgumentResolver argumentResolver) {
		argumentResolvers.add(argumentResolver);
	}
	
	private HandlerMethodArgumentResolver findArgumentResolver(Parameter eachParam) {
		HandlerMethodArgumentResolver matched = null;
		for(HandlerMethodArgumentResolver eachResolver : argumentResolvers) {
			if(eachResolver.isSupported(eachParam)) {
				matched = eachResolver;
				break;
			}
		}
		return matched;
	}
	@Override
	public String invokeHandler(RequestMappingInfo mappingInfo, HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		Object commandHandler = mappingInfo.getCommandHandler();
		Method handlerMethod = mappingInfo.getHandlerMethod();
//		memberListController.doGet(req, resp);
		Parameter[] parameters = handlerMethod.getParameters();
		Object[] parameterValues = null;
		if(parameters!=null && parameters.length>0) {
			parameterValues = new Object[parameters.length];
			for(int idx=0; idx<parameters.length; idx++) {
				Parameter eachParam = parameters[idx];
				HandlerMethodArgumentResolver matchedResolver = findArgumentResolver(eachParam);
				if(matchedResolver==null) {
					String message = String.format("현재 컨트롤러[%s.%s]의 아규먼트[%s]는 처리할 수 없는 종류임."
							, commandHandler.getClass().getName()
							, handlerMethod.getName()
							, eachParam.getParameterizedType().getTypeName());
							
					throw new RuntimeException(message);
				}
				try {
					Object argumentValue = matchedResolver.argumentResolve(eachParam, req, resp);
					parameterValues[idx] = argumentValue;					
				}catch (BadRequestException e) {
					resp.sendError(400, e.getMessage());
					return null;
				}
			}
		}
		try {
			String viewName = (String) handlerMethod.invoke(commandHandler, parameterValues);
			return viewName;
		}catch(Exception e) {
			throw new ServletException(e);
		}
	}

	public static class BadRequestException extends RuntimeException{
		public BadRequestException(String message) {
			super(message);
		}
	}
}
