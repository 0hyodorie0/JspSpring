package kr.or.ddit.member.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SimpleSearchVO;

@Controller
public class MemberListController{
	@Inject
	private MemberService service;
	
	@RequestMapping("/member/memberList.do")
	public String listHandler(
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage 
			, @ModelAttribute("simpleCondition") SimpleSearchVO simpleCondition
			, Model model
	){
		
		PagingVO<MemberVO> paging = new PagingVO<>(3, 2);
		paging.setCurrentPage(currentPage);
		paging.setSimpleCondition(simpleCondition);
		
		service.retrieveMemberList(paging);
		
		model.addAttribute("paging", paging);
		
		return "member/memberList";
	}
	
}














