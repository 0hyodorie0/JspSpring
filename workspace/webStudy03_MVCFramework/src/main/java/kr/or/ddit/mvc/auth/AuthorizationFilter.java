package kr.or.ddit.mvc.auth;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.vo.MemberVO;

/**
 * 보호 자원에 대한 요청인 경우, 현재 사용자의 인가 여부를 확인.
 *
 */
public class AuthorizationFilter implements Filter{

	private ServletContext application;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.application = filterConfig.getServletContext();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
//		1. 보호 자원 데이터 로딩
		Map<String, String[]> securedResources = (Map) application.getAttribute(AuthenticationFilter.SECUREDRESOURCESNAME);
//		2. 현재 요청이 보호자원에 대한 요청인지 여부
		String uri = req.getRequestURI();
		uri = uri.substring(req.getContextPath().length());
		uri = uri.split(";")[0];
		String[] resRoles = securedResources.get(uri);
		boolean secured = resRoles!=null;
		boolean pass = false;
		if(secured) {
//		3-1. 보호 자원
//			4. 인가 확인
//				5. 인증 객체 확보
			MemberVO authMember =  (MemberVO) req.getSession().getAttribute("authMember");
			String userRole = authMember.getMemRole();
//				6. 인가 여부 확인 (자원에 설정된 권한과 사용자가 부여받은 역할 일치)
			if(Arrays.binarySearch(resRoles, userRole)>=0) {
//					7-1. 인가 : 통과
				pass = true;
			}else {
//					7-2. 미인가 : 401 에러
				pass = false;
			}
		}else {
//		3-2. 비보호 자원 : 통과
			pass = true;
		}
		
		if(pass) {
			chain.doFilter(request, response);
		}else {
			resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, uri+" 접근 권한이 없음.");
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}













