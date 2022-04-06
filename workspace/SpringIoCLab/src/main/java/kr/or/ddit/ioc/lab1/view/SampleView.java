package kr.or.ddit.ioc.lab1.view;

import kr.or.ddit.ioc.lab1.dao.SampleDAO;
import kr.or.ddit.ioc.lab1.dao.SampleDAOImpl_MariaDB;
import kr.or.ddit.ioc.lab1.dao.SampleDAOImpl_Oracle;
import kr.or.ddit.ioc.lab1.service.SampleService;
import kr.or.ddit.ioc.lab1.service.SampleServiceImpl;

public class SampleView {
	public static void main(String[] args) {
		SampleDAO dao = new SampleDAOImpl_MariaDB();
		SampleService service = new SampleServiceImpl(dao);
		
		String parameter = "PK2";
		StringBuffer model = service.retrieveInfo(parameter);
		System.out.println(model);
	}
}
