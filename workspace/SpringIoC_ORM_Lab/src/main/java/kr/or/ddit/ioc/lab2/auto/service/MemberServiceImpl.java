package kr.or.ddit.ioc.lab2.auto.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.ioc.lab2.auto.dao.MemberDAO;
import kr.or.ddit.ioc.lab2.auto.vo.MemberVO;

@Service
public class MemberServiceImpl implements MemberService {
	
	private MemberDAO dao;
	
	@Inject
	public MemberServiceImpl(MemberDAO dao) {
		super();
		this.dao = dao;
	}

	@Override
	public MemberVO retrieveMember(String memId) {
		MemberVO member = dao.selectMemberById(memId);
		if(member==null)
			throw new IllegalArgumentException(memId+" 해당 유저 없음.");
		return member;
	}

}











