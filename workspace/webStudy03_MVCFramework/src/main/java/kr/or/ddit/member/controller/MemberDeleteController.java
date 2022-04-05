package kr.or.ddit.member.controller;

import javax.servlet.http.HttpSession;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.vo.MemberVO;

@Controller
public class MemberDeleteController{
	private MemberService service = new MemberServiceImpl();
	
	@RequestMapping(value="/member/memberDelete.do", method=RequestMethod.POST)
	public String deleteMember(
		@RequestParam("memPass") String memPass
		, HttpSession session
	){
		MemberVO authMember = (MemberVO) session.getAttribute("authMember");
		
		String memId = authMember.getMemId();
		
		ServiceResult result = service.removeMember(new MemberVO(memId, memPass));
		String viewName = null;
		switch (result) {
		case INVALIDPASSWORD:
			viewName = "redirect:/myPage.do";
			session.setAttribute("message", "비밀번호 오류");
			break;
		case FAIL:
			viewName = "redirect:/myPage.do";
			session.setAttribute("message", "서버 오류, 쫌따 다시 탈퇴!");
			break;

		default:
			session.invalidate();
			viewName = "redirect:/";
			break;
		}
		return viewName;
	}
}














