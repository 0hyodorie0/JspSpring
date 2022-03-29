package kr.or.ddit.commons.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login/loginForm.do")
public class LoginFormServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
//		1. 요청 받고, 분석(파리미터, 헤더), 검증
//		2. 컨텐츠(Model) 확보
//		3. Scope를 선택하고 model 저장.
//		4. 뷰 선택
		String viewName = "login/loginForm"; // logical view name
		viewResolve(viewName, req, resp);		
	}
//	redirect:/login/loginForm.jsp
	private void viewResolve(String viewName, HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		if(viewName.startsWith("redirect:")) {
			viewName = viewName.substring("redirect:".length());
			resp.sendRedirect(req.getContextPath() + viewName);
		}else {
			String prefix = "/WEB-INF/views/";
			String suffix = ".jsp";
			//		5. 뷰로 이동.
			req.getRequestDispatcher(prefix + viewName + suffix).forward(req, resp);
		}
	}
}














