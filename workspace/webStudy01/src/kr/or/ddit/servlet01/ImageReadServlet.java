package kr.or.ddit.servlet01;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/imageRead.do")
public class ImageReadServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("image/jpeg");
		String imagePath = "/kr/or/ddit/images/Tulips.jpg";
		byte[] buffer = new byte[1024];
		InputStream is = null;
		OutputStream os = null;
		try {
		 is = ImageReadServlet.class.getResourceAsStream(imagePath);
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
