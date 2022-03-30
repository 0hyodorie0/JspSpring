package kr.or.ddit.vo.fancytree;

import java.io.File;

public class FancytreeNodeAdapter implements FancytreeNode<File>{
	private File adaptee;
	private String key;
	
	public FancytreeNodeAdapter(File adaptee, String key) {
		this.adaptee = adaptee;
		this.key = key;		
	}
	
	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTitle() {
		return adaptee.getName();
	}

	@Override
	public File getData() {
		return adaptee;
	}

	@Override
	public boolean isFolder() {
		return adaptee.isDirectory();
	}

	@Override
	public boolean isLazy() {
		return isFolder();
	}

	@Override
	public int compareTo(FancytreeNode<File> o) {
		boolean my = this.isFolder(); //내가 폴더냐 아니냐 
		boolean you = o.isFolder(); //너가 폴더냐 아니냐 
		int order = -1;
		
		if(my ^ you) { //하나는 폴더 하나는  파일 
			order = my ? - 1 :  1 ; // 양수가 앞으로 가야 함 ... 			
		}else {//둘다 폴더 둘다 파일  : 이름으로 정렬 
			String myName = this.getTitle();
			String youName = o.getTitle();
			myName.compareTo(youName);
		
		}
		return order;
	}


}
