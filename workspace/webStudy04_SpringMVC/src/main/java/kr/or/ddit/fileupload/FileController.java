package kr.or.ddit.fileupload;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.FileUtils;

import kr.or.ddit.mvc.annotation.RequestMethod;
import kr.or.ddit.mvc.annotation.resolvers.RequestParam;
import kr.or.ddit.mvc.annotation.stereotype.Controller;
import kr.or.ddit.mvc.annotation.stereotype.RequestMapping;
import kr.or.ddit.mvc.fileupload.MultipartFile;
import kr.or.ddit.mvc.fileupload.StandardMultipartHttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class FileController {
	private String saveFolderPath = "D:\\saveFolder";
	
	@RequestMapping(value="/12/fileUpload_Framework.do", method=RequestMethod.POST)
	public String fileUpload(
		@RequestParam("uploader") String uploader
		, HttpServletRequest req
		, HttpSession session
	) throws IOException {
//		uploader, uploadFile
		session.setAttribute("uploader", uploader);
		if(req instanceof StandardMultipartHttpServletRequest) {
			Map<String, List<MultipartFile>> fileMap =((StandardMultipartHttpServletRequest) req).getFileMap();
			log.info("fileMap : {}", fileMap);
			MultipartFile uploadFile = 
					((StandardMultipartHttpServletRequest) req).getFile("uploadFile");
			Map<String, Object> fileMetaData = fileUpload(uploadFile);
			log.info("file meta data : {}", fileMetaData);
			session.setAttribute("uploadFile", fileMetaData);
		}
		return "redirect:/12/fileUploadForm.jsp";
	}
	
	@RequestMapping("/12/fileDownload.do")
	public String fileDownload(
		@RequestParam("file") String file
		, HttpServletResponse resp
		, HttpSession session
	)throws IOException {
		Map<String, Object> fileMetaData = 
				(Map) session.getAttribute("uploadFile");
		File saveFolder = new File(saveFolderPath);
		File saveFile = new File(saveFolder, file);
		if(!saveFile.exists()) {
			resp.sendError(404, file+"에 해당하는 파일이 없음.");
			return null;
		}
		String fileName = (String) fileMetaData.get("originalFilename");
		fileName = URLEncoder.encode(fileName, "UTF-8");
		fileName = fileName.replace("+", " ");
		resp.setHeader("Content-Disposition", "attatchment;filename=\""+fileName+"\"");
		resp.setHeader("Content-Length", saveFile.length()+"");
		try(
			OutputStream os = resp.getOutputStream();	
		){
			FileUtils.copyFile(saveFile, os);
			return null;
		}
	}
	
	private Map<String, Object> fileUpload(MultipartFile uploadFilePart) throws IOException {
		Map<String, Object> metaData = new HashMap<>();
		String originalFilename = uploadFilePart.getOriginalFilename();
		File saveFolder = new File(saveFolderPath);
		String saveName = UUID.randomUUID().toString();
		File saveFile = new File(saveFolder, saveName);
		long fileSize = uploadFilePart.getSize();
		String fileMime = uploadFilePart.getContentType();
		metaData.put("originalFilename", originalFilename);
		metaData.put("saveName", saveName);
		metaData.put("fileSize", fileSize);
		metaData.put("fileMime", fileMime);
		
		uploadFilePart.transferTo(saveFile);
		
		return metaData;
	}
}














