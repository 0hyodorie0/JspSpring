package kr.or.ddit.commons.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.BadRequestException;
import kr.or.ddit.member.service.AuthencticateServiceImpl;
import kr.or.ddit.member.service.AuthenticateService;
import kr.or.ddit.utils.CookieUtils;
import kr.or.ddit.vo.MemberVO;

@Controller
@RequestMapping("/login")
public class LoginController{ // POJO
	@Inject
	private AuthenticateService service;
	
	@GetMapping("loginForm.do")
	public String form(){
//		1. 요청 받고, 분석(파리미터, 헤더), 검증
//		2. 컨텐츠(Model) 확보
//		3. Scope를 선택하고 model 저장.
//		4. 뷰 선택
		return "login/loginForm"; // logical view name
	}
	
	@PostMapping(value="loginProcess.do")
	public String process(
			HttpSession session
			, @RequestParam String memId
			, @RequestParam String memPass
			, HttpServletRequest req
			, HttpServletResponse resp
	) throws IOException{
//		1. 요청 받고, 분석(파리미터, 헤더), 검증
		if(session==null || session.isNew()) {
			throw new BadRequestException();
		}
		
		// Builder design pattern
		MemberVO input = new MemberVO(memId, memPass);
		req.setAttribute("inputData", input);
		
		boolean valid = true;
		String viewName = null;
		if(valid) {
//		2. 컨텐츠(Model) 확보(로직실행)
			Object auth = service.authenticate(input);
			if(auth instanceof MemberVO) {
				String saveId = req.getParameter("saveId");
				int maxAge = 0;
				if("save".equals(saveId)) {
					maxAge = 60*60*24*7;
				}
				Cookie idCookie = CookieUtils.createCookie("idCookie", memId, 
											req.getContextPath(), maxAge);
				resp.addCookie(idCookie);
				// login success, welcome page 이동, redirect로 이동
				// "a001 이 로그인에 성공함" 메시지 전달. -- flash attribute
				session.setAttribute("message", memId+" 이 로그인에 성공함.");
				session.setAttribute("authMember", auth);
				viewName = "redirect:/";
			}else {
				// login fail(password오류), loginForm  이동, dispatch  forward 이동.
				if(ServiceResult.NOTEXIST.equals(auth)) {
					session.setAttribute("message", "해당 유저는 존재하지 않습니다.가입부터 하셈.");
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
//		3. Scope를 선택하고 model 저장.
//		4. 뷰 선택
		return viewName;
	}
	
	@PostMapping(value="logout.do")
	public String logout(HttpSession session) throws UnsupportedEncodingException{
		if(session==null || session.isNew()) {
			throw new BadRequestException();
		}
		session.invalidate();
		String message = URLEncoder.encode("로그아웃성공", "UTF-8");
		return "redirect:/?message="+message;
	}
}














