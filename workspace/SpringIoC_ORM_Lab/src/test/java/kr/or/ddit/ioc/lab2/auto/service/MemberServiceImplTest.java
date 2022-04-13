package kr.or.ddit.ioc.lab2.auto.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.ioc.lab2.auto.vo.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/resources/kr/or/ddit/ioc/lab2/conf/Auto-DI.xml")
public class MemberServiceImplTest {

	@Autowired
	private MemberService service;
	
	@Test
	public void testRetrieveMember() {
		// 조건
		String memId = "a001";
		//실행
		MemberVO member = service.retrieveMember(memId);
		//결과 예측
		assertNotNull(member);
		assertEquals("콩순이", member.getMemName());
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testNotExist() {
		//조건
		String memId = "c001";
		//실행
		MemberVO member = service.retrieveMember(memId);
		//결과예측
		
	}

}
