package kr.or.ddit.servlet04;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import kr.or.ddit.vo.SampleVO;

@WebServlet("/03/sampleProcess.do")
public class SampleProcessServlet extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		super.service(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		String name = req.getParameter("name");
//		String birth = req.getParameter("birth");
//		int age = Integer.parseInt(req.getParameter("age"));
//		String grade = req.getParameter("grade");
//		String[] license = req.getParameterValues("license");
//		String career = req.getParameter("career");
//		String[] hobby = req.getParameterValues("hobby");
//		String gen = req.getParameter("gen");
		
		SampleVO vo = new SampleVO();
		req.setAttribute("sample", vo);
		Map<String, String[]> parameterMap = req.getParameterMap();
		try {
			System.out.println("==========>"+vo);
			BeanUtils.populate(vo, parameterMap);
			System.out.println("==========>"+vo);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		for(Entry<String, String[]> entry : parameterMap.entrySet()) {
//			String name = entry.getKey();
//			String[] values = entry.getValue();
////			vo.setName(name);
//			Field field = SampleVO.class.getDeclaredField(name);
//			field.setAccessible(true);
//			field.set(vo, values);
//		}
		
//		vo.setBirth(birth);
//		vo.setAge(age);
		Map<String, String> errors = new HashMap<>(); 
		req.setAttribute("errors", errors);
		boolean valid = validate(vo, errors);
		if(valid) {
			System.out.println(vo);
			
			String goPage = "/";
			resp.sendRedirect(req.getContextPath() + goPage);
		}else {
			String goPage = "/03/sampleForm.jsp";
			req.getRequestDispatcher(goPage).forward(req, resp);
		}
		
	}

	private boolean validate(SampleVO vo, Map<String, String> errors) {
		boolean valid = true;
		if(vo.getName()==null || vo.getName().isEmpty()) {
			errors.put("name", "이름 누락");
			valid = false;
		}
		if(vo.getGrade()==null || vo.getGrade().isEmpty()) {
			errors.put("grade", "학력 누락");
			valid = false;
		}
		return valid;
	}
}























