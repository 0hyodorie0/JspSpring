package kr.or.ddit.prod.preparer;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;

import kr.or.ddit.commons.annotations.ViewPreparerBean;
import kr.or.ddit.vo.MenuVO;

@ViewPreparerBean
public class ProdViewPreparer implements ViewPreparer{

	@Override
	public void execute(Request req, AttributeContext context) {
		MenuVO menu1 = new MenuVO("상품목록조회", "/prod/prodList.do");
		MenuVO menu2 = new MenuVO("상품등록", "/prod/prodInsert.do");
		List<MenuVO> menuList = Arrays.asList(menu1, menu2);
		
		Map<String, Object> requestScope = req.getContext(Request.REQUEST_SCOPE);
		requestScope.put("menuList", menuList);
	}

}











