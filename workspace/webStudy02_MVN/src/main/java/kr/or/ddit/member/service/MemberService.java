package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.PKNotFoundException;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;

/**
 * 회원 관리(CRUD)용 Business Logic Layer
 *
 */
public interface MemberService {
	/**
	 * 회원 등록
	 * @param member
	 * @return PKDUPLICATED, OK, FAIL
	 */
	public ServiceResult createMember(MemberVO member);
	/**
	 * 회원 목록 조회
	 * @param paging TODO
	 * @return 검색 조건에 맞는 회원이 없으면, .size()==0
	 */
	public List<MemberVO> retrieveMemberList(PagingVO<MemberVO> paging);
	/**
	 * 상세 조회
	 * @param memId
	 * @return 존재하지 않는 경우, Custom exception 발생
	 */
	public MemberVO retrieveMember(String memId) throws PKNotFoundException;
	/**
	 * 회원 정보 수정
	 * @param member
	 * @return 존재하지 않는 경우, Custom exception 발생, INVALIDPASSWORD, OK, FAIL
	 */
	public ServiceResult modifyMember(MemberVO member);
	/**
	 * 회원 탈퇴
	 * @param member
	 * @return 존재하지 않는 경우, Custom exception 발생, INVALIDPASSWORD, OK, FAIL
	 */
	public ServiceResult removeMember(MemberVO member);
}












