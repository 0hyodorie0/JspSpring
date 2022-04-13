package kr.or.ddit.servlet05;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/05/serverTime")
public class ServerTimeServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		resp.setContentType("text/plain");
//		resp.setContentType("application/json");
//		resp.setIntHeader("Refresh", 1);
		Date now = new Date();
		req.setAttribute("time", now);
		req.getRequestDispatcher("/toJSON").forward(req, resp);
//		try(
//			PrintWriter out = resp.getWriter();	
//		){
//			out.print(now);
//		}
	}
}
