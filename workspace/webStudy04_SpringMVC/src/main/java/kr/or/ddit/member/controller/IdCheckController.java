package kr.or.ddit.member.controller;


import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.exception.PKNotFoundException;
import kr.or.ddit.member.service.MemberService;

@Controller
public class IdCheckController{
	@Inject
	private MemberService service;
	
	@PostMapping(value="/member/idCheck.do", produces=MediaType.TEXT_PLAIN_VALUE)
	@ResponseBody
	public String idCheck(
			@RequestParam String memId
		) {
		
		boolean available = false;
		
		try {
			service.retrieveMember(memId);
			
		}catch (PKNotFoundException e) {
			available = true;
		}
		
		return available+"";
	}
}










