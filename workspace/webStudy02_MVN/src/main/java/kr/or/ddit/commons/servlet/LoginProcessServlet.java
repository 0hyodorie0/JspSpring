package kr.or.ddit.commons.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.AuthenticateService;
import kr.or.ddit.member.service.AuthenticateServiceImpl;
import kr.or.ddit.utils.CookieUtils;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/login/loginProcess.do")
public class LoginProcessServlet extends HttpServlet {
	private AuthenticateService service = new AuthenticateServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		//1. 요청 받고, 분석(파라미터, 헤더), 검증
		HttpSession session = req.getSession(false);
		if(session == null || session.isNew()) {
			resp.sendError(400, "정상 로그인 요청이 아님");
			return;
		}
		String memId = req.getParameter("memId");
		String memPass = req.getParameter("memPass");
		
		
		// Builder design pattern
		MemberVO input = new MemberVO(memId, memPass);
		req.setAttribute("inputData", input);
		// Alt + Shift + R 변수명 변경
		
		
		boolean valid = validate(input);
		String viewName = null;
		if(valid) {
			//2. 컨턴츠(Model) 확보(로직실행)
			Object auth = service.authenticate(input);
			
			if(auth instanceof MemberVO) {
				// loginsuccess, welcome page 이동, redirect로 이동
				// "a001 이 로그인에 성공함" 메시지전달. -- flash attrlbute
				int maxAge = 0;
				String saveId = req.getParameter("saveId");
				if(saveId!=null) {
					maxAge = 60*60*24*7;
				}
				Cookie idCookie = CookieUtils.createCookie("saveId", memId, req.getContextPath(), maxAge);
				resp.addCookie(idCookie);					
				session.setAttribute("message", memId + " 이 로그인에 성공함.");
				session.setAttribute("authMember", auth);
				viewName="redirect:/";
			}else {
				// login fail(password오류). loginForm 이동, dispatch forward이동.
				if(ServiceResult.NOTEXIST.equals(auth)) {
					// "해당 유저확인"
					session.setAttribute("message", "해당 유저는 존재하지 않습니다.가입부터 하세요.");
				}else if(ServiceResult.INVALIDPASSWORD.equals(auth)) {
					// "비밀번호 오류" 메시지 전달
					session.setAttribute("message", "비밀번호 오류");					
				}
				
				viewName = "redirect:/login/loginForm.do";
			}
		}else {
			session.setAttribute("message", "아이디나 비밀번호 누락");
			viewName = "redirect:/login/loginForm.do";
		}
		//3. Scope를 선택하고 model 저장.
		//4. 뷰 선택
		viewResolve(viewName, req, resp);
		
	}
//	private boolean authenticate(MemberVO member) {
//		// id == password 하면 인증 성공.
//		String memId = member.getMemId();
//		String memPass = member.getMemPass();
//		return memId.equals(memPass);
//	}
	
	private boolean validate(MemberVO member) {
		String memId = member.getMemId();
		String memPass = member.getMemPass();
		boolean valid = !(StringUtils.isBlank(memId) || StringUtils.isBlank(memPass));
		return valid;
	}
	
	private void viewResolve(String viewName, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//5. 뷰로 이동.
		if(viewName.startsWith("redirect:")) {
			viewName = viewName.substring("redirect:".length());
			resp.sendRedirect(req.getContextPath() + viewName);
		}else {
			String prefix ="/WEB-INF/views/";
			String suffix = ".jsp";
			req.getRequestDispatcher(prefix + viewName + suffix).forward(req, resp);
		}
		
	}
}
