package kr.or.ddit.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.exception.PKNotFoundException;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;

@Controller
public class IdCheckController{
	@Inject
	private MemberService service;
	
	@PostMapping(value="/member/idCheck.do", produces=MediaType.TEXT_PLAIN_VALUE)
	@ResponseBody
	public String idCheck(
		@RequestParam String memId
	){
		
		boolean available = false;
		
		try {
			service.retrieveMember(memId);
			
		}catch (PKNotFoundException e) {
			available = true;
		}
		return available+"";
	}
}










