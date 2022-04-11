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
		return key;
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
		boolean my = this.isFolder();
		boolean you = o.isFolder();
		int order = -1;
		if(my ^ you) {
			order = my ? -1 : 1; 
		}else {
			String myName = this.getTitle();
			String youName = o.getTitle();
			order = myName.compareTo(youName);
		}
		return order;
	}

}
