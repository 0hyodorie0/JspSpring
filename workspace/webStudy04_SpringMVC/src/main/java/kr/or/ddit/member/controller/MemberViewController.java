package kr.or.ddit.member.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.vo.MemberVO;

@Controller
public class MemberViewController{
	@Inject
	private MemberService service;
	
	@RequestMapping("/member/memberView.do")
	public String viewHandler(
		@RequestParam("who") String memId
		, Model model
	){
		MemberVO member = service.retrieveMember(memId);
		
		model.addAttribute("member", member);
		
		return "member/memberView";
	}
}




















