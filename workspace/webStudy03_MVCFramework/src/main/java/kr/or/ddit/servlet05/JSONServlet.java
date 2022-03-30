package kr.or.ddit.servlet05;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet("/toJSON")
public class JSONServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		Map<String, Object> target = new HashMap<>();
		Enumeration<String> names = req.getAttributeNames();
		while (names.hasMoreElements()) {
			String key = (String) names.nextElement();
			Object value = req.getAttribute(key);
			target.put(key, value);
		}
		try(
			PrintWriter out = resp.getWriter();	
		){
			ObjectMapper mapper = new ObjectMapper();
			mapper.writeValue(out, target); 
		}
		
	}
}


















