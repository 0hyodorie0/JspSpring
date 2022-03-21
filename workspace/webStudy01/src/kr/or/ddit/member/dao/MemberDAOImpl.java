package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.utils.DataMapperUtils;
import kr.or.ddit.utils.SqlMapperUtils;
import kr.or.ddit.vo.MemberVO;

public class MemberDAOImpl implements MemberDAO {
	
	private DataMapperUtils dataMapper = new DataMapperUtils();
	private SqlMapperUtils sqlMapper = new SqlMapperUtils("/kr/or/ddit/db/mappers/Member.xml");

	@Override
	public MemberVO selectMemberForAuth(MemberVO input) {
		MemberVO saved = null;
		
		try (
			Connection conn = ConnectionFactory.getConnection(); 
			PreparedStatement pstmt = sqlMapper
										.generatePreparedStatement(conn
													, "kr.or.ddit.member.dao.MemberDAO.selectMemberForAuth")
		) {
			pstmt.setString(1, input.getMemId());
			ResultSet rs = pstmt.executeQuery();
			
			saved =  (MemberVO) dataMapper.queryForObject(rs, MemberVO.class);
			
			return saved;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int insertMember(MemberVO member) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<MemberVO> selectMemberList() {
		List<MemberVO> memberList = new ArrayList<>();
		
		try(
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement pstmt = sqlMapper.generatePreparedStatement(conn, "kr.or.ddit.member.dao.MemberDAO.selectMemberList");
		){
			ResultSet rs = pstmt.executeQuery();
			memberList = dataMapper.queryForList(rs, MemberVO.class);
			return memberList;
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public MemberVO selectMember(String memId) {
		MemberVO member = null;
		
		try(
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement pstmt = sqlMapper.generatePreparedStatement(conn, 
									"kr.or.ddit.member.dao.MemberDAO.selectMember");
		){
			pstmt.setString(1, memId);
			ResultSet rs = pstmt.executeQuery();
			
			member = (MemberVO) dataMapper.queryForObject(rs, MemberVO.class);
			
			return member;
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int updateMember(MemberVO member) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteMember(String memId) {
		// TODO Auto-generated method stub
		return 0;
	}

}









