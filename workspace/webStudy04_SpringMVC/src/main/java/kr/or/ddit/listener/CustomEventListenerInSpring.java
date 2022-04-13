package kr.or.ddit.listener;

import javax.servlet.ServletContext;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import lombok.extern.slf4j.Slf4j;

@Component // POJO
@Slf4j
public class CustomEventListenerInSpring {
	
	@EventListener(classes=ContextRefreshedEvent.class)
	public void eventListener(ContextRefreshedEvent event) {
		WebApplicationContext container = 
				(WebApplicationContext) event.getApplicationContext();
		log.info("컨테이너 초기화되었음.{}", container);
		ServletContext application = container.getServletContext();
		application.setAttribute("cPath", application.getContextPath());
		log.info("cPath 로 {} 를 저장했음.", application.getContextPath());
	}
}












