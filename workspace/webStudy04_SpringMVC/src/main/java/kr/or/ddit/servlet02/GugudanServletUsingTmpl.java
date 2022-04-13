package kr.or.ddit.servlet02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/01/gugudan.tmpl")
public class GugudanServletUsingTmpl extends AbstractServletUsingTmpl{

	@Override
	protected Map<String, Object> getDataMap(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//2. 데이터를 치환 : <%=now%>, <%=gugudan%>
		Map<String, Object> dataMap = new HashMap<>();
		Calendar now = Calendar.getInstance();
		dataMap.put("now", now);
		StringBuffer gugudan = new StringBuffer();
		dataMap.put("gugudan", gugudan);
		for(int dan = 2; dan <= 9; dan++) {
			gugudan.append("<tr>");
			for(int mul = 1; mul <=9; mul++) {
				gugudan.append(
					String.format("<td>%d * %d = %d</td>\n", dan, mul, dan*mul)
				);
			}
			gugudan.append("</tr>\n");
		}
		return dataMap;
	}
	
}
