package kr.or.ddit.mvc.fileupload;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * 파일이 업로드되는 파트 한개의 정보를 가진 객체의 추상모델.
 *
 */
public interface MultipartFile {
	 public byte[] getBytes() throws IOException;
	 public String getContentType();
	 public InputStream getInputStream() throws IOException;
	 public String getName();
	 public String getOriginalFilename();
	 public Object getResource();
	 public long getSize();
	 public boolean isEmpty();
	 public void transferTo(File dest) throws IOException;
}
