package kr.or.ddit.ioc.lab2.auto.controller;

import static org.junit.Assert.*;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringRunner;

import kr.or.ddit.ioc.lab2.auto.vo.MemberVO;

@RunWith(SpringRunner.class)
@ContextHierarchy({
	@ContextConfiguration("file:src/main/resources/kr/or/ddit/ioc/lab2/conf/*.xml")
	,
	@ContextConfiguration("file:src/main/resources/kr/or/ddit/ioc/lab2/conf/child/Child-Context.xml")
})
public class MemberRetrieveControllerTest {
	
//	@Resource(name="memberRetrieveController")
	@Inject
	private MemberRetrieveController controller;

	@Test
	public void test() {
		//조건
		String memId = "a001";
		//실행
		MemberVO member = controller.readMemberDetail(memId);
		//결과예측
		assertNotNull(member);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testBadRequest() {
		//조건
		String memId = null; //누락시
		//실행
		controller.readMemberDetail(memId);
		//결과예측
	}

}
