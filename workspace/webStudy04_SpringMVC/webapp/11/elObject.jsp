<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>11/elObject.jsp</title>
</head>
<body>
<h4>EL 기본객체</h4>
<pre>
	1. Scope 객체(4)
	2. 요청 파라미터 객체 : param(Map<String,String>), paramValues(Map<String,String[]>)
	<a href="?paramName=VALUE1&paramName=VALUE2">테스트</a>
	<%=request.getParameter("paramName") %>, ${param['paramName'] }, ${param.paramName }
	<%=request.getParameterValues("paramName") %>, ${paramValues['paramName'] }
	3. 요청 헤더 객체 : header(Map<String,String>), headerValues(Map<String,String[]>)
	<%=request.getHeader("Accept") %>, ${header['accept'] }, ${header.accept }
	${header['user-agent'] }, ${header.user-agent }
	4. cookie (Map<String,Cookie>)
	JSESSIONID -> ${cookie['JSESSIONID']['value'] }
	5. initParam(Map<String,String>) : 컨텍스트 파라미터 확보
	<%=application.getInitParameter("contextParameter") %>
	${initParam['contextParameter'] }, ${initParam.contextParameter }
	<%=session.getId() %>, ${pageContext.session.id }
	6. pageContext : ${pageContext.request.contextPath }
</pre>
</body>
</html>









