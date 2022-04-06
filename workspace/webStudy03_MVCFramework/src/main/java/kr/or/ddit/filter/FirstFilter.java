package kr.or.ddit.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;


/**
 * Filter : 요청과 응답에 대한 전후처리를 담당하는 재사용(singleton) 가능한 객체.
 * 
 * lifecycle callback : init, destroy
 * request(filtering) callback : doFilter
 */
@Slf4j
public class FirstFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("{} 초기화되었음.", this.getClass().getName());
	}

	@Override
	public void doFilter(ServletRequest request
			, ServletResponse response
			, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		log.info("요청 [{}] 필터링", req.getRequestURI());
		
		chain.doFilter(request, response);
		
		log.info("응답 [{}] 필터링", req.getRequestURL());
	}

	@Override
	public void destroy() {
		log.info("{} 소멸.", this.getClass().getName());
	}

}
