package kr.or.ddit.ioc.lab2.auto.view;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import kr.or.ddit.ioc.lab2.auto.conf.MemberJavaConfig;
import kr.or.ddit.ioc.lab2.auto.controller.MemberRetrieveController;
import kr.or.ddit.ioc.lab2.auto.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MemberJavaConfigTestView {
	public static void main(String[] args) {
		ConfigurableApplicationContext context =
			new AnnotationConfigApplicationContext(MemberJavaConfig.class);
		context.registerShutdownHook();
		
		MemberRetrieveController controller = context.getBean(MemberRetrieveController.class);
		
		MemberVO member = controller.readMemberDetail("a001");
		
		log.info("검색 결과 : {}", member);
	}
}










