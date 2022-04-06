package kr.or.ddit.listener;


import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@WebListener
@Slf4j
public class CustomHttpServletRequestListener implements ServletRequestListener {
	//싱글톤으로 운영될떄엔 전역변수 쓰지 않음
	@Override
	public void requestInitialized(ServletRequestEvent sre) { //이벤트는 타겟에대한 정보를 갖고있음
		long start = System.currentTimeMillis(); //시스템이 갖고있는 현재 시각
		sre.getServletRequest().setAttribute("request.start", start);
	}
	
	@Override
	public void requestDestroyed(ServletRequestEvent sre) {
		long end = System.currentTimeMillis();
		long start = (long) sre.getServletRequest().getAttribute("request.start");

		HttpServletRequest req = (HttpServletRequest) sre.getServletRequest();
		log.info("{}, {}ms", req.getRequestURI(),end-start); //소요시간
	}

	

}
