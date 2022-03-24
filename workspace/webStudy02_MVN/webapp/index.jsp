<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp" />
<style type="text/css">
	body{
		background-color: yellow;
	}
</style>
<%
	request.setCharacterEncoding("UTF-8");
	String message = request.getParameter("message");
	if(StringUtils.isBlank(message)){
		message = (String) session.getAttribute("message");
	}
	if(StringUtils.isNotBlank(message)){
		%>
		<script type="text/javascript">
			alert("<%=message %>");
		</script>
		<%
		session.removeAttribute("message"); // flash attribute
	}
%>
</head>
<body>
웰컴 페이지
<%
	MemberVO authMember = (MemberVO) session.getAttribute("authMember");
	if(authMember==null){
		%>
		<a href="${pageContext.request.contextPath }/login/loginForm.do">로그인</a>
		<a href="${pageContext.request.contextPath }/member/memberInsert.do">회원가입</a>
		<%
	}else{
		%>
		<a href="${pageContext.request.contextPath }/myPage.do"><%=authMember.getMemName() %>님</a>
		<a href="${pageContext.request.contextPath }/login/logout.do" id="logoutBtn">로그아웃</a>
		<form method="post" id="hiddenForm"></form>
		<script>
			const hiddenForm = $("#hiddenForm");
			$("#logoutBtn").on("click", function(event){
				event.preventDefault();
				let href = this.href;
				hiddenForm.attr("action", href);
				hiddenForm.submit();
				hiddenForm.attr("action", "");
			});
		</script>
		<%
	}
%>


<h4><%=request.getAttribute("sample") %></h4>
<h4 id="timeArea"></h4>
<script type="text/javascript">
	document.getElementById("timeArea").innerHTML = new Date();
</script>
<jsp:include page="/includee/postScript.jsp" />
</body>
</html>
















