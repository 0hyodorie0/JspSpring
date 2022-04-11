<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp" />
</head>
<body>
<form action="${pageContext.request.contextPath }/imageRead.do">
	<%
		String[] children = (String[]) request.getAttribute("children");
		String lastImage = (String) request.getAttribute("lastImage");
	%>
	<select name="image">
		<c:forEach items="${children }" var="file">
			<c:set var="selected" value="${file eq lastImage?'selected':'' }" />
			<option ${selected }>${file }</option>
		</c:forEach>
	</select>
	<input type="submit" value="전송" />
</form>
<!-- <img src="http://localhost/webStudy01/imageRead.do?image=cute1.PNG" /> -->
<script type="text/javascript">
	const SRCPTRN = "http://localhost/webStudy01/imageRead.do?image=%V";
	let form = $("form:first").on("submit", function(event){
		event.preventDefault();
		let selected = $("[name=image]").val();
		let newImg = $("<img>").attr({
						src:SRCPTRN.replace('%V', selected)
					});
		form.next("img:first").remove();
		form.after(newImg);
		return false;
	});
	<c:if test="${not empty lastImage}">
		form.trigger('submit');
	</c:if>
</script>
</body>
</html>