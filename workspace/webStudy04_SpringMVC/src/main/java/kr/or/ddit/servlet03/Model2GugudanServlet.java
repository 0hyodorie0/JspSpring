package kr.or.ddit.servlet03;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/model2/gugudan.do")
public class Model2GugudanServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Calendar now = Calendar.getInstance();
		
		StringBuffer gugudan = new StringBuffer();
		for(int dan = 2; dan <= 9; dan++) {
			gugudan.append("<tr>\n");
			for(int mul = 1; mul <= 9; mul++) {
				gugudan.append(String.format("<td>%d*%d=%d</td>\n", dan, mul, dan*mul));
			}
			gugudan.append("</tr>\n");
		}
		
		req.setAttribute("now", String.format("%tc", now));
		req.setAttribute("gugudan", gugudan);
		String goPage = "/WEB-INF/views/gugudan.jsp";
		req.getRequestDispatcher(goPage).forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String minStr = req.getParameter("minDan");
		String maxStr = req.getParameter("maxDan");
		if(minStr==null || maxStr==null 
				|| !minStr.matches("[2-9]") ||  !maxStr.matches("[2-9]") ) {
			resp.sendError(400);
			return;
		}
		int minDan = Integer.parseInt(minStr);
		int maxDan = Integer.parseInt(maxStr);
		StringBuffer gugudan = new StringBuffer();
		for(int dan = minDan; dan <= maxDan; dan++) {
			gugudan.append("<tr>\n");
			for(int mul = 1; mul <= 9; mul++) {
				gugudan.append(String.format("<td>%d*%d=%d</td>\n", dan, mul, dan*mul));
			}
			gugudan.append("</tr>\n");
		}
		
		try(
			PrintWriter out = resp.getWriter();
		){
			out.print(gugudan);
		}
	}
}











