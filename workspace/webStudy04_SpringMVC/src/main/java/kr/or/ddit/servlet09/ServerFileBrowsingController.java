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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.vo.fancytree.FancytreeNode;
import kr.or.ddit.vo.fancytree.FancytreeNodeAdapter;

//@WebServlet(value="/server/browsing.do", loadOnStartup=1)
@Controller
public class ServerFileBrowsingController {
 
 
 @RequestMapping("/server/browsing.do")
 public String browsing(
          @RequestParam(value="currentPath", required=false, defaultValue="/") String currentPath
          , HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    String accept = req.getHeader("accept");
    ServletContext application = req.getServletContext();
    String viewName = "";
//    /resources/images/chopa.jpg
    if(StringUtils.containsIgnoreCase(accept, "json")) {
       Set<String> resourcePaths = application.getResourcePaths(StringUtils.isNotBlank(currentPath) ? currentPath : "/");
       List<FancytreeNode<File>> dataList = new ArrayList<>();
       for(String path : resourcePaths) {
          String realPath = application.getRealPath(path);    //context경로뒤에 path를 붙여준다.
//       System.out.println(path);
          System.out.println(realPath);
          File adaptee = new File(realPath);
          dataList.add(new FancytreeNodeAdapter(adaptee, path));
       }
       
       Collections.sort(dataList);
       
       // 마샬링과 직렬화를 해야함.
       try(
          PrintWriter out = resp.getWriter();
       ){
          //마샬링. write계열
          ObjectMapper mapper = new ObjectMapper();
          mapper.writeValue(out, dataList);            
       }
       return null;
    }else {
       viewName = "server/browsing";
    }
    
    return viewName;
 }
 
 
}