<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>07/pageContextDesc.jsp</title>
</head>
<body>
<h4>PageContext</h4>
<pre>
	: 하나의 JSP 와 관련된 모든 정보를 가진 객체.
	: 가장 먼저 생성되는 기본객체로 나머지 기본객체를 소유하고 있음.
	<%=request == pageContext.getRequest() %>
	<%=session == pageContext.getSession() %>
	<%=application == pageContext.getServletContext() %>
	${pageContext.request.contextPath }
	
	1. 기본객체 조회시.
	2. 속성 데이터 조회/저장.
		<%
			request.setAttribute("attrName", "attrValue");
			session.setAttribute("attrName", "attrValue");
			pageContext.setAttribute("attrName", "attrValue", 
						PageContext.APPLICATION_SCOPE);
		%>
	3. 에러 데이터 조회	
		<%
			ErrorData errorData = pageContext.getErrorData();
			out.println(errorData.getStatusCode());
		%>
	4. 요청 분기	
		<%--
			request.getRequestDispatcher("/").forward(request, response);
			pageContext.forward("/");
		--%>
</pre>
</body>
</html>

























