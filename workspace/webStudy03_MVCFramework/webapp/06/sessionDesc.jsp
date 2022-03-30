<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>06/sessionDesc.jsp</title>
</head>
<body>
<h4> HttpSession </h4>
<h4 id="sessionTimer">2:00</h4>
<div id="msgArea">
	세션 연장할래요? 
	<input type="button" value="예" class="ctrlBtn" id="yesBtn"/>
	<input type="button" value="아니오"  class="ctrlBtn" id="noBtn"/>
</div>
<!-- setInterval, setTimeout, clearInterval, clearTimeout -->
<!-- 모든 이벤트 핸들러는 jQuery 활용. -->
<pre>
	세션 : 어플리케이션 사용 시작부터 종료까지의 시간.
	
	시작 : 한명의 클라이언트가 하나의 브라우저를 이용해서 최초의 요청을 발생시켰을 때.
	session ID : <%=session.getId() %>
	세션 생성 시점 : <%=new Date(session.getCreationTime()) %>
	세션내의 마지막 요청 시점 : <%=new Date(session.getLastAccessedTime()) %>
	timeout : <%=session.getMaxInactiveInterval() %>s
	
	세션 유지 방법 - session tracking mode, 클라이언트와 서버 사이에서 세션의 ID를 공유하는 방법
	1) COOKIE : Cookie 저장 형태로 세션의 ID 를 공유하는방법.
	2) URL : 세션 파라미터 사용한 공유 방법.
		<a href="sessionDesc.jsp;jsessionid=<%=session.getId()%>">쿠키가 없는 상태에서 세션 유지</a>
	3) SSL : Secure Socket 을 이용한 공유 방법.
	종료
		1) 로그아웃
		2) timeout 이내에 새로운 요청이 요청이 발생하지 않을때.
		3) 세션 쿠키가 삭제될때 - session tracking mode 에 따라.
		4) 브라우저 종료시.
</pre>
</body>
</html>























