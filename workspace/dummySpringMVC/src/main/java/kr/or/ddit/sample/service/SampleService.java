package kr.or.ddit.sample.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.sample.dao.SampleDAO;

@Service
public class SampleService {
	
	private SampleDAO dao;
	@Inject
	public SampleService(SampleDAO dao) {
		super();
		this.dao = dao;
	}

	public StringBuffer retrieveInfo() {
		String raw = dao.selectRawData();
		return new StringBuffer("가공된 "+raw); 
	}
}














