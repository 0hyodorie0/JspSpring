<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
	<style type="text/css">
		table{
			background-color: green;
		}
	</style>
	<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-3.6.0.min.js"></script>
</head>
<body>
<h4><%=request.getAttribute("now") %></h4>
<form method="post" id="gugudanForm">
<input type="number" name="minDan" min="2" max="9"/>
<select name="maxDan">
	<%
		for(int dan=2; dan<=9; dan++){
			%>
			<option value="<%=dan %>"><%=dan %>단</option>
			<%
		}
	%>
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















