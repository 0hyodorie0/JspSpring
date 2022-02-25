<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>02/standard.jsp</title>
</head>
<body>

<pre>
	1. 정적 텍스트 : HTML, CSS, Javascript, 문자열...
	2. 동적 스크립ㅌ 요소(background 동작, server-side 실행 구조)
		1) scriptlet : &lt;% //자바 코드 %&gt;, _JspService 메소드의 지역코드 형태
			<%
				String sample = "변수값";
			%>
		2) directive : &lt;%@ 지시자명 속성들... %&gt;
			현재 JSP 페이지에 대한 설정 정보.
			page (기본)
			include : 정적 내포(include)
			taglib : 커스텀 태그 라이브러리 로딩
		3) expression : &lt;%=출력값%&gt;
			<%=sample %>
		4) declaration
		5) comment
</pre>
</body>
</html>