package kr.or.ddit.mvc.annotation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface HandlerAdapter {
	/**
	 * invoker인 Front controller 대신해서 backend controller(command handler)를 호출하는 역할.
	 * @param mappinInfo 특정 조건에 해당하는 요청을 처리할 수 있는 command handler 에 대한 정보를 가진 객체.
	 * @param request
	 * @param response
	 * @return
	 */
	 public String invokeHandler(RequestMappingInfo mappinInfo, HttpServletRequest request, HttpServletResponse response) 
			 throws ServletException,IOException;
}
