package kr.or.ddit.mvc.annotation;

import java.lang.reflect.Method;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class RequestMappingInfo {
	private RequestMappingCondition mappingCondition;
	private Object commandHandler;
	private Method handlerMethod;
	
	
}
