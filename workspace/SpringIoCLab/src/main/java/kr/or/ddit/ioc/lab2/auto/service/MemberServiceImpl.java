package kr.or.ddit.ioc.lab2.auto.service;

import kr.or.ddit.ioc.lab2.auto.dao.MemberDAO;
import kr.or.ddit.ioc.lab2.auto.vo.MemberVO;

public class MemberServiceImpl implements MemberService {
	
	//주입받을수있는 구조만 만들면 됨 
	//직접생성하지 않고 주입받을수있는 제어권이 역전된 구조 !
	//dao의 구현체에 결합력이 발생하지 않음 db 연결하더라도 여길 수정할 필요가 없음 
	
	
	private MemberDAO dao;
	

 
	public MemberServiceImpl(MemberDAO dao) {
		super();
		this.dao = dao;
	}


	@Override
	public MemberVO retrieveMember(String memId) {
		MemberVO member =  dao.selectMemberById(memId);
		if(member==null) 
			throw new IllegalArgumentException(memId+"해당 유저가없음");
		return member;
	}

}
