package kr.or.ddit.servlet09;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.vo.fancytree.FancytreeNode;
import kr.or.ddit.vo.fancytree.FancytreeNodeAdapter;

@WebServlet(value="/server/browsing.do", loadOnStartup=1)
public class ServerFileBrowsingServlet extends HttpServlet{
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String accept = req.getHeader("Accept");
		String currentPath = req.getParameter("currentPath");
		if(StringUtils.isBlank(currentPath)) {
			currentPath = "/";
		}		
		ServletContext application = getServletContext();//server와 커뮤니케이션 ! 
//		"/resources/images/01.jpg";

		if(StringUtils.containsAnyIgnoreCase(accept, "json")) {//마샬링
			//모든자원은 다른식별자, 돌아오는 리턴은 set(중복허용하지않음)! 
			Set<String> resourcePaths = application.getResourcePaths(currentPath);
			List<FancytreeNode<File>> dataList = new ArrayList<>();
			for(String path : resourcePaths) {				
				String realPath =application.getRealPath(path);
				System.out.println(path);
				System.out.println(realPath);
				File adaptee = new File(realPath);
				dataList.add(new FancytreeNodeAdapter(adaptee,path));
			}
			
			Collections.sort(dataList);			
			try(
				PrintWriter out = resp.getWriter();
			){
				ObjectMapper mapper = new ObjectMapper();
				mapper.writeValue(out, dataList);				
			}			
		}else {
			String viewName= "server/browsing";
			viewResolve(viewName, req, resp);			
		}
				
	}
	
	
	private void viewResolve(String viewName, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if (viewName.startsWith("redirect:")) {
			viewName = viewName.substring("redirect:".length());
			resp.sendRedirect(req.getContextPath() + viewName);
		} else if (viewName.startsWith("forward:")) {
			viewName = viewName.substring("forward:".length());
			req.getRequestDispatcher(viewName).forward(req, resp);
		} else {
			String prefix = "/WEB-INF/views/";
			String suffix = ".jsp";
			// 5. 뷰로 이동
			req.getRequestDispatcher(prefix + viewName + suffix).forward(req, resp);
		}
	}
}
