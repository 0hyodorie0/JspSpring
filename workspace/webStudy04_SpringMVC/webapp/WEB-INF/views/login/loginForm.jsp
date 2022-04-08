<%@page import="java.util.Objects"%>
<%@page import="kr.or.ddit.utils.CookieUtils"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login/loginForm.jsp</title>
<%
	String message = (String)session.getAttribute("message");
	if(StringUtils.isNotBlank(message)){
		%>
		<script type="text/javascript">
			alert("${message }");
		</script>
		<%
		session.removeAttribute("message");
	}
%>
</head>
<body>
<!-- 1. 인증에 성공했을때 체크박스를 체크한 경우, 로그인에 성공한 아이디를 일주일동안 저장. -->
<!-- 2. 인증에 성공했을때 체크박스를 해제한 경우, 저장된 아이디가 있는 경우, 삭제. -->
<!-- 3. 저장된 아이디가 있으면,  아이디 초기값과, 체크박스 상태 복원 -->
<%
 	String savedId = new CookieUtils(request).getCookieValue("idCookie");
%>
<form action="${pageContext.request.contextPath }/login/loginProcess.do" method="post">
	<ul>
		<li>
			<input type="text" name="memId" value="<%=Objects.toString(savedId, "")%>"/>
			<label>
			<input type="checkbox" name="saveId" value="save" <%=StringUtils.isBlank(savedId)?"":"checked" %>/>아이디저장하기
			</label>
		</li>
		<li>
			<input type="password" name="memPass" />
			<input type="submit" value="로그인"  />
		</li>
	</ul>
</form>
</body>
</html>











