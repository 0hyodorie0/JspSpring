package kr.or.ddit.servlet10;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileUploadServletVersion2 extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		DiskFileItemFactory fileItemFactory =
				new DiskFileItemFactory(10, new File("d:/uploadTemp"));
		ServletFileUpload uploadHandler = new ServletFileUpload(fileItemFactory);
		try {
			List<FileItem> items = uploadHandler.parseRequest(req);
			for(FileItem item : items) {
				String partName = item.getFieldName();
				if(item.isFormField()) {
					String partValue = item.getString(req.getCharacterEncoding());
					log.info("{} : {}", partName, partValue);
					session.setAttribute(partName, partValue);
				}else {
					Map<String, Object> fileMetaData = fileUpload(item);
					log.info("{} : {}", partName, fileMetaData);
					session.setAttribute(partName, fileMetaData);
				}
			}
			resp.sendRedirect(req.getContextPath() + "/12/fileUploadForm.jsp");
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private String saveFolderPath = "D:\\saveFolder";
	
	private Map<String, Object> fileUpload(FileItem uploadFilePart) throws IOException {
		Map<String, Object> metaData = new HashMap<>();
		String originalFilename = uploadFilePart.getName();
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














