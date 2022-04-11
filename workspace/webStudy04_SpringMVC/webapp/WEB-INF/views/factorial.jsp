<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>02/factorial.jsp</title>
<jsp:include page="/includee/preScript.jsp" />
</head>
<body>
<h4>팩토리얼 연산 수행</h4>
<!-- form 에 의해 발생하는 동기요청을 비동기요청으로 전환. -->
<form method="post" name="factorialForm">
	<input type="number" name="operand" />
	<input type="text" name="param1" />
	<select name="param2">
		<option>value1</option>
		<option>value2</option>
	</select>
	<button type="submit">=</button>
</form>
<!-- 선언부 기호(재귀호출)를 사용해서, 10! 연산의 결과를 브라우저에 랜더링. -->
<div id="resultArea">
${result }
</div>
<script type="text/javascript">
	let resultArea = $("#resultArea");
	$("[name=factorialForm]").on("submit", function(event){
		event.preventDefault();
		let url = this.action;
		let method = this.method;
		let data = $(this).serialize();
		console.log(data);
		$.ajax({
			url : url,
			method : method,
			data : data,
			dataType : "json",
			success : function(resp) {
				console.log(resp);
				resultArea.html(resp.result);
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





















