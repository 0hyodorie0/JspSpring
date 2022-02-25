package kr.or.ddit.servlet02;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 1. /02/gugudan.do 요청이 발생하면,
 * 2. 2단부터 9단까지 승수(1~9)에 따라 구구단 컨텐츠 제공. + 폰트 빨간색
 * 3. table 태그 UI
 * 4. 한 단이 하나의 행을 구성함.
 * 5. 오늘, 현재 시각이 h4 태그를 이용한 타이틀로 출력됨. + 배경 노란색
 * 6. 요청은 get 방식으로 발생함.
 *
 */
@WebServlet("/02/gugudan.do")
public class GugudanServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String mime = "text/html; charset=UTF-8";
		resp.setContentType(mime);
		StringBuffer html = new StringBuffer();
		html.append("<html>           \n");
		html.append("	<body>        \n");
		Calendar now = Calendar.getInstance();
		html.append(String.format("		<h4>%tc</h4> \n", now));
		html.append("		<table>   \n");
		for(int dan = 2; dan <= 9; dan++) {
			html.append("<tr>\n");
			for(int mul = 1; mul <= 9; mul++) {
				html.append(String.format("<td>%d*%d=%d</td>\n", dan, mul, dan*mul));
			}
			html.append("</tr>\n");
		}
		html.append("		</table>  \n");
		html.append("	</body>       \n");
		html.append("</html>          \n");
		PrintWriter out = null;
		try {
			out = resp.getWriter();
			out.print(html);			
		}finally {
			if(out!=null) {
				out.close();
			}
		}
	}
	

	
}
