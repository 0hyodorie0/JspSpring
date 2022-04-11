package kr.or.ddit.fileupload;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FileController {

   private String saveFolderPath = "D:\\saveFolder";// 저장 폴더 명 
   
   @RequestMapping("/12/fileDownLoad.do")
   public String fileDownload(@RequestParam("file") String file,
         HttpServletResponse resp,
         HttpSession session) throws IOException {
      
      Map<String, Object>fileMetaData = (Map)session.getAttribute("fileMetaData");
      
      
      
      File saveFolder = new File(saveFolderPath);
      File savefile = new File(saveFolder,file);
      //원본파일명이 잘못되었을수도 있고 파일이 없을수도있는 조건이 있어야 함       
      if(!savefile.exists()) {//파일 존재 하지 않음,  notFound 
         resp.sendError(404, file +"에 해당하는파일이 없음.");
         return null;
      }
      String fileName = (String)fileMetaData.get("originalFilename");
      fileName = URLEncoder.encode(fileName, "UTF-8");
      fileName = fileName.replace("+", " ");
      resp.setHeader("Content-Disposition", "attatchment;filename=\""+fileName);
      resp.setHeader("Content-Length", savefile.length()+"");
      try(
         OutputStream os = resp.getOutputStream();    
       ){
          FileUtils.copyFile(savefile, os);
          return null;
       }      
       
   }
}
