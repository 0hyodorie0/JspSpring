package kr.or.ddit.servlet08;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.dbprop.service.DataBasePropertyService;
import kr.or.ddit.dbprop.service.DataBasePropertyServiceImpl;
import kr.or.ddit.vo.DataBasePropertyVO;

/**
 * DataBase_Properties 정보를 조회하기 위한 controller layer
 *
 */
@WebServlet("/09/jdbcDesc.do")
public class DataBasePropertyServlet extends HttpServlet{
	
	private DataBasePropertyService service = new DataBasePropertyServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Map<String, Object> pMap = new HashMap<>();
		
		List<DataBasePropertyVO> list = service.retrieveDataBasePropertyList(pMap);
		
		String[] headers = (String[]) pMap.get("headers");
		req.setAttribute("headers", headers);
		req.setAttribute("dataList", list);
		
		String viewName = "09/jdbcDesc";
		viewResolve(viewName, req, resp);		
	}
	
	private void viewResolve(String viewName, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//5. 뷰로 이동.
		if(viewName.startsWith("redirect:")) {
			viewName = viewName.substring("redirect:".length());
			resp.sendRedirect(req.getContextPath() + viewName);
		}else if(viewName.startsWith("forward:")) {
			viewName = viewName.substring("forward:".length());
			req.getRequestDispatcher(viewName).forward(req, resp);
		}else {
			String prefix ="/WEB-INF/views/";
			String suffix = ".jsp";
			req.getRequestDispatcher(prefix + viewName + suffix).forward(req, resp);
		}
		
	}
}
















