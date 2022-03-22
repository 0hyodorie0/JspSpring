<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/jquery-3.6.0.min.js"></script>
</head>
<body>
<form action="${pageContext.request.contextPath }/imageRead.do">
	<%
		String[] children = (String[]) request.getAttribute("children");
		String lastImage = (String) request.getAttribute("lastImage");
	%>
	<select name="image">
		<%
			for(String file : children){
				String selected = file.equals(lastImage)?"selected" : "";
				%>
				<option <%=selected %>><%=file %></option>
				<%
			}
		%>
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
	
	<%
		if(StringUtils.isNotBlank(lastImage)){
			%>
			form.trigger('submit');
			<%
		}
	%>
</script>
</body>












</html>