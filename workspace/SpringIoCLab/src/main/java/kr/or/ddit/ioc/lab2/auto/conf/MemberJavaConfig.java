package kr.or.ddit.ioc.lab2.auto.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import kr.or.ddit.ioc.lab2.auto.controller.MemberRetrieveController;
import kr.or.ddit.ioc.lab2.auto.dao.InMemoryMemberDAOImpl;
import kr.or.ddit.ioc.lab2.auto.dao.MemberDAO;
import kr.or.ddit.ioc.lab2.auto.service.MemberService;
import kr.or.ddit.ioc.lab2.auto.service.MemberServiceImpl;

@Configuration
@ComponentScan("kr.or.ddit.ioc.lab2.auto")
public class MemberJavaConfig {
	
	@Bean(value="memberDAO")
	@Scope("prototype")
	public MemberDAO memberDAO() {
		return new InMemoryMemberDAOImpl();
	}
	
	@Bean
	public MemberService memberService(@Autowired MemberDAO dao) {
		return new MemberServiceImpl(dao);
	}
	
	@Bean
	public MemberRetrieveController retrieveController() {
		MemberRetrieveController controller = new MemberRetrieveController();
		
		return controller;
	}
}
