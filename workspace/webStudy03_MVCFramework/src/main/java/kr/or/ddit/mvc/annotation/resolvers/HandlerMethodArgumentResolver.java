package kr.or.ddit.mvc.annotation.resolvers;

import java.io.IOException;
import java.lang.reflect.Parameter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 핸들러 어댑터가 컨트롤러의 핸들러 메소드를 호출할때,
 * 넘겨줘야되는 메소드 아규먼트 하나를 생성하기 위한 전략.
 *
 */
public interface HandlerMethodArgumentResolver {
	/**
	 * 현재 아규먼트가 처리 가능한 타입이나 조건을 갖고있는 지를 판단하기 위한 메소드
	 * @param parameter
	 * @return
	 */
	public boolean isSupported(Parameter parameter);
	/**
	 * 하나의 아규먼트에 해당하는 값을 만들어내기 위한 메소드
	 * @param parameter
	 * @param req
	 * @param resp
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public Object argumentResolve(Parameter parameter, HttpServletRequest req, HttpServletResponse resp)
					throws ServletException, IOException;
}
