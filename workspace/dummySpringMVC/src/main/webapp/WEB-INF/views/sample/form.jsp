<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:set var="member" value="${member }" scope="page" />
<h4>성공 후 전달된 모델데이터 : ${message }</h4>
<h4>성공 후 전달된 모델데이터 : ${member }</h4>
<img src="<c:url value='/resources/memImages/${member.memImg }' />" />
<form method="post" enctype="multipart/form-data">
	<input type="text" name="memId" placeholder="아이디" />
	<input type="text" name="memName" placeholder="이름" />
	<input type="date" name="memBir" placeholder="생일" />
	<input type="file" name="memImage" />
	<input type="submit" value="전송" />
</form>
</body>
</html>