package kr.or.ddit.mvc.annotation;

import javax.servlet.http.HttpServletRequest;

public interface HandlerMapping {
	public RequestMappingInfo findCommandHandler(HttpServletRequest req);
}
