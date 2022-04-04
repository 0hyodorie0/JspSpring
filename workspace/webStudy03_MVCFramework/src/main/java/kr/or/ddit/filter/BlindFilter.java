package kr.or.ddit.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import lombok.extern.slf4j.Slf4j;
/**
 * 블랙리스트 기반의 차단 필터
 * @author PC-10
 *
 */
@Slf4j
public class BlindFilter implements Filter {
	Map<String, String> blackList;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("{} 초기화.", this.getClass().getName());
		blackList = new HashMap<>();
		blackList.put("127.0.0.1", "IPv4 방식의 나니까 차단.");
		blackList.put("0:0:0:0:0:0:0:1", "IPv6 방식의 나니까 차단.");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String clientIP = request.getRemoteAddr();
		boolean block = blackList.containsKey(clientIP);
		if(block) {
			String reason = blackList.get(clientIP);
			String view = "/WEB-INF/views/messageView.jsp";
			request.getRequestDispatcher(view).forward(request, response);
		}else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
		log.info("{} 소멸.", this.getClass().getName());
		
	}

}
