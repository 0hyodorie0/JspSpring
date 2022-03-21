<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>03/requestDesc.jsp</title>
</head>
<body>
<h4> HttpServletRequest(request 기본객체) </h4>
<pre>
	: 클라이언트와 요청에 대한 정보를 캡슐화한 객체
	
	Http 패키징 규칙
	1. Request Line : URL(자원식별), Method, Protocol/ver(HTTP/1.1)
		HTTP method : 요청의 의도(목적), 패키징 방식
		1) GET(R)
		2) POST(C)
		3) PUT/PATCH(U)
		4) DELETE(D)
		5) OPTION(preFlight 요청)
		6) HEADER
		7) TRACE
		ex) /boardRead.do, /boardInsert.do, /boardUpdate.do, boardDelete.do
		ex) /board (GET, POST, PUT, DELETE), RESTful URI 구조
	<%=request.getRequestURI() %>
	<%=request.getMethod() %>
	<%=request.getProtocol() %>	
	
	2. Request Header : meta data (name:value-String)
		getHeader(name), getHeaderNames
	3. Request Body (only POST)
		** 전달 파라미터에 특수문자가 포함된 경우. : 데이터를 읽기 전에 디코딩 charset 설정 필요.
		Line(Query string) : server 설정 필요(server.xml)
		Body(Content) : setCharacterEncoding
	
	전달된 파라미터 : <%=request.getParameter("param") %>
</pre>
<form method="post" action="<%=request.getContextPath() %>/03/requestDesc.jsp">
	<input type="text" name="param" />
	<input type="text" name="param" />
	<input type="text" name="param" />
	<input type="submit" value="전송" />
</form>
<table>
<thead>
<tr>
	<th>HEADER NAME</th>
	<th>HEADER VALUE</th>
</tr>
</thead>
<tbody>
<%
	Enumeration<String> headerNames = request.getHeaderNames();
	while(headerNames.hasMoreElements()){
		String name = headerNames.nextElement();
		String value = request.getHeader(name);
		%>
		<tr>
			<td><%=name %></td>
			<td><%=value %></td>
		</tr>
		<%
	}
%>
</tbody>
</table>
</body>
</html>


















