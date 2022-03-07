<%@page import="java.util.Date"%>
<%@ page language="java" 
    pageEncoding="UTF-8"%>
    <!-- contentType="text/plain; charset=UTF-8" -->
    <%
    	//response.setHeader("content-Type", "text/plain;charset=UTF-8");
    	response.setContentType("text/plain;charset=UTF-8");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>05/responseDesc.jsp</title>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-3.6.0.min.js"></script>
</head>
<body>
	<h4>HttpServletResponse</h4>
	<pre>
		: http 패키징되어서 클라이언트로 전송되는 응답과 관련된 모든 정보를 가진 객체.
		1. Response Line : Status Code(응답상태코드), protocol/version(HTTP/1.1)
			status code : 서버측의 처리 결과를 표현하는 세자리 숫자
			100~ : ING..(101 : websocket)
				Http : Connectless, Stteless
			200~ : OK
			300~ : 처리 완료를 위해 클라이언트의 추가 액션이 필요함
				304(Not Modified)
				302/307(Moved)- redirection 과정 중 [location header]와 함께 사용됨.
				<%--
					//response.sendRedirect(request.getContextPath()+"/");
					request.getRequestDispatcher("/").forward(request, response);
				--%>
			400~  : client error
				404(NotFoudn)
				400(BadRequest>)
				401/403 : Access Control 시 표현
				405 : Method not allowed
				415 : UnSupported Media Type        
			500~ : server error
		2. Response Header : meta data (name / value - String)
			1) Content-Type : response body 내의 컨텐츠의 종류를 표현
				: setHeader, setContentType, page 지시자 속성
				*** 츨력 스트림을 개방하기 전에 설정해야 함.
			2) Refresh : auto request에 활용
				현재 서버의 시간 : <span id="time"></span><%=new Date() %>
				<%--
					/* response.setHeader("Refresh", "1"); */
					response.setIntHeader("Refresh", 1);
				--%>
			3) Cache-Control/Pragma : 캐시 제어
			4) Location : redirect 이동 구조에 활용
		3. Response Body(contents body, message body)
	</pre>
	<script>
		let time = $("#time");
		$.ajax({
			url : "<%=request.getContextPath()%>/05/serverTime",
			method : "get",
			dataType : "json", //Accept(text/plain)
			success : function(resp){
				/* time.html(resp); */
				time.html(resp.time);
			},
			error:function(jqXHR, textStatus, errorThrown){
				console.log(jqXHR);
				console.log(textStatus);
				console.log(errorThrown);
			}
		})
	</script>
</body>
</html>