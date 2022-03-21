package kr.or.ddit.member.service;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.vo.MemberVO;

public class AuthencticateServiceImpl implements AuthenticateService {
	private MemberDAO dao = new MemberDAOImpl();
	
	@Override
	public Object authenticate(MemberVO input) {
		Object retValue = null;
		MemberVO saved = dao.selectMemberForAuth(input);
		if(saved!=null) {
			String inputPass = input.getMemPass();
			String savedPass = saved.getMemPass();
			if(savedPass.equals(inputPass)) {
				retValue = saved;
			}else {
				retValue = ServiceResult.INVALIDPASSWORD;	
			}
		}else {
			retValue = ServiceResult.NOTEXIST;
		}
		return retValue;
	}

}
