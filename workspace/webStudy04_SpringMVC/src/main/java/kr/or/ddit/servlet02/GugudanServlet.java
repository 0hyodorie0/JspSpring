package kr.or.ddit.servlet02;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 1. /02/gugudan.do 요청이 발생하면,
 * 2. 2단부터 9단까지 승수(1~9)
 * 3. table 태그 UI
 * 4. 한단이 하나의 행을 구성함.
 * 5. 오늘, 현재 시각이 h4 태그를 이용한 타이틀로 출력됨.
 * 6. 요청은 get 방식으로 발생함.
 *
 */

@WebServlet("/02/gugudan.do")
public class GugudanServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html; charset=UTF-8"); 
		
		Date date = new Date();
		Calendar now = Calendar.getInstance();
		StringBuffer html = new StringBuffer();
		String sik = "";
		String mul = "";
		
		html.append("<html>\n<body>\n");
		html.append(String.format("<h4 style='background-color:yellow;'> 현재시각 : %tc</h4>\n", now));
		html.append("<table border='1' width='800px' height='350px'>\n");
		for(int i=2; i<10; i++) {
			html.append("<tr>\n");
			for(int j=1; j<10; j++) {
				sik = i+"X"+j+"=";
				mul = (i*j)+"";
				//html.append(String.format("<td>\n%s<span style='color:red;'>%s</span>\n</td>\n", sik, mul));
				
				html.append(String.format("<td>\n%d*%d=<span style='color:red;'>%d</span>\n</td>\n", i, j, i*j));
			}
			html.append("</tr>\n");
		}
		html.append("</table>\n");
		html.append("</body>\n</html>");
		PrintWriter out = resp.getWriter();
		try {
			out.print(html);
		}finally {
			if(out != null) out.close();
		}
		
	}
}

















