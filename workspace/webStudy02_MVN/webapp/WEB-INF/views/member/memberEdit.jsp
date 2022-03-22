<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member/memberEdit.jsp</title>
<jsp:include page="/includee/preScript.jsp"/>
<%
	String message = (String)session.getAttribute("message");
	if(StringUtils.isNotBlank(message)){
%>
	<script>
		alert("<%=message %>");
	</script>
	<%
		session.removeAttribute("message");
	}
%>

</head>
<body>
<%
	MemberVO member = (MemberVO)request.getAttribute("member");
%>
<form action="${pageContext.request.contextPath }/member/memberUpdateServlet.do" method="post">
	
</form>
</body>
</html>