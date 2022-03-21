<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>05/flowControl.jsp</title>
</head>
<body>
<h4>흐름 제어 구조</h4>
<%
	request.setAttribute("sample", "전달할 속성 데이터");
%>
<pre>
	A -> B로 이동 방법.
	1. Request Dispatch : 서버 내에서만 이동하는 방식.
		1) forward : 최종 응답은 B에서만 전송되는 위임구조.
		2) include : 최종 응답이 A+B 의 형태로 전송되는 구조.
		<%
// 			request.getRequestDispatcher("/").forward(request, response);
			request.getRequestDispatcher("/").include(request, response);
// 			pageContext.include("/");
		%>
	2. Redirect : Stateless 특성과의 연관성을 바탕으로 동작.
		A를 대상으로 요청 발생 -> ** A에서 응답이 전송(body 가 없고, 302/Location-새로운 주소)
		-> Location 헤더의 방향(B)으로 새로운 요청 -> 최종 응답은 B에서 전송
		<%--
			response.sendRedirect(request.getContextPath() + "/");
		--%>
</pre>
</body>
</html>








