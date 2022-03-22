package kr.or.ddit.member.dao;

import java.util.List;

import kr.or.ddit.vo.MemberVO;

/**
 * 회원 관리(CRUD) 및 인증 시스템을 위한 Persistence Layer
 *
 */
public interface MemberDAO {
	 /**
	  * 아이디와 비밀번호의 기반의 데이터 조회
	 * @param input
	 * @return 조건에 맞는 사용자가 없으면, null 반환.
	 */
	public MemberVO selectMemberForAuth(MemberVO input);
	
	/**
	 * 신규 회원 정보 등록
	 * @param member
	 * @return 등록된 레코드 수 >0 성공
	 */
	public int insertMember(MemberVO member);
	/**
	 * 회원 목록 조회
	 * @return 조건에 맞는 레코드가 없는 경우, .size()==0 
	 */
	public List<MemberVO> selectMemberList();
	/**
	 * 회원 정보 상세 조회
	 * @param memId 조회할 아이디
	 * @return 존재하지 않는 경우, null 반환
	 */
	public MemberVO selectMember(String memId);
	/**
	 * 회원 정보 수정
	 * @param member 수정할 정보를 가진 VO
	 * @return > 0, 성공
	 */
	public int updateMember(MemberVO member);
	/**
	 * 회원 정보 삭제
	 * @param memId 삭제할 아이디
	 * @return > 0, 성공
	 */
	public int deleteMember(String memId);
}















