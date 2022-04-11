package kr.or.ddit.member.preparer;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;
import org.springframework.stereotype.Component;

import kr.or.ddit.commons.annotations.ViewPreparerBean;
import kr.or.ddit.vo.MenuVO;

@ViewPreparerBean
public class MemberViewPreparer implements ViewPreparer{

	@Override
	public void execute(Request req, AttributeContext context) {
		// 회원관리용 뷰레이어 이전의 사전 처리.
		MenuVO menu1 = new MenuVO("회원목록조회", "/member/memberList.do");
		MenuVO menu2 = new MenuVO("회원등록", "/member/memberInsert.do");
		List<MenuVO> menuList = Arrays.asList(menu1, menu2);
		
		Map<String, Object> requestScope = req.getContext(Request.REQUEST_SCOPE);
		requestScope.put("menuList", menuList);
	}

}











