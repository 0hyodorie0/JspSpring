package kr.or.ddit.servlet01;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.utils.CookieUtils;

@WebServlet("/imageRead.do")
public class ImageReadServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String imageName = req.getParameter("image");
		if(StringUtils.isBlank(imageName)) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		String imagesPath = "/kr/or/ddit/images";
		URL imagesURL = getClass().getResource(imagesPath);
		File imagesFolder = new File(imagesURL.getFile());
		
		File imageFile = new File(imagesFolder, imageName);
		if(!imageFile.exists()) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND, "해당 이미지는 존재하지 않습니다.");
			return;
		}
		
//		String cookieValue = URLEncoder.encode(imageName, "UTF-8");
//		Cookie imageCookie = new Cookie("lastImage", cookieValue);
//		imageCookie.setMaxAge(60*60*24*3);
//		imageCookie.setPath(req.getContextPath());
		Cookie imageCookie = 
				CookieUtils.createCookie("lastImage", imageName, 
								req.getContextPath(), 60*60*24*3);
		resp.addCookie(imageCookie);
		
		resp.setContentType(getServletContext().getMimeType(imageName));
//		String imagePath = "/kr/or/ddit/images/Tulips.jpg";
		byte[] buffer = new byte[1024];
		InputStream is = null;
		OutputStream os = null;
		try {
			is = new FileInputStream(imageFile);
			os = resp.getOutputStream();
	//		EOF : -1
			int length = -1;
			while((length = is.read(buffer))!=-1) {
				os.write(buffer, 0, length);
			}
		}finally {
			if(is!=null)
				is.close();
			if(os!=null)
				os.close();
		}
	}
}












