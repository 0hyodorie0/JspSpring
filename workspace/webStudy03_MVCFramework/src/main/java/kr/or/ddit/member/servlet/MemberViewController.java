package kr.or.ddit.member.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.exception.PKNotFoundException;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.vo.MemberVO;

@Controller
public class MemberViewController {
	private MemberService service = new MemberServiceImpl();
	
	@RequestMapping("/member/memberView.do")
	public String viewHandler(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String memId = req.getParameter("who");
		if(StringUtils.isBlank(memId)) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "필수 파라미터 누락. 누구를 조회하시는교?!");
			return null;
		}
		try {
			MemberVO member = service.retrieveMember(memId);
			
			req.setAttribute("member", member);
			
			return "member/memberView";
			
			
		}catch (PKNotFoundException e) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND, "조회할 회원이 없음.");
			return null;
		}
	}
	
}




















