package kr.or.ddit.ioc.lab1.view;

import java.io.File;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;

public class ResourceLoaderDesc {
	public static void main(String[] args) {
		ConfigurableApplicationContext contextx = new ClassPathXmlApplicationContext();
		
		String cPFile = "/log4j2.xml";
		String fsFile = "file://D:/contents/Desert.jpg";
		
		Resource cPRes = contextx.getResource(cPFile);
		Resource fsRes = contextx.getResource(fsFile);
		
		System.out.println(cPRes.exists());
		System.out.println(fsRes.exists());
		
		System.out.println(cPRes.getClass().getSimpleName());
		System.out.println(fsRes.getClass().getSimpleName());
		
//		File cPRes = new File(cPFile);
//		File fsRes = new File(fsFile);
	}
}
