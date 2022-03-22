<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>06/implicitObject.jsp</title>
</head>
<body>
<h4>기본객체</h4>
ContextAwareComputing
<pre>
	: JSP 컨테이너에 의해 서블릿 소스가 파싱될때 지역변수의 형태로 제공되는 객체들.
	1. HttpServletRequest request :  클라이언트와 요청에 대한 정보 캡슐화
	2. HttpServletResponse response : 서버에서 클라이언트로 전송되는 응답에 대한 캡슐화
	3. JspWriter out : jsp 웹 페이지에서 사용하는 출력스트림.
		<a href="bufferDesc.jsp">bufferDesc.jsp</a>
	4. HttpSession session : 한 세션에 대한 캡슐화
		<a href="sessionDesc.jsp">sessionDesc.jsp</a>
	5. ServletContext application : 서버와 어플리케이션 대한 정보의 캡슐화
		<%=application.hashCode() %>
		<a href="<%=request.getContextPath() %>/07/servletContextDesc.jsp">servletContextDesc.jsp</a>
		<a href="<%=application.getContextPath() %>/desc">Desc servlet</a>
	6. ServletConfig config : 하나의 서블릿에 대한 메타 설정 정보의 캡슐화
	7. Object page : this
	8. Throwable exception : 비정상 처리 상황이 발생했을때, 해당 상황에 대한 정보의 캡슐화
	9(****). PageContext pageContext : 하나의 JSP 페이지와 관련된 모든 정보의 캡슐화
		<a href="../07/pageContextDesc.jsp">pageContextDesc.jsp</a>
</pre>
</body>
</html>











