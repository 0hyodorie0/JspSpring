package kr.or.ddit.ioc.lab2.various;

import java.io.IOException;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class VariousDITestView {
	public static void main(String[] args) throws IOException {
		//컨테이너는 자체로 리소스 오더
		ConfigurableApplicationContext context =
				new GenericXmlApplicationContext("classpath:/kr/or/ddit/ioc/lab2/conf/Various-DI.xml"); 
		context.registerShutdownHook();
		
		 VariousDIVO vo_ci = context.getBean("variousDIVO_CI", VariousDIVO.class);
//		 VariousDIVO vo_si = context.getBean("variousDIVO_SI", VariousDIVO.class);
//		 
//		 log.info("생성자 주입 객체 : {}", vo_ci);
//		 log.info("setter 주입 객체 : {}", vo_si);
//		 
//		 Resource res1 = vo_ci.getCpRes();
//		 Resource res2 = vo_ci.getCpRes();
//		 log.info(" class path resource : {}", res1.exists());
//		 log.info(" file system path resource : {}", res2.exists());
//		 
////		 https://www.google.co.kr/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png
//			 
//		 Resource urlRes = context.getResource("https://www.google.co.kr/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png");
//		 log.info("url resource : {}", urlRes.getURI());
	}
}
