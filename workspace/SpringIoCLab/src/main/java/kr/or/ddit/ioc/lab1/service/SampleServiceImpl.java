package kr.or.ddit.ioc.lab1.service;

import kr.or.ddit.ioc.lab1.dao.SampleDAO;
import kr.or.ddit.ioc.lab1.dao.SampleDAOFactory;
import kr.or.ddit.ioc.lab1.dao.SampleDAOImpl_MariaDB;
import kr.or.ddit.ioc.lab1.dao.SampleDAOImpl_Oracle;

public class SampleServiceImpl implements SampleService {
	
//	1. new 키워드로 인스턴스를 직접 생성. 결합력 최상
//	private SampleDAO dao = new SampleDAOImpl_Oracle();
//	2. Factory Object Pattern
//	private SampleDAO dao = new SampleDAOFactory().getSampleDAO();
//	3. Strategy Pattern, Strategy(Dependency) Injection
//	반드시 전략의 주입자가 필요함. ==> 4. 외부의 전략 주입자(DI Container) 활용
	private SampleDAO dao;
	
	public SampleServiceImpl(SampleDAO dao) {
		super();
		this.dao = dao;
	}

	public void setDao(SampleDAO dao) {
		this.dao = dao;
	}

	@Override
	public StringBuffer retrieveInfo(String pk) {
		String rawData = dao.selectRawData(pk);
		StringBuffer information = new StringBuffer("가공된");
		information.append(rawData);
		return information;
	}

}
