package kr.or.ddit.ioc.lab2.various;

import java.io.IOException;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.io.Resource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class VariousDITestView {
	public static void main(String[] args) throws IOException {
		ConfigurableApplicationContext context =
				new GenericXmlApplicationContext("classpath:/kr/or/ddit/ioc/lab2/conf/Various-DI.xml");
		context.registerShutdownHook();
		
		VariousDIVO vo_ci = context.getBean("variousDIVO_CI", VariousDIVO.class);
//		VariousDIVO vo_si = context.getBean("variousDIVO_SI", VariousDIVO.class);
//		
//		log.info("생성자 주입 객체 : {}", vo_ci);
//		log.info("setter 주입 객체 : {}", vo_si);
//		
//		Resource res1 = vo_ci.getCpRes();
//		Resource res2 = vo_si.getCpRes();
//		log.info(" class path resource : {} ", res1.exists());
//		log.info(" file system path resource : {} ", res2.exists());
//		
//		
//		Resource urlRes = context.getResource("https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png");
//		log.info(" url resource : {}", urlRes.getURI());
			
	}
}













