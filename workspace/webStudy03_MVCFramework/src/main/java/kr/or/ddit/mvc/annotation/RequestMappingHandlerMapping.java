package kr.or.ddit.mvc.annotation;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.utils.ReflectionUtils;

/**
 * 1.핸들러와 관련된 어노테이션 (Controller, RequestMapping) 트레이싱 ==> handlerMap 형성.
 * 2.수집된 정보를 기반으로 특정 요청을 처리할 수 있는 핸들러를 검색.
 *
 */
public class RequestMappingHandlerMapping implements HandlerMapping {

	private Map<RequestMappingCondition, RequestMappingInfo> handlerMap;
	
	
	public RequestMappingHandlerMapping(String...basePackages) {
		handlerMap = new LinkedHashMap<>();
		Map<Class<?>, Controller> handlerClasses = ReflectionUtils.getClassesWithAnnotationAtBasePackages(Controller.class, basePackages);
		for(Class<?> eachHandlerClass : handlerClasses.keySet()) {
			try {
				Object commandHandler = eachHandlerClass.newInstance();
				
				 Map<Method, RequestMapping> handlerMethods = ReflectionUtils.getMethodsWithAnnotationAtClass(eachHandlerClass, RequestMapping.class
						 										, String.class, HttpServletRequest.class, HttpServletResponse.class);
				for( Entry<Method, RequestMapping> entry : handlerMethods.entrySet()) {
					Method handlerMethod = entry.getKey();
					RequestMapping mappingAnnotation = entry.getValue();
					String url = mappingAnnotation.value();
					RequestMethod method = mappingAnnotation.method();
					RequestMappingCondition mappingCondition = new RequestMappingCondition(url, method);
					RequestMappingInfo mappingInfo = new RequestMappingInfo(mappingCondition, commandHandler, handlerMethod);
					handlerMap.put(mappingCondition, mappingInfo);
					System.out.println(mappingInfo);
				}
				
			}catch (Exception e) {
				System.err.printf("%s 객체 생성 및 핸들러 호출 과정에서 예외 발생, %s\n", eachHandlerClass.getName(), e.getMessage());
				continue;
			}
		}
	}


	@Override
	public RequestMappingInfo findCommandHandler(HttpServletRequest req) {
		String uri = req.getRequestURI();
		String cPath = req.getContextPath();
		uri = uri.substring(cPath.length());
		uri = uri.split(";")[0];
		String methodStr = req.getMethod();
		RequestMethod method = RequestMethod.valueOf(methodStr);
		RequestMappingCondition mappingCondition = new RequestMappingCondition(uri, method);
		RequestMappingInfo mappingInfo = handlerMap.get(mappingCondition);
		return mappingInfo;
	}

}
