package kr.or.ddit.commons.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.BadRequestException;
import kr.or.ddit.member.service.AuthencticateServiceImpl;
import kr.or.ddit.member.service.AuthenticateService;
import kr.or.ddit.utils.CookieUtils;
import kr.or.ddit.vo.MemberVO;

//@WebServlet("/login/loginForm.do")
@Controller
@RequestMapping("/login")
public class LoginController extends HttpServlet {
//POJO
	@Inject
	private AuthenticateService service;

	@RequestMapping("loginForm.do")
	public String form(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		return "login/loginForm";//logical view name (논리적인이름)

	}
	
	@RequestMapping(value="loginProcess.do",method=RequestMethod.POST)
	public String process (HttpSession session
							, @RequestParam String memId
							, @RequestParam String memPass
							, HttpServletRequest req
							, HttpServletResponse resp
		) throws IOException {	
		
		if(session==null || session.isNew()) {
			throw new BadRequestException();
		}
		
		
		//인증에 성공한 사람의 모든 정보를 갖고 잇음 
		//Builder design Pattern
		MemberVO input = new MemberVO(memId,memPass);
		req.setAttribute("inputData", input);
		
		//1.요청받고, 분석(파라미터,헤더), 검증		
		boolean valid = true;
		String viewName = null;
		if(valid) {
			//2. 컨텐츠(model)확보(로직실행 
			Object auth = service.authenticate(input);
			if(auth instanceof MemberVO) {//인증의 성공 판단
							   //인증성공 유저 vo 
				String saveId = req.getParameter("saveId");
				int maxAge = 0; 
				if("save".equals(saveId)) {
					maxAge = 60*60*24*7;
				}
				//쿠키생성 
				Cookie idCookie = CookieUtils.createCookie("idCookie", memId,
						req.getContextPath(), maxAge);
				resp.addCookie(idCookie);
				
			//login success,welcome page 이동, redirect로 이동 
			//"a001이 로그인에 성공함" 메시지 전달.-- flash attribute
				session.setAttribute("message",memId+ "이 로그인에 성공함.");
				session.setAttribute("authMember", auth);
				viewName= "redirect:/";
			}else {
				//login fall(password오류), loginForm 이동, dispatch forward 이동 
				
				if(ServiceResult.NOTEXIST.equals(auth)) {
					session.setAttribute("message", "해당 user 는 존재하지않습니다. 가입부터하삼");
				}else if(ServiceResult.INVALIDPASSWORD.equals(auth)) {
					//"비밀번호 오류" 메시지 전달 
					session.setAttribute("message", "비밀번호오류");					
				}
				//redirect 방식 
				return "redirect:/login/loginForm.do";
			}
		}else {
			//요청이 검증을 통과하지 못함. (400) 
			//누락되면은 에러페이지는 보여지지 않는게 좋다. 
			//redirect 방식 
			req.setAttribute("message", "아이디나 비밀번호 누락");
			return "redirect:/login/loginForm.do";
		}
		//3. Scope를 선택하고 model 저장 
		//4. 뷰 선택
		return viewName;

	}
	
	
	@RequestMapping(value="logout.do",method=RequestMethod.POST)
	public String logout(HttpSession session) throws UnsupportedEncodingException{
	
		if(session==null || session.isNew()) {
			throw new BadRequestException();
		}
		session.invalidate();
		String message = URLEncoder.encode("로그아웃성공", "UTF-8");
		return "redirect:/?message="+message;
	}


}
