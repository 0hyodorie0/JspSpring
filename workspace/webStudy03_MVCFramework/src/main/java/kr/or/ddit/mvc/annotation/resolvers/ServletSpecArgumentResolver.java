package kr.or.ddit.mvc.annotation.resolvers;

import java.io.IOException;
import java.lang.reflect.Parameter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * {@link HttpServletRequest}, {@link HttpServletResponse}, {@link HttpSession}
 * 
 *
 */
public class ServletSpecArgumentResolver implements HandlerMethodArgumentResolver{

	@Override
	public boolean isSupported(Parameter parameter) {
		Class<?> parameterType = parameter.getType();
		boolean supported = 
				HttpServletRequest.class.equals(parameterType)
				|| HttpServletResponse.class.equals(parameterType)
				|| HttpSession.class.equals(parameterType);
		return supported;
	}

	@Override
	public Object argumentResolve(Parameter parameter, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Class<?> parameterType = parameter.getType();
		Object argumentValue = null;
		if(HttpServletRequest.class.equals(parameterType)) {
			argumentValue = req;
		}else if(HttpServletResponse.class.equals(parameterType)) {
			argumentValue = resp;
		}else {
			argumentValue = req.getSession();
		}
		return argumentValue;
	}

}











