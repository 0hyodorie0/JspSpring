package kr.or.ddit.prod.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.dao.OthersDAOImpl;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.utils.ValidatorUtils;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.vo.ProdVO;

@WebServlet("/prod/prodInsert.do")
public class ProdInsertServlet extends HttpServlet{
	
	private ProdService service = new ProdServiceImpl();
	private OthersDAO othersDAO = new OthersDAOImpl();
	
	
  private void addAttributes(HttpServletRequest req) {
	  req.setAttribute("lprodList", othersDAO.selectLprodList());
	  								/*null값으로 입력 하여서 마이바티스로감 ! */
	  req.setAttribute("buyerList", othersDAO.selectBuyerList(null));
}
	
	@Override /*중복되는 작업 ! 나중에 빼면은 프론트컨트롤러임 */
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		super.service(req, resp);
	}
	
	
	@Override/* UI 로 이동 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {		
		addAttributes(req);
		String viewName="prod/prodForm";		
		viewResolve(viewName, req, resp);
	}
	
	
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		addAttributes(req);
		
		ProdVO prod = new ProdVO();//command object , model ! 
		req.setAttribute("prod", prod);
		
		try {
			BeanUtils.populate(prod, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new ServletException(e);
		}
		
		
		Map<String, List<String>> errors = new LinkedHashMap<>();
		req.setAttribute("errors", errors);
				
		boolean valid = new ValidatorUtils<ProdVO>().validate(prod,errors, InsertGroup.class);
		
		String viewName = null;	
		
		if(valid) {
			ServiceResult result = service.createProd(prod);
			if(ServiceResult.OK.equals(result)) {
				String ProdId = prod.getProdId();
				viewName = "redirect:/prod/prodView.do?what="+ProdId;
			}else {
				req.setAttribute("message", "서버 오류 !  잠시후 다시 시도하세요");
				viewName = "prod/prodForm";
			}
		}else {
			viewName = "prod/prodForm";
		}
		viewResolve(viewName, req, resp);
	}
	
	private void viewResolve(String viewName, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if (viewName.startsWith("redirect:")) {
			viewName = viewName.substring("redirect:".length());
			resp.sendRedirect(req.getContextPath() + viewName);
		} else if (viewName.startsWith("forward:")) {
			viewName = viewName.substring("forward:".length());
			req.getRequestDispatcher(viewName).forward(req, resp);
		} else {
			String prefix = "/WEB-INF/views/";
			String suffix = ".jsp";
			// 5. 뷰로 이동
			req.getRequestDispatcher(prefix + viewName + suffix).forward(req, resp);
		}
	}
	
}
