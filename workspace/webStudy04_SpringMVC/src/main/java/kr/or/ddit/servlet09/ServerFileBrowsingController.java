package kr.or.ddit.servlet09;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.vo.fancytree.FancytreeNode;
import kr.or.ddit.vo.fancytree.FancytreeNodeAdapter;

@Controller
public class ServerFileBrowsingController{
	
	@RequestMapping("/server/browsing.do")
	public String browsing(
			@RequestParam(value="currentPath", required=false, defaultValue="/") String currentPath
			, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String accept = req.getHeader("Accept");
//		String currentPath = req.getParameter("currentPath");
//		if(StringUtils.isBlank(currentPath)) {
//			currentPath = "/";
//		}
		ServletContext application = req.getServletContext();
//		"/resources/images/chopa.jpg"
		if(StringUtils.containsIgnoreCase(accept, "json")) {
			Set<String> resourcePaths = application.getResourcePaths(currentPath);
			List<FancytreeNode<File>> dataList = new ArrayList<>();
			for(String path : resourcePaths) {
				String realPath = application.getRealPath(path);
				System.out.println(path);
				System.out.println(realPath);
				File adaptee = new File(realPath);
				dataList.add(new FancytreeNodeAdapter(adaptee, path));
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
			return "server/browsing";
		}
	}
	
}
