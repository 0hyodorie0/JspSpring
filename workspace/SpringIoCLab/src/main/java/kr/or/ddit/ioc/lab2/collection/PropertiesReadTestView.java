package kr.or.ddit.ioc.lab2.collection;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class PropertiesReadTestView {
	public static void main(String[] args) {
//		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("/kr/or/ddit/ioc/lab2/conf/Properties-Read.xml");
//		
//		context.registerShutdownHook();
//		
//		DbInfoVO vo1 = context.getBean("DBInfoVO1", DbInfoVO.class);
//		
//		log.info("{}",vo1);
//		
//		DbInfoVO vo2 = context.getBean("DBInfoVO1", DbInfoVO.class);
//		
//		log.info("{}",vo2);
		
		Map<String, String> env = System.getenv();
		for(Entry<String, String> entry : env.entrySet()) {
			System.out.printf("%s : $s\n", entry.getKey(), entry.getValue());
		}
		
		Properties props = System.getProperties();
		for(Entry<Object, Object> entry : props.entrySet()) {
			System.err.printf("%s : $s\n", entry.getKey(), entry.getValue());
		}
	}
}
