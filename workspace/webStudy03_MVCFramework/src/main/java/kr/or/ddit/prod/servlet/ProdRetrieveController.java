package kr.or.ddit.prod.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.dao.OthersDAOImpl;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;
import kr.or.ddit.vo.SimpleSearchVO;


@Controller
public class ProdRetrieveController  {
	private ProdService service = new ProdServiceImpl();
	private OthersDAO othersDAO = new OthersDAOImpl(); 
	
//	private void addAttibutes(HttpServletRequest req) {
//		req.setAttribute("lprodList", othersDAO.selectLprodList());
//		req.setAttribute("buyerList", othersDAO.selectBuyerList());
//	}
	
	@RequestMapping("/prod/prodList.do")
	public String prodList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		addAttibutes(req);
		String pageParam = req.getParameter("page");
		
//		String prodLgu = req.getParameter("prodLgu");
//		String prodBuyer = req.getParameter("prodBuyer");
//		String prodName = req.getParameter("prodName");
		ProdVO detailCondition = new ProdVO();
		req.setAttribute("detailCondition", detailCondition);
		
		
		int currentPage = 1;
		if(StringUtils.isNumeric(pageParam)) {
			currentPage = Integer.parseInt(pageParam);
		}
		
		PagingVO<ProdVO> paging = new PagingVO<>(3, 2);
		paging.setCurrentPage(currentPage);
		paging.setDetailCondition(detailCondition);
		
		service.retrieveProdList(paging);
		
		req.setAttribute("paging", paging);
		String viewName = "prod/prodList";
		return viewName;
	}
	
	@RequestMapping("/prod/prodView.do")
	public String view(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String prodId = req.getParameter("what");
		if(StringUtils.isBlank(prodId)) {
			resp.sendError(400, "필수 파라미터 누락");
			return null;
		}
		
		ProdVO prod = service.retrieveProd(prodId);
		req.setAttribute("prod", prod);
		
		return "prod/prodView";
	}
	
	
}
