package kr.or.ddit.sample.vo;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

public class MemberVO {
	private String memId;
	private String memName;
	private String memBir;
	
	private MultipartFile memImage;
	
	private String memImg;
	
	
	public MultipartFile getMemImage() {
		return memImage;
	}
	public void setMemImage(MultipartFile memImage) {
		if(memImage==null || memImage.isEmpty()) return;
		
		this.memImage = memImage;
		this.memImg = UUID.randomUUID().toString();
		
			
	}
	public void saveTo(File saveFolder) throws  IOException {
		if(memImage==null || memImage.isEmpty()) return;
		memImage.transferTo(new File(saveFolder,memImg));
	}
	
	public String getMemImg() {
		return memImg;
	}
	public void setMemImg(String memImg) {
		this.memImg = memImg;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public String getMemBir() {
		return memBir;
	}
	public void setMemBir(String memBir) {
		this.memBir = memBir;
	}
	@Override
	public String toString() {
		return "MemberVO [memId=" + memId + ", memName=" + memName + ", memBir=" + memBir + "]";
	}
	
}
