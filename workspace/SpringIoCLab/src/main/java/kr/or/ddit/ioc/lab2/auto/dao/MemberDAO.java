package kr.or.ddit.ioc.lab2.auto.dao;

import kr.or.ddit.ioc.lab2.auto.vo.MemberVO;

public interface MemberDAO {
	
	//아이디조회  
	public MemberVO selectMemberById(String pk);
}
