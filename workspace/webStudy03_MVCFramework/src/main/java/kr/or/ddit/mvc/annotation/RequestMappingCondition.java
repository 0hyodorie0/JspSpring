package kr.or.ddit.mvc.annotation;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode
@Getter
@ToString
public class RequestMappingCondition {
	private String url;
	private RequestMethod method;
	
	public RequestMappingCondition(String url, RequestMethod method) {
		super();
		this.url = url;
		this.method = method;
	}
	
	
}
