package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.vo.BuyerVO;

@Controller
public class OthersListController{
	@Inject
	private OthersDAO othersDAO;
	
	@RequestMapping("/prod/getLprodList.do")
	public String lprodList(Model model){
		List<Map<String, Object>> lprodList = othersDAO.selectLprodList();
//		{lprodList:[
//			{lprodGu:"P101", lprodNm:"컴퓨터제품"}
//		]}
		model.addAttribute("lprodList", lprodList);
		return "jsonView";
	}
	
	@RequestMapping(value="/prod/getBuyerList.do", produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<BuyerVO> buyerList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String lprodGu = req.getParameter("lprodGu");
		List<BuyerVO> buyerList = othersDAO.selectBuyerList(lprodGu);
//		[{buyerId:"P10101", buyerLgu:"P101", buyerName:"삼성전자"}]
		
		return buyerList;
	}
}

