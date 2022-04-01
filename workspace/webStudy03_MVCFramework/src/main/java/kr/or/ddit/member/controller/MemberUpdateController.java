package kr.or.ddit.member.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.utils.ValidatorUtils;
import kr.or.ddit.validate.UpdateGroup;
import kr.or.ddit.vo.MemberVO;

//@WebServlet("/member/memberUpdate.do")
@Controller
public class MemberUpdateController {
	private MemberService service = new MemberServiceImpl();
	
	@RequestMapping("/member/memberUpdate.do")
	public String doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MemberVO authMember = (MemberVO) req.getSession().getAttribute("authMember");
		
		MemberVO member = service.retrieveMember(authMember.getMemId());
		
		req.setAttribute("member", member);
		
		return "member/memberEdit";
	}

	@RequestMapping(value="/member/memberUpdate.do", method=RequestMethod.POST)
	public String doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		MemberVO member = new MemberVO();
		req.setAttribute("member", member);
//		member.setMemId(req.getParameter("memId"));
		
		try {
			BeanUtils.populate(member, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new ServletException(e);
		}
		Map<String, List<String>> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors);
		
		boolean valid = new ValidatorUtils<MemberVO>().validate(member, errors, UpdateGroup.class);
		String viewName = null;
		if(valid) {
			ServiceResult result = service.modifyMember(member);
			switch (result) {
			case INVALIDPASSWORD:
				req.setAttribute("message", "비밀번호 오류");
				viewName = "member/memberEdit";
				break;
			case FAIL:
				req.setAttribute("message", "서버 오류, 잠시 뒤 다시 실행하세요.");
				viewName = "member/memberEdit";
				break;

			default:
				viewName = "redirect:/myPage.do";
				break;
			}
		}else {
			viewName = "member/memberEdit";
		}
		
		return viewName;
	}
	

}
