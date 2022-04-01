package kr.or.ddit.vo;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 페이징 처리를 위한 프로퍼티를 가진 VO
 *
 */
@Getter
@NoArgsConstructor
public class PagingVO<T> {
	
	public PagingVO(int screenSize, int blockSize) {
		super();
		this.screenSize = screenSize;
		this.blockSize = blockSize;
	}

	private int totalRecord;
	private int screenSize=10;
	private int blockSize=5;
	private int currentPage;
	
	private int totalPage;
	private int startRow;
	private int endRow;
	private int startPage;
	private int endPage;
	
	private T detailCondition;
	public void setDetailCondition(T detailCondition) {
		this.detailCondition = detailCondition;
	}
	
	private SimpleSearchVO simpleCondition;
	public void setSimpleCondition(SimpleSearchVO simpleCondition) {
		this.simpleCondition = simpleCondition;
	}
	
	private List<T> dataList;
	
	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}
	
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
		totalPage = (totalRecord+(screenSize-1)) / screenSize;
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
		
		endRow = currentPage * screenSize;
		startRow = endRow - (screenSize - 1);

		endPage = (currentPage+(blockSize-1))/blockSize * blockSize;
		startPage = endPage - (blockSize - 1);
	}
	
	private static final String PTRN = "<a href='?page=%d'>%s</a>";

	public String getPagingHTML() {
		StringBuffer html = new StringBuffer();
		if(startPage > blockSize) {
			html.append(
				String.format(PTRN, (startPage - blockSize), "이전")
			);
		}
		if(endPage>totalPage) {
			endPage = totalPage;
		}
		for(int i=startPage; i<=endPage; i++) {
			html.append(
				String.format(PTRN, i, i)	
			);
		}
		if(endPage<totalPage) {
			html.append(
				String.format(PTRN, (endPage + 1), "다음")
			);
		}
		return html.toString();
	}
	
	
	private static final String BSPTRN = "<li class='page-item %s'>"
			+ "<a class='page-link' href='#' data-page='%d'>%s</a>"
			+ "</li>";

//    <li class='page-item disabled'>
//      <span class='page-link'>Previous</span>
//    </li>
//    <li class='page-item'><a class='page-link' href='#'>1</a></li>
//    <li class='page-item active' aria-current='page'>
//      <span class='page-link'>2</span>
//    </li>
//    <li class='page-item'><a class='page-link' href='#'>3</a></li>
//    <li class='page-item'>
//      <a class='page-link' href='#'>Next</a>
//    </li>
	public String getPagingHTMLBS() {
		StringBuffer html = new StringBuffer();
		html.append("<nav aria-label='...'>");
		html.append("<ul class='pagination'>");
		String activeOrDisable = null;
		if(startPage > blockSize) {
			activeOrDisable = "";
		}else {
			activeOrDisable = "disabled";
			
		}
		html.append(
			String.format(BSPTRN, activeOrDisable, (startPage - blockSize), "이전")
		);
		if(endPage>totalPage) {
			endPage = totalPage;
		}
		for(int i=startPage; i<=endPage; i++) {
			if(i==currentPage) {
				activeOrDisable = "active";
			}else {
				activeOrDisable = "";
			}
			html.append(
				String.format(BSPTRN, activeOrDisable, i, i)	
			);
		}
		if(endPage<totalPage) {
			activeOrDisable = "";
		}else {
			activeOrDisable = "disabled";
		}
		html.append(
			String.format(BSPTRN, activeOrDisable, (endPage + 1), "다음")
		);
		html.append("</ul>");
		html.append("</nav>");
		return html.toString();
	}
}





























