package kr.or.ddit.ioc.lab1.dao;

import java.util.LinkedHashMap;
import java.util.Map;

public class SampleDAOImpl_MariaDB implements SampleDAO {
	
	private static Map<String, String> sampleDB;
	static {
		sampleDB = new LinkedHashMap<>();
		sampleDB.put("PK1", "RECORD1 IN MARIADB");
		sampleDB.put("PK2", "RECORD2 IN MARIADB");
		sampleDB.put("PK3", "RECORD3 IN MARIADB");
	}

	public String selectRawData(String pk) {
		return sampleDB.get(pk);
	}

}
