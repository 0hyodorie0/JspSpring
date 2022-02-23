package kr.or.ddit.servlet01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//Convention over Configuration
@WebServlet()
public class DescriptionServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String ip = req.getRemoteAddr();
		StringBuffer html = new StringBuffer();
		html.append("<html>                               \n");
		html.append("	<body>                            \n");
		html.append(String.format("		<h4>client ip address : %s</h4>\n", ip));
		html.append("	</body>                           \n");
		html.append("</html>                              \n");
		PrintWriter out = resp.getWriter();
		out.println(html);
		out.close();
	}
	
}
