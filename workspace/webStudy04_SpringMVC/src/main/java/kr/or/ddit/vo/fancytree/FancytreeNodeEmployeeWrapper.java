package kr.or.ddit.vo.fancytree;

import kr.or.ddit.vo.EmployeeVO;

public class FancytreeNodeEmployeeWrapper implements FancytreeNode<EmployeeVO>{
	private EmployeeVO adaptee;
	
	public FancytreeNodeEmployeeWrapper(EmployeeVO adaptee) {
		super();
		this.adaptee = adaptee;
	}

	// 연봉 순서로 정렬하려면???
	@Override
	public int compareTo(FancytreeNode<EmployeeVO> o) {
		
		return 0;
	}

	@Override
	public String getKey() {
		return adaptee.getEmployeeId()+"";
	}

	@Override
	public String getTitle() {
		return adaptee.getEmpName();
	}

	@Override
	public EmployeeVO getData() {
		return adaptee;
	}

	@Override
	public boolean isFolder() {
		return adaptee.getChlidCnt()>0;
	}

	@Override
	public boolean isLazy() {
		return isFolder();
	}

}
