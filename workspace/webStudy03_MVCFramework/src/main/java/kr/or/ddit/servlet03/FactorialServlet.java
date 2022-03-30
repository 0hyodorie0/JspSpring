package kr.or.ddit.servlet03;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/02/factorial.do")
public class FactorialServlet extends HttpServlet{
	private long factorial(int operand){
		if(operand < 0) throw new IllegalArgumentException("음수 피연산자는 처리 불가");
		else if(operand <= 1)
			return operand;
		else
			return operand * factorial(operand - 1);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String goPage = "/WEB-INF/views/factorial.jsp";
		req.getRequestDispatcher(goPage).forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String opStr = req.getParameter("operand");
		 if(opStr==null || opStr.isEmpty() || !opStr.matches("\\d{1,2}")){
			 resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		} 
//		if(opStr!=null && !opStr.isEmpty() && opStr.matches("\\d{1,2}")){
//			operand = Integer.parseInt(opStr);
//		}
		 int operand = Integer.parseInt(opStr);
		long result = factorial(operand);
		req.setAttribute("result", result);
		
		String goPage = "/WEB-INF/views/factorial.jsp";
		req.getRequestDispatcher(goPage).forward(req, resp);
	}
}
