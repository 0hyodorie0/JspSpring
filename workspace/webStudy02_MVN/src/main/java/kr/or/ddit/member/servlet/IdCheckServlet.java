package kr.or.ddit.member.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.exception.PKNotFoundException;
import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.member.service.MemberServiceImpl;

@WebServlet("/member/idCheck.do")
public class IdCheckServlet extends HttpServlet{
	private MemberService service = new MemberServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String memId = req.getParameter("memId");
		if(StringUtils.isBlank(memId)) {
			resp.sendError(400, " 필수파라미터 누락 ");
			return;
		}
		boolean available = false;
		try {
			service.retrieveMember(memId);
			
		}catch (PKNotFoundException e) {
			available = true;
		}
		
		try(
			PrintWriter out = resp.getWriter();	
		){
			out.print(available);
		}
		
	}
}










