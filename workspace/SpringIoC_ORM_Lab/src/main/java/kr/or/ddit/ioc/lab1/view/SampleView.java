package kr.or.ddit.ioc.lab1.view;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.or.ddit.ioc.lab1.service.SampleService;

public class SampleView {
	public static void main(String[] args) {
		ApplicationContext context =
				new ClassPathXmlApplicationContext("/kr/or/ddit/ioc/lab1/conf/Sample-Context.xml");
		SampleService service = context.getBean(SampleService.class);		
		
		String parameter = "PK2";
		StringBuffer model = service.retrieveInfo(parameter);
		System.out.println(model);
	}
}






