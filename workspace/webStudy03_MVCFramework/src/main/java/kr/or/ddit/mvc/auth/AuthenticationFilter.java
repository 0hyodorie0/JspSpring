package kr.or.ddit.mvc.auth;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import lombok.extern.slf4j.Slf4j;
/**
 * access control
 * 보안(auth) : 인증(Authentication, 신원확인) + 인가(Authorization, 권한확인)
 * 보호 자원을 요청한 경우, 인증되었는지 여부(session scope's authMember)를 확인하기 위한 필터.
 */
@Slf4j
public class AuthenticationFilter implements Filter{
	public static final String SECUREDRESOURCESNAME ="securedResources";
	private Map<String, String[]> securedResources;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		securedResources = new LinkedHashMap<>();
		filterConfig.getServletContext().setAttribute(SECUREDRESOURCESNAME, securedResources);
//		1. resourcePath 파라미터 확보
		String resourcePath = filterConfig.getInitParameter("resourcePath");
		if(StringUtils.isBlank(resourcePath)) return;
		
//		2. class path resource 로딩->IO(read)
		try(
				InputStream is = AuthenticationFilter.class.getResourceAsStream(resourcePath);
			){
//		3. securedResource 맵완성
			Properties props = new Properties();
			props.load(is);
			for( Entry<Object, Object> entry : props.entrySet()) {
				String securedURL = entry.getKey().toString();
				String roles = entry.getValue().toString();
				String[] roleArray = roles.trim().split(",");
				Arrays.sort(roleArray);
				securedResources.put(securedURL.trim(), roleArray);
				log.info("{} : {} loading", securedURL, roleArray);
			}
		}catch(IOException e) {
			throw new ServletException(e);
		}
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
//		1. 현재 요청이 보호자원인지 여부 확인
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		String uri = req.getRequestURI();
		uri = uri.substring(req.getContextPath().length());
		uri = uri.split(";")[0];
		boolean secured = securedResources.containsKey(uri); //true: 보호자원  //false:비보호자원
		boolean pass = false;
		//		2-1. 보호자원
		if(secured) {
//			3. 인증 여부 확인
			Object authMember = req.getSession().getAttribute("authMember");
			if(authMember!=null) {
//				3.1 : 인증 : 통과
				pass = true;
			}else {
//				3.2 : 미인증 : 로그인 폼으로 이동.
				pass = false;
			}
			
		}else {
//		2-2. 비보호 자원 : 통과
			pass = true;
		}
		if(pass) {
//		4-1. 통과
			chain.doFilter(request, response);
		}else {
//		4-2. 로그인폼으로 이동
			resp.sendRedirect(req.getContextPath() + "/login/loginForm.do");
		}
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
