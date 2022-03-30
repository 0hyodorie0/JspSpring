package kr.or.ddit.member.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.SimpleSearchVO;

@Controller
public class MemberListController {
	private MemberService service = new MemberServiceImpl();
	
	
	@RequestMapping("/member/memberList.do")
	public String doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
				
		String pageParam = req.getParameter("page");
		String searchType = req.getParameter("searchType");
		String searchWord = req.getParameter("searchWord");
		SimpleSearchVO simpleCondition = new SimpleSearchVO(searchType, searchWord);
				
		int currentPage = 1;
		if(StringUtils.isNumeric(pageParam)) {
			currentPage = Integer.parseInt(pageParam);
		}
		PagingVO<MemberVO> paging = new PagingVO<>(3, 2);
		paging.setCurrentPage(currentPage);
		paging.setSimpleCondition(simpleCondition);
		
		service.retrieveMemberList(paging);
		
		req.setAttribute("paging", paging);
		
		String viewName = "member/memberList";
		return viewName;
	}
	
	
}
