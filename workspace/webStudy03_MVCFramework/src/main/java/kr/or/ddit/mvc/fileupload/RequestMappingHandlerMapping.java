package kr.or.ddit.mvc.fileupload;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import kr.or.ddit.mvc.annotation.HandlerMapping;
import kr.or.ddit.mvc.annotation.RequestMappingCondition;
import kr.or.ddit.mvc.annotation.RequestMappingInfo;

/**
 * 1.핸들러와 관련된 어노테이션 (Controller, RequestMapping) 트레이싱 ==> handlerMap 형성.
 * 2.
 *
 */
public class RequestMappingHandlerMapping implements HandlerMapping {

	private Map<RequestMappingCondition, RequestMappingInfo> handlerMap;
	@Override
	public RequestMappingInfo findCommandHandler(HttpServletRequest req) {
		// TODO Auto-generated method stub
		return null;
	}

}
