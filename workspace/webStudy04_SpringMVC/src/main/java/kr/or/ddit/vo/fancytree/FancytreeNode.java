package kr.or.ddit.vo.fancytree;

public interface FancytreeNode<T> extends Comparable<FancytreeNode<T>>{
	public String getKey();
	public String getTitle();
	public T getData();
	public boolean isFolder();
	public boolean isLazy();
}
