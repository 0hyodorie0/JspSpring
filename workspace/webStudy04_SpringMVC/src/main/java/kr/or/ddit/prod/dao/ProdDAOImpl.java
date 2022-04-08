package kr.or.ddit.prod.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import kr.or.ddit.db.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;
@Repository
public class ProdDAOImpl implements ProdDAO {

	private SqlSessionFactory sqlSessionFactory = 
				CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	@Override
	public ProdVO selectProd(String prodId) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();	
		){
			ProdDAO mapper = sqlSession.getMapper(ProdDAO.class);
			return mapper.selectProd(prodId);
		}
	}
	@Override
	   public int insertProd(ProdVO prod) {
	      try(
	         SqlSession sqlSession = sqlSessionFactory.openSession();   
	      ){
	         int rowcnt = sqlSession.insert("kr.or.ddit.prod.dao.ProdDAO.insertProd", prod);
	         sqlSession.commit();
	         return rowcnt;
	      }
	   }
	   @Override
	   public int updateProd(ProdVO prod) {
	      try(
	         SqlSession sqlSession = sqlSessionFactory.openSession();   
	      ){
	         int rowcnt = sqlSession.insert("kr.or.ddit.prod.dao.ProdDAO.updateProd", prod);
	         sqlSession.commit();
	         return rowcnt;
	      }
	   }

	
	@Override
	public int selectTotalRecord(PagingVO<ProdVO> paging) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();	
		){
			ProdDAO mapper = sqlSession.getMapper(ProdDAO.class);
			return mapper.selectTotalRecord(paging);
		}
	}
	
	@Override
	public List<ProdVO> selectProdList(PagingVO<ProdVO> paging) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();	
		){
			ProdDAO mapper = sqlSession.getMapper(ProdDAO.class);
			return mapper.selectProdList(paging);
		}
	}

}











