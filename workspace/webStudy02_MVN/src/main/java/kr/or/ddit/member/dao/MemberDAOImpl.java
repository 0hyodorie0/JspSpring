package kr.or.ddit.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.prod.dao.ProdDAO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;

public class MemberDAOImpl implements MemberDAO {
	private SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	
	@Override
	public MemberVO slectMemberForAuth(MemberVO input) {
		try(
			SqlSession sqlSeesion = sqlSessionFactory.openSession(); 
		){			
			return sqlSeesion.selectOne("kr.or.ddit.member.dao.MemberDAO.slectMemberForAuth", input);
		}
	}

	@Override
	public int insertMember(MemberVO member) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession(true);
		){
			//return sqlSession.insert("kr.or.ddit.member.dao.MemberDAO.insertMember", member);
			MemberDAO mapper = sqlSession.getMapper(MemberDAO.class);
			int rowcnt = mapper.insertMember(member);
			return rowcnt;
		}
	}

	@Override
	public int selectTotalRecord(PagingVO<MemberVO> paging) {
		try(
			SqlSession sqlSession  = sqlSessionFactory.openSession();
		){
			MemberDAO mapper = sqlSession.getMapper(MemberDAO.class);
	        return mapper.selectTotalRecord(paging);
		}
	}
	
	
	@Override
	public List<MemberVO> selectMemberList(PagingVO<MemberVO> paging) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){
			return sqlSession.selectList("kr.or.ddit.member.dao.MemberDAO.selectMemberList", paging);
		}
	}

	@Override
	public MemberVO selectMember(String memId) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){
			return sqlSession.selectOne("kr.or.ddit.member.dao.MemberDAO.selectMember",memId);
		}
	}

	@Override
	public int updateMember(MemberVO member) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession(true);
		){
			return sqlSession.update("kr.or.ddit.member.dao.MemberDAO.updateMember", member);
		}
	}

	@Override
	public int deleteMember(String memId) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession(false);
		){
			int rowcnt = sqlSession.update("kr.or.ddit.member.dao.MemberDAO.deleteMember",memId);
			sqlSession.commit();
			return rowcnt;
		}
	}



}
