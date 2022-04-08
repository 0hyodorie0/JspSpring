package kr.or.ddit.servlet10;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import lombok.extern.slf4j.Slf4j;

@WebServlet("/12/fileUpload_3.do")
@MultipartConfig(location="D:\\uploadTemp")
@Slf4j
public class FileUploadServletVersion3 extends HttpServlet{
	private String saveFolderPath = "D:\\saveFolder";
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		// uploader, uploadFile
		String uploader = req.getParameter("uploader");
//		String uploadFile = req.getParameter("uploadFile");
		
		HttpSession session = req.getSession();
		Collection<Part> items = req.getParts();
		for(Part item : items) {
			String partName = item.getName();
			String fileMime = item.getContentType();
			if(fileMime==null) {
				String partValue = req.getParameter(partName);
				log.info("{} : {}", partName, partValue);
				session.setAttribute(partName, partValue);				
			}else {
				 Map<String, Object> fileMetaData = fileUpload(item);
				 log.info("{} : {}", partName, fileMetaData);
				 session.setAttribute(partName, fileMetaData);	
			}
		}
		
		resp.sendRedirect(req.getContextPath() + "/12/fileUploadForm.jsp");
	}

	private Map<String, Object> fileUpload(Part uploadFilePart) throws IOException {
		Map<String, Object> metaData = new HashMap<>();
		String originalFilename = uploadFilePart.getSubmittedFileName();
		File saveFolder = new File(saveFolderPath);
		String saveName = UUID.randomUUID().toString();
		File saveFile = new File(saveFolder, saveName);
		long fileSize = uploadFilePart.getSize();
		String fileMime = uploadFilePart.getContentType();
		metaData.put("originalFilename", originalFilename);
		metaData.put("saveName", saveName);
		metaData.put("fileSize", fileSize);
		metaData.put("fileMime", fileMime);
		try(
			InputStream is = uploadFilePart.getInputStream();
//			FileOutputStream fos = new FileOutputStream(saveFile);
		){
//			IOUtils.copy(is, fos);
			FileUtils.copyInputStreamToFile(is, saveFile);
			metaData.put("saveFilePath", saveFile.getAbsolutePath());
		}
		return metaData;
	}
}























