package kr.or.ddit.mvc.annotation.stereotype;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import kr.or.ddit.mvc.annotation.RequestMethod;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RequestMapping {
	/**
	 * 요청 매핑 주소
	 * @return
	 */
	String value();
	
	/**
	 * 요청 매핑 HTTP 메소드명
	 * @return
	 */
	RequestMethod method() default RequestMethod.GET;
}
