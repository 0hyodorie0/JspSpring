<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" buffer="8kb" autoFlush="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>06/bufferDesc.jsp</title>
</head>
<body>
<h4>버퍼 관리</h4>
<pre>
	현재 버퍼 크기 : <%=out.getBufferSize() %>
	잔여 버퍼 크기 : <%=out.getRemaining() %>
	<%
		request.getRequestDispatcher("/02/standard.jsp").forward(request, response);
	%>
	<%--
		for(int i=1; i<=100; i++){
			out.println(i+"번째 반복문");
			if(out.getRemaining()<50 && !out.isAutoFlush()){
				out.flush();
			}
// 			if(i==100){
// 				throw new NullPointerException("강제 발생 예외");
// 			}
		}
	--%>
	A의 남은 잔여 코드
</pre>
</body>
</html>








