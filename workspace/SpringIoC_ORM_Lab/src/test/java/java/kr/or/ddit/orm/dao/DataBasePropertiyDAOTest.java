package java.kr.or.ddit.orm.dao;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.kr.or.ddit.orm.vo.DataBasePropertyVO;
import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/resources/kr/or/ddit/orm/conf/*-context.xml")
public class DataBasePropertiyDAOTest {
	   
	   @Inject
	   private DataBasePropertyDAO dao;

	   @Test
	   public void testSelectDataBasePropertyList() {
	      List<DataBasePropertyVO> list = dao.selectDataBasePropertyList();
	      assertNotNull(list);
	      assertNotEquals(0, list.size());
	   }
	   
	   @Test
	   public void testSelectDataBaseProperty() {
	      DataBasePropertyVO vo = dao.selectDataBaseProperty("GLOBAL_DB_NAME");
	      assertNotNull(vo);
	      log.info("조회결과 : {}", vo);
	   }
	}