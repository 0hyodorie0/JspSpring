package kr.or.ddit.employee.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.EmployeeVO;

public class EmployeeDAOImpl implements EmployeeDAO {
	private SqlSessionFactory sqlSessionFactory = 
			CustomSqlSessionFactoryBuilder.getSqlSessionFactory();


	
	@Override
	public List<EmployeeVO> selectParents() {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){
			return sqlSession.selectList("kr.or.ddit.employee.dao.EmployeeDAO.selectParents");
		}
	}

	@Override
	public List<EmployeeVO> selectChildren(String managerId) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){
			return sqlSession.selectList("kr.or.ddit.employee.dao.EmployeeDAO.selectChildren", managerId);
		}
	}

}
