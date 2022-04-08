package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.utils.ValidatorUtils;
import kr.or.ddit.validate.UpdateGroup;
import kr.or.ddit.vo.MemberVO;

@Controller
@RequestMapping("/member/memberUpdate.do")
public class MemberUpdateController{
	@Inject
	private MemberService service;
	
	@GetMapping
	public String form(HttpSession session
					 , Model model
					 , @SessionAttribute MemberVO authMember
		){
		 		
		MemberVO member = service.retrieveMember(authMember.getMemId());
		
		model.addAttribute("member", member);
		
		return "member/memberEdit";
	}
	
	@PostMapping
	public String process(
		@ModelAttribute MemberVO member //command object
		, Model model
	) {
				
		Map<String, List<String>> errors = new LinkedHashMap<>();
		model.addAttribute("errors", errors);
		
		boolean valid = new ValidatorUtils<MemberVO>().validate(member, errors, UpdateGroup.class);
		String viewName = null;
		if(valid) {
			ServiceResult result = service.modifyMember(member);
			switch (result) {
			case INVALIDPASSWORD:
				model.addAttribute("message", "비밀번호 오류");
				viewName = "member/memberEdit";
				break;
			case FAIL:
				model.addAttribute("message", "서버 오류, 잠시 뒤 다시 실행하세요.");
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
