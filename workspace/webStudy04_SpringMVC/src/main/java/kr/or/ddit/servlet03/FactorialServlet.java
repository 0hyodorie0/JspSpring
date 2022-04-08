package kr.or.ddit.servlet03;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/02/factorial.do")
public class FactorialServlet extends HttpServlet{
	private long factorial(int operand){
		if(operand < 0 ) throw new IllegalArgumentException("음수 피연산자는 처리 불가");
		else if(operand <= 1 )
			return operand;
		else
			return operand * factorial(operand - 1);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String goPage = "/WEB-INF/views/factorial.jsp";
		request.getRequestDispatcher(goPage).forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
 		String opStr = request.getParameter("operand");
	 	if(opStr==null || opStr.isEmpty() || !opStr.matches("\\d{1,2}")){
	 		response.sendError(HttpServletResponse.SC_BAD_REQUEST);	
	 		return;
	 	}
//		if(opStr!=null && !opStr.isEmpty() && opStr.matches("\\d{1,2}")){
//			operand = Integer.parseInt(opStr);
//		}
	  	String accept = request.getHeader("Accept");
	 	
	 	int operand = Integer.parseInt(opStr);
		long result = factorial(operand);
		Map<String, Object> map = new HashMap<>();
		map.put("result", result);
		map.put("prop", "value");
//		{"result":323432, "prop":"value"}
		if(accept.contains("json")) { // application/json
//			 Marshalling
			String pattern = "\"%s\":\"%s\",";
			StringBuffer json = new StringBuffer();
			json.append("{");
			for(Entry<String, Object> entry : map.entrySet()) {
				String key = entry.getKey();
				Object value = entry.getValue();
				json.append(String.format(pattern, key, value));
			}
			int lastIdx = json.lastIndexOf(",");
			json.deleteCharAt(lastIdx);
			json.append("}");
			response.setContentType("application/json;charset=UTF-8");
			try(
				PrintWriter out = response.getWriter();
			){		
				out.print(json);
			}
		}else {
			request.setAttribute("result", result);
			String goPage = "/WEB-INF/views/factorial.jsp";
			request.getRequestDispatcher(goPage).forward(request, response);
		}
	}
}










