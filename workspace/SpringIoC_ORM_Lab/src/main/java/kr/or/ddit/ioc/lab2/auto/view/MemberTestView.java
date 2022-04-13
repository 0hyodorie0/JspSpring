package kr.or.ddit.ioc.lab2.auto.view;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import kr.or.ddit.ioc.lab2.auto.controller.MemberRetrieveController;
import kr.or.ddit.ioc.lab2.auto.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MemberTestView {
	public static void main(String[] args) {
		ConfigurableApplicationContext parentContext =
				new GenericXmlApplicationContext("classpath:/kr/or/ddit/ioc/lab2/conf/Auto-DI.xml");
		ConfigurableApplicationContext childContext =
				new ClassPathXmlApplicationContext(
					new String[] {"/kr/or/ddit/ioc/lab2/conf/child/Child-Context.xml"}
					, parentContext
				);
//		context.registerShutdownHook();
		
		String memId = "a001";
		MemberRetrieveController controller = 
				childContext.getBean(MemberRetrieveController.class);
		MemberVO member = controller.readMemberDetail(memId);
		log.info("검색된 결과 : {}", member);
	}
}













