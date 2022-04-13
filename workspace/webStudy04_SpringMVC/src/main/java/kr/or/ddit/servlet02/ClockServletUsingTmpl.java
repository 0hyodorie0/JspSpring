package kr.or.ddit.servlet02;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Template Method design pattern
 * 
 *
 */
@WebServlet("/01/clock.tmpl")
public class ClockServletUsingTmpl extends AbstractServletUsingTmpl{

	@Override
	protected Map<String, Object> getDataMap(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Map<String, Object> dataMap = new HashMap<>();
		Calendar now = Calendar.getInstance();
		dataMap.put("now", String.format("%tc", now));
		return dataMap;
	}

}
