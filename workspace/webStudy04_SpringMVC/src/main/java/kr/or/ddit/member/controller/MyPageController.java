package kr.or.ddit.member.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.vo.MemberVO;

@Controller
public class MyPageController{
	@Inject
	private MemberService service;
	
	@RequestMapping("/myPage.do")
	public String myPage(
		@SessionAttribute(value="authMember", required=true) MemberVO authMember
		, Model model
	) {
		
		MemberVO detail = service.retrieveMember(authMember.getMemId());
		model.addAttribute("member", detail);
		return "member/myPage";
	}
}












