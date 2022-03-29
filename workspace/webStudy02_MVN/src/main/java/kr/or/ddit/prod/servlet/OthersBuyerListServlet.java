package kr.or.ddit.prod.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.dao.OthersDAOImpl;
import kr.or.ddit.vo.BuyerVO;

@WebServlet("/prod/getBuyerList.do")
public class OthersBuyerListServlet extends HttpServlet {
	private OthersDAO othersDAO = new OthersDAOImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String lprodGu = req.getParameter("lprodGu");
		List<BuyerVO> buyerList = othersDAO.selectBuyerList(lprodGu);
//		[{buyerId:"P10101", buyerLgu:"P101", buyerName:"삼성전자"}]
		
		resp.setContentType("application/json;charset=UTF-8");
		ObjectMapper mapper = new ObjectMapper();
		try(
			PrintWriter out = resp.getWriter();	
		){
			mapper.writeValue(out, buyerList);
		}
	}
}





















