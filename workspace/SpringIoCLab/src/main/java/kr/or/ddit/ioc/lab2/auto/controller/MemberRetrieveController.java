package kr.or.ddit.ioc.lab2.auto.controller;

import kr.or.ddit.ioc.lab2.auto.service.MemberService;
import kr.or.ddit.ioc.lab2.auto.vo.MemberVO;

public class MemberRetrieveController {
	
	private MemberService service;
	
	//주입방식 = setter 	
	public void setService(MemberService service) {
		this.service = service;
	}
	
	public MemberVO readMemberDetail(String memId) {
	      if(memId ==null || memId.isEmpty())
	         throw new IllegalArgumentException("필수파라미터누락");
	      MemberVO member = service.retrieveMember(memId);
	      return member;
	   }


}
