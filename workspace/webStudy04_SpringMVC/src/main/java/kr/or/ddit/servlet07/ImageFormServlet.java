package kr.or.ddit.servlet07;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.utils.CookieUtils;

@WebServlet("/07/imageForm.do")
public class ImageFormServlet extends HttpServlet{
	
	private ServletContext application;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		application = getServletContext();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String imagesPath = "/kr/or/ddit/images";
		URL imagesURL = getClass().getResource(imagesPath);
		File imagesFolder = new File(imagesURL.getFile());
//		imagesFolder.list
		String[] children = imagesFolder.list((dir, name)->{
			String mime = application.getMimeType(name);
			return mime!=null && mime.startsWith("image/");
		});
		req.setAttribute("children", children);
		String lastImageValue = new CookieUtils(req).getCookieValue("lastImage");
//		Cookie[] cookies = req.getCookies();
//		if(cookies!=null) {
//			for(Cookie tmp : cookies) {
//				if("lastImage".equals(tmp.getName())) {
//					lastImageValue = URLDecoder.decode(tmp.getValue(), "UTF-8");
//					break;
//				}
//			} // for end
//		} // if end
		
		req.setAttribute("lastImage", lastImageValue);
		
		String viewName = "imageForm";
		viewResolve(viewName, req, resp);
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
