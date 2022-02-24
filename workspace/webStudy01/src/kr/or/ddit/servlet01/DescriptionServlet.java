package kr.or.ddit.servlet01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//Convention over Configuration
/**
 * 서블릿
 *  : 자바를 기반으로 웹상의 요청을 처리할 수 있는 객체에 대한 조건의 집합
 *  동적 요청 분석 + 동적 응답 생성
 *
 */
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
