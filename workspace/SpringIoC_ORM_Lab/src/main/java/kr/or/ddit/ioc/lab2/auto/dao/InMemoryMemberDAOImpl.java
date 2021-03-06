package kr.or.ddit.ioc.lab2.auto.dao;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kr.or.ddit.ioc.lab2.auto.vo.MemberVO;

@Repository
public class InMemoryMemberDAOImpl implements MemberDAO {
	
	private static Map<String, MemberVO> memberTable;
	static {
		memberTable = new LinkedHashMap<>();
		memberTable.put("a001", new MemberVO("a001", "김은대"));
		memberTable.put("b001", new MemberVO("b001", "이쁜이"));
	}

	@Override
	public MemberVO selectMemberById(String pk) {
		return memberTable.get(pk);
	}

}









