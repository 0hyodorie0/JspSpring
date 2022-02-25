<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!
	
%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>02/factorial.jsp</title>
	</head>
	<body>
		<h4>팩토리얼 연산 수행</h4>
		<form method="post">
			<input type="number" name="operand" />
			<button type="submit">=</button>
		</form>
		<!-- 선언부 기호를 사용해서, 10! 연산의 결과를 브 라우저에 렌더링. -->
		<% 
			Object data = request.getAttribute("result");
			long result = 0;
			if(data != null)
				result = (Long)data;
		%>
		<%=result %>
	</body>
</html>