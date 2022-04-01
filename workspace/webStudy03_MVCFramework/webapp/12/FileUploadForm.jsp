<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4> 파일 업로드양식 </h4>
<%--type : 만들어지는 입력 양식 ui, 입력양식을 통해 받을수 있는 데이터형태 
		  name : 식별성 역활, 파라미터의 이름이 결정 --%>
<form action="<c:url value='/12/fileUpload.do'/>" method="post" enctype="multipart/form-data">
	<input type="text" name="uploader" />
	<input type="file" name="uploadFile" />
	<input type="submit" value="업로드" />
</form>
<table border ="1" >
<tr>
<td>
<c:if test="${not empty uploader}"> 
업로더 : ${uploader}
<c:remove var="uploader" scope="session"/> <!-- var 는 속성의 이름이고 변수의 이름이 아니다 !  -->
</c:if>
</td>
</tr>
<tr>
<td>
<c:if test="${not empty fileMetaData}"> 
업로드 완료된 파일 : ${fileMetaData}
<c:url value="/12/fileDownLoad_2.do" var="downloadURL">
	<c:param name="file" value="${fileMetaData['saveName']}"></c:param>
</c:url>
<a href="${downloadURL}">다운로드</a>
	<%-- <c:remove var="fileMetaData" scope="session"/> --%>
</c:if>
</td>
</tr>
</table>
</body>
</html>