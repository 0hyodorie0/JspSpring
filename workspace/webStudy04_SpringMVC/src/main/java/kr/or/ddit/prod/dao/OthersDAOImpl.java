package kr.or.ddit.prod.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import kr.or.ddit.db.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.BuyerVO;

@Repository
public class OthersDAOImpl implements OthersDAO {
	
	private SqlSessionFactory sqlSessionFactory = 
			CustomSqlSessionFactoryBuilder.getSqlSessionFactory();

	@Override
	public List<Map<String, Object>> selectLprodList() {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();	
		){
			OthersDAO mapper = sqlSession.getMapper(OthersDAO.class);
			return mapper.selectLprodList();
		}
	}

	@Override
	public List<BuyerVO> selectBuyerList(String lprodGu) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();	
		){
			OthersDAO mapper = sqlSession.getMapper(OthersDAO.class);
			return mapper.selectBuyerList(lprodGu);
		}
	}

}
