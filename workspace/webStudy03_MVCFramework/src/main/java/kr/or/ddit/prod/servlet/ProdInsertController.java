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
import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.dao.OthersDAOImpl;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.utils.ValidatorUtils;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.vo.ProdVO;


@Controller
public class ProdInsertController {
	
	private ProdService service = new ProdServiceImpl();
	private OthersDAO othersDAO = new OthersDAOImpl();
	
	
  private void addAttributes(HttpServletRequest req) {
	  req.setAttribute("lprodList", othersDAO.selectLprodList());
	  								/*null값으로 입력 하여서 마이바티스로감 ! */
	  req.setAttribute("buyerList", othersDAO.selectBuyerList(null));
}
	
	
	
	@RequestMapping("/prod/prodInsert.do")
	public String form(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {		
		addAttributes(req);
		return "prod/prodForm";	
	}
	
	
	
	
	@RequestMapping(value="/prod/prodInsert.do", method=RequestMethod.POST)
	public String process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
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
		return viewName;
	}
	
	
	
}
