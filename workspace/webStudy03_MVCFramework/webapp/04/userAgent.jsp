<%@page import="kr.or.ddit.enumpkg.BrowserInfo"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>04/userAgent.jsp</title>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-3.6.0.min.js"></script>
</head>
<body>
<!-- enum 문법 활용 추천. -->
<!-- 사용자의 브라우저 종류를 확인하고 -->
<!-- "당신의 브라우저는 크롬[사파리, 엣지]입니다." -->
<!-- 와 같은 형태의 메시지를 alert 창으로 출력. -->
<%--
	String agent = request.getHeader("User-Agent");
	
	String browser = BrowserInfo.findBrowser(agent);
	
	String message = String.format( "당신의 브라우저는 %s입니다.", browser );
--%>
<a href="#" id="clickBtn">클릭</a>
<script type="text/javascript">
	const PTRN = "당신의 브라우저는 %s입니다.";
	$("#clickBtn").on("click", function(event){
		event.preventDefault();
		$.ajax({
			url : "<%=request.getContextPath()%>/04/findBrowser",
			method : "get",
			dataType : "json", // Accept/Content-Type
			success : function(resp) {
				let message = PTRN.replace("%s", resp.browser);
				alert(message);
			},
			error : function(jqXHR, textStatus, errorThrown) {
				console.log(jqXHR);
				console.log(textStatus);
				console.log(errorThrown);
			}
		});
// 		1. 비동기 요청 발생(/04/findBrowser)
// 		2. 응답 데이터 JSON 형식(browser 프로퍼티)
// 		3. 응답이 전송되면, alert 창
	});
</script>
</body>
</html>












