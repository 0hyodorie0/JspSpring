package kr.or.ddit.employee.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.employee.dao.EmployeeDAO;
import kr.or.ddit.employee.dao.EmployeeDAOImpl;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.vo.EmployeeVO;
import kr.or.ddit.vo.fancytree.FancytreeNode;
import kr.or.ddit.vo.fancytree.FancytreeNodeEmployeeWrapper;

@Controller
public class EmployeeTreeController {
	
	private EmployeeDAO empDAO = new EmployeeDAOImpl();
	
	@RequestMapping("/employee/treeView.do")
	public String browsing(
			@RequestParam(value="managerId", required=false, defaultValue="") String managerId
			, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String accept = req.getHeader("Accept");
		
		List<EmployeeVO> adapteeList = null; 
		if(StringUtils.isBlank(managerId)) {
			adapteeList = empDAO.selectParents();
			
		}else {
			adapteeList = empDAO.selectChildren(managerId); 
		}
		
		if(StringUtils.containsIgnoreCase(accept, "json")) {
			List<FancytreeNode<EmployeeVO>> dataList = new ArrayList<>();
			for(EmployeeVO adaptee : adapteeList) {
				dataList.add(new FancytreeNodeEmployeeWrapper(adaptee));
			}
			
			Collections.sort(dataList);
			
			try(
				PrintWriter out = resp.getWriter();	
			){
				ObjectMapper mapper = new ObjectMapper();
				mapper.writeValue(out, dataList);
			}
			return null;
		}else {
			return "employee/treeView";
		}
	}
}
