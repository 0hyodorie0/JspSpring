package kr.or.ddit.ioc.lab2.auto.controller;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;

import kr.or.ddit.ioc.lab2.auto.service.MemberService;
import kr.or.ddit.ioc.lab2.auto.vo.MemberVO;

@Controller
public class MemberRetrieveController {

	private MemberService service;
	@Required
	@Inject
	public void setService(MemberService service) {
		this.service = service;
	}
	
	public MemberVO readMemberDetail(String memId) {
		if(memId==null || memId.isEmpty())
			throw new IllegalArgumentException("필수 파라미터 누락");
		MemberVO member = service.retrieveMember(memId);
		return member;
	}
}












