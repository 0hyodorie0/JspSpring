package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.PKNotFoundException;
import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.utils.PasswordUtils;
import kr.or.ddit.vo.MemberVO;

public class MemberServiceImpl implements MemberService {
	
	private MemberDAO dao = new MemberDAOImpl();
	private AuthenticateService authService = new AuthencticateServiceImpl();

	@Override
	public ServiceResult createMember(MemberVO member) {
		ServiceResult result = null;
		try {
			retrieveMember(member.getMemId());
			result = ServiceResult.PKDUPLICATED;
		}catch (Exception e) {
			String encoded = PasswordUtils.encodePassword(member.getMemPass());
			member.setMemPass(encoded);
			int rowcnt = dao.insertMember(member);
			result = rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
		}
		return result;
	}

	@Override
	public List<MemberVO> retrieveMemberList() {
		return dao.selectMemberList();
	}

	@Override
	public MemberVO retrieveMember(String memId) throws PKNotFoundException {
		MemberVO member = dao.selectMember(memId);
		if(member==null)
			throw new PKNotFoundException(memId+"에 해당하는 회원이 없음.");
		return member;
	}

	@Override
	public ServiceResult modifyMember(MemberVO member) {
		ServiceResult retValue = null;
		Object result = authService.authenticate(member);
		if(result instanceof MemberVO) {
			int rowcnt = dao.updateMember(member);
			retValue = rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
		}else {
			if(ServiceResult.INVALIDPASSWORD.equals(result)) {
				retValue = (ServiceResult) result;
			}else {
				throw new PKNotFoundException(member.getMemId()+ "에 해당하는 유저가 없음.");
			}
		}
		return retValue;
	}

	@Override
	public ServiceResult removeMember(MemberVO member) {
		ServiceResult retValue = null;
		Object result = authService.authenticate(member);
		if(result instanceof MemberVO) {
			int rowcnt = dao.deleteMember(member.getMemId());
			retValue = rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
		}else {
			if(ServiceResult.INVALIDPASSWORD.equals(result)) {
				retValue = (ServiceResult) result;
			}else {
				throw new PKNotFoundException(member.getMemId()+ "에 해당하는 유저가 없음.");
			}
		}
		return retValue;
	}

}











