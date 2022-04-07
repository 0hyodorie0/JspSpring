package kr.or.ddit.sample.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.HomeController;
import kr.or.ddit.sample.dao.SampleDAO;

@Service
public class SampleService {
	
	   private SampleDAO dao; 
	   
	   // 생성자가 추가되는 순간 기본생성자가 없어짐 
	   @Inject
	   public SampleService(SampleDAO dao) {
	   super();
	   this.dao = dao;
	   }


	   public StringBuffer retrieveInfo() {
	      String raw =   dao.selectRawData(); 
	      return new StringBuffer("가공된" + raw ) ; 
	      
	   }

	}