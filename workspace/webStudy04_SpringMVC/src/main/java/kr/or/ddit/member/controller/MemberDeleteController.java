package kr.or.ddit.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.vo.MemberVO;

@Controller
public class MemberDeleteController{
	@Autowired
	private MemberService service;
	
	@PostMapping(value="/member/memberDelete.do")
	public String deleteMember(
		@RequestParam String memPass
		, RedirectAttributes redirectAttributes
		, @SessionAttribute MemberVO authMember
		, HttpSession session 
	){
		String memId = authMember.getMemId();
		
		ServiceResult result = service.removeMember(new MemberVO(memId, memPass));
		String viewName = null;
		switch (result) {
		case INVALIDPASSWORD:
			viewName = "redirect:/myPage.do";
			redirectAttributes.addFlashAttribute("message", "비밀번호 오류");
			break;
		case FAIL:
			viewName = "redirect:/myPage.do";
			redirectAttributes.addFlashAttribute("message", "서버 오류, 쫌따 다시 탈퇴!");
			break;

		default:
			session.invalidate();
			viewName = "redirect:/";
			break;
		}
		return viewName;
	}
}














