package kr.or.ddit.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SimpleSearchVO {
	private String searchType;
	private String searchWord;
}
