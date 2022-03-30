package kr.or.ddit.vo;

import java.util.List;

import org.apache.logging.log4j.message.StringFormattedMessage;

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
	private int screenSize = 10;
	private int blockSize = 5;
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
		totalPage = (totalRecord+(screenSize-1))/screenSize;
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
		endRow = currentPage * screenSize;
		startRow = endRow - (screenSize -1);

		endPage = (currentPage+(blockSize-1))/blockSize * blockSize;
		startPage = endPage - (blockSize -1);
	}
	
	

	private static final String UL = "<nav aria-label='Page navigation example'><ul class='pagination'>";
	private static final String PTRN = " <li class='page-item'><a class='page-link' href='#' data-page='%d'>%s</a></li>";
	private static final String UL2 = "</ul></nav>";
	public String getPagingHTML() {
		StringBuffer html = new StringBuffer();
		html.append(UL);
		if(startPage > blockSize) {
			html.append(
				String.format(PTRN, (startPage - blockSize),"이전")
			);
			
		}
		if(endPage > totalPage) {
			endPage = totalPage;
		}
		for(int i = startPage; i<=endPage; i++) {
			html.append(
				String.format(PTRN, i,i)
			);
		}
		if(endPage < totalPage) {
			html.append(
				String.format(PTRN, (endPage + 1),"다음")
			);
		}
		html.append(UL2);
		return html.toString();
	}
}
