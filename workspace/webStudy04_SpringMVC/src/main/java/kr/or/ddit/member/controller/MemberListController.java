package kr.or.ddit.member.controller;

import javax.servlet.http.HttpServletRequest;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.annotation.resolvers.ModelAttribute;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SimpleSearchVO;

@Controller
public class MemberListController{
	private MemberService service = new MemberServiceImpl();
	
	@RequestMapping("/member/memberList.do")
	public String listHandler(
			@RequestParam(value="page", required=false, defaultValue="1") int currentPage 
			, @ModelAttribute("simpleCondition") SimpleSearchVO simpleCondition
			, HttpServletRequest req
	){
		
//		String pageParam = req.getParameter("page");
//		String searchType = req.getParameter("searchType");
//		String searchWord = req.getParameter("searchWord");
//		SimpleSearchVO simpleCondition = new SimpleSearchVO(searchType, searchWord);
				
//		int currentPage = 1;
//		if(StringUtils.isNumeric(pageParam)) {
//			currentPage = Integer.parseInt(pageParam);
//		}
		PagingVO<MemberVO> paging = new PagingVO<>(3, 2);
		paging.setCurrentPage(currentPage);
		paging.setSimpleCondition(simpleCondition);
		
		service.retrieveMemberList(paging);
		
		req.setAttribute("paging", paging);
		
		return "member/memberList";
	}
	
}














