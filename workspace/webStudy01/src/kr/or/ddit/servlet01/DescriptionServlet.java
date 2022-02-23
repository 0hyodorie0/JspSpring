package kr.or.ddit.servlet01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//Convention over Configuration
@WebServlet("/desc")
public class DescriptionServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		MIME(MultiPurposed Internet Mail Extension)
//		main_type/sub_type;charset=encoding
//		ex) text/html, text/javascript, text/css, text/plain
		
		String mime = "text/html;charset=UTF-8";
		resp.setContentType(mime);
		String ip = req.getRemoteAddr();
		StringBuffer html = new StringBuffer();
		html.append("<html>                               \n");
		html.append("	<body>                            \n");
		html.append(String.format("		<h4>클라이언트 ip address : %s</h4>\n", ip));
		html.append("	</body>                           \n");
		html.append("</html>                              \n");
		PrintWriter out = resp.getWriter();
		out.println(html);
		out.close();
	}
	
}
