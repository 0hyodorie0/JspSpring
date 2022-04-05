<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>13/eventHandling.jsp</title>
</head>
<body>
<h4>웹어플리케이션에서의 (서버 사이드) 이벤트 처리</h4>
<button onclick="clickHandler();">클릭!</button>
<pre>
	이벤트 처리 과정
	ex) 특정 버튼을 클릭하면 alert 창을 띄운다.
	1. 이벤트 타겟 결정 , $("#button") : request, session, application(ServletContext)
	2. 이벤트 종류 결정 , $("#button").on("click")
		lifecycle : init(create)/destroy
		attribute : add/remove/replace
	3. 이벤트 핸들러 구현, function handler(){ //code }, Listener 구현체
	4. 이벤트 타겟에게 핸들러를 부착 $("#button").on("click", handler) , 
					web.xml, @WebListener
</pre>
<script>
	function clickHandler(){
		alert("메시지");
	}
</script>
</body>
</html>