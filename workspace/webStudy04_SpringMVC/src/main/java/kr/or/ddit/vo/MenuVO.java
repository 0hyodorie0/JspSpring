package kr.or.ddit.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MenuVO {
	// <a href="menuURL" class="menuClass" id="menuId">menuText</a>
	
	public MenuVO(String menuText, String menuURL) {
		super();
		this.menuText = menuText;
		this.menuURL = menuURL;
	}
	private String menuText;
	private String menuURL;
	private String menuClass;
	private String menuId;
	
}
