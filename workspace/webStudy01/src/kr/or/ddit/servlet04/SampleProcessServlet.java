package kr.or.ddit.servlet04;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import kr.or.ddit.vo.SampleVO;

/**
 * Servlet implementation class SampleProcessServlet
 */
@WebServlet("/03/sampleProcess.do")
public class SampleProcessServlet extends HttpServlet {
	
	@Override
		protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			req.setCharacterEncoding("UTF-8");
			super.service(req, resp);
		}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String birth = req.getParameter("birth");
		int age = Integer.parseInt(req.getParameter("age"));
		String grade = req.getParameter("grade");
		String[] license = req.getParameterValues("license");
		String career = req.getParameter("career");
		
		
		SampleVO vo = new SampleVO();
		Map<String, String[]> parameterMap = req.getParameterMap();
		BeanUtils.populate(vo, parameterMap);
		
		for(Entry<String, String[]> entry : parameterMap.entrySet()) {
			String name = entry.getKey();
			String[] values = entry.getValue();
			vo.setName(name);
			Field field = SampleVO.class.getDeclaredField(name);
			field.setAccessible(true);
			field.set(vo, values);
		}
		
//		vo.setBirth(birth);
//		vo.setAge(age);
		
//		boolean valid = validate(vo);
//		if(valid) {
//			System.out.printf("%s : %s\n", "name", name);
//			System.out.printf("%s : %s\n", "birth", birth);
//			System.out.printf("%s : %s\n", "age", age);
//			System.out.printf("%s : %s\n", "grade", grade);
//			System.out.printf("%s : %s\n", "license", license);
//			System.out.printf("%s : %s\n", "career", career);			
//		}else {
//			
//		}
		
	}

	private boolean validate(SampleVO vo) {
		
		return false;
	}
}
