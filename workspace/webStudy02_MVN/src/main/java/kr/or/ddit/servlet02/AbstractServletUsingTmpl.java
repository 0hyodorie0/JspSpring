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

@WebServlet("*.tmpl")
public abstract class AbstractServletUsingTmpl extends HttpServlet {
	protected abstract Map<String, Object> getDataMap(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		//1. tmpl 파일 읽기 : /01/gugudan.tmpl  
		ServletContext application = getServletContext();
		StringBuffer tmpl = new StringBuffer();
		String path = req.getServletPath();
		try(
				InputStream is = application.getResourceAsStream(path);
				InputStreamReader isr = new InputStreamReader(is);
				BufferedReader reader = new BufferedReader(isr);
		){
			String tmp = null;
			while((tmp = reader.readLine()) != null) {
				tmpl.append(tmp + "\n");
			}
		}
		
		//2. 데이터를 치환 : <%=now%>, <%=gugudan%>
		Map<String, Object> dataMap = getDataMap(req, resp);
		
		String regex = "<%=(\\w+)%>";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(tmpl);
		StringBuffer html = new StringBuffer();
		while(matcher.find()) {
			String dataName = matcher.group(1);
			Object data = dataMap.get(dataName);
			matcher.appendReplacement(html, Objects.toString(data, ""));
		}
		matcher.appendTail(html);
		
		//3. 완전한 형태의 HTML 컨텐츠 응답 전송
		
		//try with resource 구문(java 1.7)
		try(
				PrintWriter out = resp.getWriter(); //out.close(); 생략가능
		){
			out.print(html);
		}
		
	}

	
}
