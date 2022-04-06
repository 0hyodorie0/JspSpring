package kr.or.ddit.listener;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import kr.or.ddit.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class CustomServletContextListner implements ServletContextListener {
	
	public void contextInitialized(ServletContextEvent sce)  { 
    	ServletContext target = sce.getServletContext(); //이벤트 타겟
    	target.setAttribute("cPath", target.getContextPath());
    	target.setAttribute("usercount", 0);
    	Set<MemberVO> userList = new LinkedHashSet<>();
    	target.setAttribute("userList", userList);
    	log.info("{} 초기화되었음.", target.getContextPath());   
    }
	
    public void contextDestroyed(ServletContextEvent sce)  { 
    	ServletContext target = sce.getServletContext(); //이벤트 타겟
    	target.removeAttribute("usercount");
    	target.removeAttribute("userList");
    	log.info("{} 소멸되었음.", target.getContextPath());   
    }

	
    
	
}
