package kr.or.ddit.mvc.fileupload;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.Part;

import org.apache.commons.lang3.StringUtils;

import oracle.net.aso.r;

/**
 * 멀티 파트 중 하나(Part/FileItem)의 정보를 MultipartFile 타입으로 캡슐화.
 *
 */
public class StandardMultipartHttpServletRequest extends HttpServletRequestWrapper {

	private Map<String, List<MultipartFile>> fileMap;
	
	public StandardMultipartHttpServletRequest(HttpServletRequest request) throws IOException, ServletException {
		super(request);
		parseRequest(request);
	}

	private void parseRequest(HttpServletRequest request) throws IOException, ServletException {
		fileMap = new LinkedHashMap<>();
		Collection<Part> parts = request.getParts();
		for(Part single : parts) {
			String partName = single.getName();
			String contentType = single.getContentType();
			if(StringUtils.isBlank(contentType)) continue;
			MultipartFile adapter = new StandaredServletMultipartFile(single);
			List<MultipartFile> already = fileMap.get(partName);
			if(already==null) {
				already = new ArrayList<>();
				fileMap.put(partName, already);
			}
			already.add(adapter);
		}
	}

	public Map<String, List<MultipartFile>> getFileMap() {
		return fileMap;
	}
	
	public MultipartFile getFile(String partName) {
		List<MultipartFile> files = fileMap.get(partName);
		if(files==null || files.isEmpty()) {
			return null;
		}else {
			return files.get(0);
		}
	}
	
	public List<MultipartFile> getFiles(String partName){
		return fileMap.get(partName);
	}
}











