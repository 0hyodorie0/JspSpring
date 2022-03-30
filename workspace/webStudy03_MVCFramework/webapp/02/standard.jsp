<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" trimDirectiveWhitespaces="true"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>02/standard.jsp</title>
</head>
<body>
<h4>표준 JSP 구성 요소 : 템플릿 기반의 스크립트 언어 형태를 가진 스펙.</h4>
<pre>
	1. 정적 텍스트 : HTML, CSS, Javascript, 문자열...
	2. 동적 스크립트 요소(background 동작, server-side 실행 구조)
		1) scriptlet : &lt;% //자바 코드 %&gt;, _JspService 메소드의 지역코드 형태
			<%
				String sample = "변수값";
			%>
		2) directive : &lt;%@ 지시자명 속성들... %&gt;
			page (기본) : 현재 JSP 페이지에 대한 설정 정보. 
			include : 정적 내포(include)
			taglib : 커스텀 태그 라이브러리 로딩
		3) expression : &lt;%= 출력값 %&gt;
			<%=sample %>
		4) declaration : &lt;%! 전역 변수나 메소드에 대한 선언. %&gt;
			<%!
				private String instanceStr;
				private void test(){}
			%>
		5) comment
			1) client-side : HTML, CSS, Javascript
<!-- 			HTML  comment -->
<script type="text/javascript">
// 	JS comment
</script>
<style>
/* 	CSS comment */
</style>
			2) server-side java comment, JSP
			<% //java comment %>
			<%-- JSP comment --%>
			
	3. 기본객체(implicit object)
	4. EL(표현언어)
	5. Custom Tag Library(JSTL)		

</pre>
</body>
</html>












