package kr.or.ddit.ioc.lab1.dao;

import java.util.LinkedHashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SampleDAOImpl_Oracle implements SampleDAO {
	
	public SampleDAOImpl_Oracle() {
		super();
		log.info("{} 생성되고 초기화되었음.", this.getClass().getSimpleName());
	}

	private static Map<String, String> sampleDB;
	static{
		sampleDB = new LinkedHashMap<>();
		sampleDB.put("PK1", "RECORD1 IN ORACLE");
		sampleDB.put("PK2", "RECORD2 IN ORACLE");
		sampleDB.put("PK3", "RECORD3 IN ORACLE");
	}

	@Override
	public String selectRawData(String pk) {
		return sampleDB.get(pk);
	}

}











