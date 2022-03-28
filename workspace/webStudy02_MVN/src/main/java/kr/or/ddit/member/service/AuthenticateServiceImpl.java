package kr.or.ddit.member.service;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.utils.PasswordUtils;
import kr.or.ddit.vo.MemberVO;

public class AuthenticateServiceImpl implements AuthenticateService {
	
	private MemberDAO dao = new MemberDAOImpl();
	
	@Override
	public Object authenticate(MemberVO input) {
		Object obj = null;
		MemberVO vo = dao.slectMemberForAuth(input);
		if(vo!=null) {
			String inputPass = input.getMemPass();
			String voPass = vo.getMemPass();
			if(PasswordUtils.passwordMatcher(inputPass, voPass)) {
				obj = vo;				
			}else {
				obj = ServiceResult.INVALIDPASSWORD;
			}
		}else {
			obj = ServiceResult.NOTEXIST;
		}
		return obj;
	}

}
