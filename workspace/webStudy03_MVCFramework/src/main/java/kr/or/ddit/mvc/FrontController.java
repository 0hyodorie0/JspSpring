package kr.or.ddit.mvc;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import kr.or.ddit.mvc.annotation.HandlerAdapter;
import kr.or.ddit.mvc.annotation.HandlerMapping;
import kr.or.ddit.mvc.annotation.RequestMappingHandlerAdapter;
import kr.or.ddit.mvc.annotation.RequestMappingHandlerMapping;
import kr.or.ddit.mvc.annotation.RequestMappingInfo;

public class FrontController extends HttpServlet {
	private HandlerMapping handlerMapping;
	private HandlerAdapter handlerAdapter;
	private ViewResolver viewResolver;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
			super.init(config);
			handlerMapping = new RequestMappingHandlerMapping("kr.or.ddit");
			handlerAdapter = new RequestMappingHandlerAdapter();
			viewResolver = new InternalResourceViewResolver();
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

////		/member/memberView.do;jsessionid=asdfasdf
//		System.out.println(uri);
////		/member/memberView.do
//		String viewName = null;
//		boolean controllerSearched = false;
		RequestMappingInfo mappingInfo = handlerMapping.findCommandHandler(req);
		if(mappingInfo==null) {
			resp.sendError(404, "해당 서비스는 지원하지 않습니다.");
			return;
		}
		String viewName = handlerAdapter.invokeHandler(mappingInfo, req, resp);
		
		
		if(viewName!=null) {
			viewResolver.viewResolve(viewName, req, resp);			
		}else {
			if(!resp.isCommitted()) {
				resp.sendError(500, "커맨드 핸들러에서 논리적 뷰네임이 결정되지 않았음.");
			}
			
		}
	}
	
	private void viewResolve(String viewName, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//5. 뷰로 이동.
		if(viewName.startsWith("redirect:")) {
			viewName = viewName.substring("redirect:".length());
			resp.sendRedirect(req.getContextPath() + viewName);
		}else if(viewName.startsWith("forward:")) {
			viewName = viewName.substring("forward:".length());
			req.getRequestDispatcher(viewName).forward(req, resp);
		}else {
			String prefix ="/WEB-INF/views/";
			String suffix = ".jsp";
			req.getRequestDispatcher(prefix + viewName + suffix).forward(req, resp);
		}
		
	}
}
