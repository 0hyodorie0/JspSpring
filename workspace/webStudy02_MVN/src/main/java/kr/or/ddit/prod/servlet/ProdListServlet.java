package kr.or.ddit.prod.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;

@WebServlet("/prod/prodList.do")
public class ProdListServlet extends HttpServlet{
	
	private ProdService service = new ProdServiceImpl();
	private OthersDAO othersDAO;
	
	private void addAttibutes(HttpServletRequest req) {
		req.setAttribute("lprodList", othersDAO.selectLprodList());
		req.setAttribute("buyerList", othersDAO.selectBuyerList());
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		addAttibutes(req);
		String pageParam = req.getParameter("page");
		
//		String prodLog = req.getParameter("prodLog");
//		String prodBuyer = req.getParameter("prodBuyer");
//		String prodName = req.getParameter("prodName");
		
		ProdVO detailCondition = new ProdVO();
		req.setAttribute("detailCondition", detailCondition);
		try {
			BeanUtils.populate(detailCondition, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int currentPage = 1;
		if(StringUtils.isNumeric(pageParam)) {
			currentPage = Integer.parseInt(pageParam);
		}
		
		PagingVO<ProdVO> paging = new PagingVO<>(10,5);
		paging.setCurrentPage(currentPage);
		paging.setDetailCondition(detailCondition);
		
		service.retrieveProdList(paging);
		
		req.setAttribute("paging", paging);
		String viewName = "prod/prodList";
		viewResolve(viewName, req, resp);
		
	}
	
	
	private void viewResolve(String viewName, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//5. 뷰로 이동.
		if(viewName.startsWith("redirect:")) {
			viewName = viewName.substring("redirect:".length());
			resp.sendRedirect(req.getContextPath() + viewName);
		}else {
			String prefix ="/WEB-INF/views/";
			String suffix = ".jsp";
			req.getRequestDispatcher(prefix + viewName + suffix).forward(req, resp);
		}
	}
}
