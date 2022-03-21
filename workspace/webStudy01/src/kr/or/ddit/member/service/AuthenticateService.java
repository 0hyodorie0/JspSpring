package kr.or.ddit.member.service;

import kr.or.ddit.vo.MemberVO;

/**
 * 인증 시스템을 위한 Business Logic Layer
 *
 */
public interface AuthenticateService {
	/**
	 * 아이디와 비밀번호 기반의 인증
	 * @param input
	 * @return 인증 성공시, MemberVO(인증 객체) 반환, 인증 실패시 ServiceResult 반환
	 */
	public Object authenticate(MemberVO input);
}
