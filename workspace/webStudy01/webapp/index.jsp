<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<style type="text/css">
		body{
			background-color:yellow;
		}
	</style>
	</head>
	<body>
		웰컴 페이지
		<h4 id="timeArea"></h4>
		
		<script type="text/javascript">
			document.getElementById('timeArea').innerHTML = new Date();
		</script>
	</body>
</html>