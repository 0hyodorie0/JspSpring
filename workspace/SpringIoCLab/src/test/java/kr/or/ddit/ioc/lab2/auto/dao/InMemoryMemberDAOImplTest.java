package kr.or.ddit.ioc.lab2.auto.dao;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.ioc.lab2.auto.vo.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class) //spring---junit 연결
@ContextConfiguration("file:src/main/resources/kr/or/ddit/ioc/lab2/conf/Auto-DI.xml") 
public class InMemoryMemberDAOImplTest {
	
	@Autowired
	private MemberDAO dao;

	@Test
	public void testSelectMemberById() {
		String pk = "a001";
		MemberVO member = dao.selectMemberById(pk);
		assertNotNull(member); //member가 null이 아닌걸 예상해보겠다
	}
	
	@Test
	public void testSelectMemberByIdNotExist() {
		String pk = "a001";
		MemberVO member = dao.selectMemberById(pk);
		assertNull(member); 
	}

}
