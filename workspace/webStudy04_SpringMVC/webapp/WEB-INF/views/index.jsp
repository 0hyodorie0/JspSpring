<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
웰컴 페이지
현재까지 누적 방문자수 : ${usercount }명
<%
	MemberVO authMember = (MemberVO) session.getAttribute("authMember");
	if(authMember==null){
		%>
		<a href="${cPath }/login/loginForm.do">로그인</a>
		<a href="${cPath }/member/memberInsert.do">회원가입</a>
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
		<ul>
			<c:forEach items="${userList }" var="user">
				<li>${user.memName }</li>
			</c:forEach>
		</ul>
		<%
	}
%>


<h4><%=request.getAttribute("sample") %></h4>
<h4 id="timeArea"></h4>
<script type="text/javascript">
	document.getElementById("timeArea").innerHTML = new Date();
</script>
















