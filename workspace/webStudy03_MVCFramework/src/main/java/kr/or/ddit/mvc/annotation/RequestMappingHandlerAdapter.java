package kr.or.ddit.mvc.annotation;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestMappingHandlerAdapter implements HandlerAdapter {
	public String invokeHandler(RequestMappingInfo mappingInfo, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Object commandHandler = mappingInfo.getCommandHandler();
		Method handlerMethod = mappingInfo.getHandlerMethod();
//		memberListController.doGet(req, resp);
		try {
			String viewName = (String) handlerMethod.invoke(commandHandler, req, resp);
			return viewName;
		}catch(Exception e) {
			throw new ServletException(e);
		}
	}
}
