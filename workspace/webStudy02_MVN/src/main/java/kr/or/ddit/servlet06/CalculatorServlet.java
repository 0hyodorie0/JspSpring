package kr.or.ddit.servlet06;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.enumpkg.OperatorType;

@WebServlet("/06/calculate")
public class CalculatorServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String viewName = "calForm";
		viewResolve(viewName, req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String acceept = req.getHeader("Accept");
		String left =  req.getParameter("leftOp");
		String opParam = req.getParameter("operator");
		String right = req.getParameter("rightOp");
		
		if(!StringUtils.isNumeric(left) || !StringUtils.isNumeric(right)) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "피연산자 오류");
			return;
		}
		try {
			OperatorType operator = OperatorType.valueOf(opParam);
			int leftOp = Integer.parseInt(left);
			int rightOp = Integer.parseInt(right);
			long result = operator.operate(leftOp, rightOp);
			String expression = operator.expression(leftOp, rightOp);
			
			if(acceept.contains("json")) {
				req.setAttribute("expression", expression);
				viewResolve("forward:/toJSON", req, resp);
			}else {
				try(
						PrintWriter out = resp.getWriter();
						){
					out.print(expression);
				}				
			}
		}catch(Exception e) {
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "연산자 오류");
			return;
		}
	}
	
	private void viewResolve(String viewName, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//5. 뷰로 이동.
		if(viewName.startsWith("redirect:")) {
			viewName = viewName.substring("redirect:".length());
			resp.sendRedirect(req.getContextPath() + viewName);
		}else if(viewName.startsWith("forward:")) {
			viewName = viewName.substring("forward:".length());
			req.getRequestDispatcher(viewName).forward(req, resp);
		}else {
			String prefix ="/WEB-INF/views/";
			String suffix = ".jsp";
			req.getRequestDispatcher(prefix + viewName + suffix).forward(req, resp);
		}
		
	}
}
