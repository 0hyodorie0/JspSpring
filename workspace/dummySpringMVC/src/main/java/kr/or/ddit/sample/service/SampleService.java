package kr.or.ddit.sample.service;

import kr.or.ddit.sample.dao.SampleDAO;

public class SampleService {


	   private SampleDAO dao; 
	   
	   // 생성자가 추가되는 순간 기본생성자가 없어짐 
	   public SampleService(SampleDAO dao) {
	   super();
	   this.dao = dao;
	   }


	   public StringBuffer retrieveInfo() {
	      String raw =   dao.selectRawData(); 
	      return new StringBuffer("가공된" + raw ) ; 
	      
	   }

	}