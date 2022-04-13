package kr.or.ddit.ioc.lab2.collection;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CollectionDITestView {
	public static void main(String[] args) {
		ConfigurableApplicationContext context =
				 new ClassPathXmlApplicationContext("/kr/or/ddit/ioc/lab2/conf/Collection-DI.xml");
		context.registerShutdownHook();
		
		CollectionDIVO vo1 = context.getBean("cDIVO1", CollectionDIVO.class);
		CollectionDIVO vo2 = context.getBean("cDIVO2", CollectionDIVO.class);
		
		log.info("{}", vo1);
		log.info("{}", vo2);
	}
}
