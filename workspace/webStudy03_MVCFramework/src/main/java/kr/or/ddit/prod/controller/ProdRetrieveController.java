package kr.or.ddit.prod.controller;

import javax.servlet.http.HttpServletRequest;

import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

@Controller
public class ProdRetrieveController{
	private ProdService service = new ProdServiceImpl();
	
	@RequestMapping("/prod/prodList.do")
	public String prodList(
			@RequestParam(value="page", required=false, defaultValue="1") int[] currentPage
			, @RequestParam(value="prodLgu", required=false) String prodLgu
			, @RequestParam(value="prodBuyer", required=false) String prodBuyer
			, @RequestParam(value="prodName", required=false) String prodName
			, HttpServletRequest req
	){
		
		ProdVO detailCondition = new ProdVO();
		req.setAttribute("detailCondition", detailCondition);
		detailCondition.setProdLgu(prodLgu);
		detailCondition.setProdBuyer(prodBuyer);
		detailCondition.setProdName(prodName);
		
		PagingVO<ProdVO> paging = new PagingVO<>(3, 2);
		paging.setCurrentPage(currentPage[0]);
		paging.setDetailCondition(detailCondition);
		
		service.retrieveProdList(paging);
		
		req.setAttribute("paging", paging);
		return "prod/prodList";
	}
	
	@RequestMapping("/prod/prodView.do")
	public String view(@RequestParam("what") String prodId, HttpServletRequest req){
		ProdVO prod = service.retrieveProd(prodId);
		req.setAttribute("prod", prod);
		return "prod/prodView";
	}
	
}
















