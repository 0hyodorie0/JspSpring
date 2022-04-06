package kr.or.ddit.ioc.lab1.dao;

public class SampleDAOFactory {
	public SampleDAO getSampleDAO(){
		return new SampleDAOImpl_MariaDB();
	}
}
