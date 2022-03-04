package kr.or.ddit.servlet04;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.enumpkg.BrowserInfo;

@WebServlet("/04/findBrowser")
public class FindBrowserServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String agent = req.getHeader("User-Agent");
		
		String browser = BrowserInfo.findBrowser(agent);
		
		Map<String, Object> result = Collections.singletonMap("browser", browser);
//		Marshalling
//		new Gson().toJSON();
		ObjectMapper mapper = new ObjectMapper();
		resp.setContentType("application/json;charset=UTF-8");
		try(
			PrintWriter out = resp.getWriter();	
		){
			mapper.writeValue(out, result);
		}
		
	}
}













