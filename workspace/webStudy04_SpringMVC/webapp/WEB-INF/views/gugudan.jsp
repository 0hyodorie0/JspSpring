<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<jsp:include page="/includee/preScript.jsp" />
	<style type="text/css">
		table{
			background-color: green;
		}
	</style>
</head>
<body>
<h4>${now }</h4>
<form method="post" id="gugudanForm">
<input type="number" name="minDan" min="2" max="9"/>
<select name="maxDan">
	<c:forEach begin="2" end="9" var="dan" step="1">
		<option value="${dan }">${dan }단</option>
	</c:forEach>
</select>
<input type="submit" value="구구단" />
</form>
<table id="gugudanTable">
<%-- <%=request.getAttribute("gugudan") %> --%>
</table>
<script type="text/javascript">
	let gugudanTable = $("#gugudanTable");
	$("#gugudanForm").on("submit", function(event){
		event.preventDefault();
		let url = this.action;
		let method = this.method;
		let data = $(this).serialize();
		$.ajax({
			url : url,
			method : method,
			data : data,
			dataType : "html", // Accept : text/html -> Content-Type : text/html
			success : function(resp) {
				// DOM 구조
				gugudanTable.html(resp);
			},
			error : function(jqXHR, textStatus, errorThrown) {
				console.log(jqXHR);
				console.log(textStatus);
				console.log(errorThrown);
			}
		});
	});
</script>
</body>
</html>















