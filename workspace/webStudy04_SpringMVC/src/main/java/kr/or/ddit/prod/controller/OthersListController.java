package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.dao.OthersDAOImpl;
import kr.or.ddit.vo.BuyerVO;

@Controller
public class OthersListController{
	@Inject
	private OthersDAO othersDAO;
	
	@RequestMapping("/prod/getLprodList.do")
	public String lprodList(HttpServletRequest req){
		List<Map<String, Object>> lprodList = othersDAO.selectLprodList();
//		{lprodList:[
//			{lprodGu:"P101", lprodNm:"컴퓨터제품"}
//		]}
		req.setAttribute("lprodList", lprodList);
		return "forward:/toJSON";
	}
	
	@RequestMapping("/prod/getBuyerList.do")
	public String buyerList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
		
		return null;
	}
}
