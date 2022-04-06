package kr.or.ddit.ioc.lab1.view;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import kr.or.ddit.ioc.lab1.dao.SampleDAOImpl_MariaDB;
import kr.or.ddit.ioc.lab1.service.SampleService;

public class ContainerDescTestView {
	public static void main(String[] args) {
//		컨테이너 생성
		ConfigurableApplicationContext context = new GenericXmlApplicationContext("classpath:/kr/or/ddit/ioc/lab1/conf/Container-Desc.xml"); 
//		셧다운 생성
		context.registerShutdownHook();
		/*
		SampleService service1 = context.getBean("sampleService_CI", SampleService.class);
		SampleService service2 = context.getBean("sampleService_CI", SampleService.class);
		
		SampleService service3 = context.getBean("sampleService_SI", SampleService.class);
		
		System.out.println(service1 == service3);
		
		SampleDAOImpl_MariaDB mariaDAO1 = context.getBean(SampleDAOImpl_MariaDB.class);
		SampleDAOImpl_MariaDB mariaDAO2 = context.getBean(SampleDAOImpl_MariaDB.class);
		
		System.out.printf("mariaDAO1==mariaDAO2 -> %b\n", mariaDAO1==mariaDAO2);
		*/
//		Object model = service1.retrieveInfo("PK3");
//		System.out.println(model);
//		
//		context.close();
	}
}
