<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>03/sampleForm.jsp</title>
</head>
<body>
<h4> 입력 양식 </h4>
<!-- 이름, 생일, 나이, 전공, 자격증, 경력사항, 취미 -->
<form action="">
	<ul>
		<li>
			이름 : <input type="text" name="name" />
		</li>
		<li>
			생일 : <input type="date" name="birth" />
		</li>
		<li>
			나이 : <input type="number" name="age" />
		</li>
		<li>
			학력 :
			 <select name="grade">
			 	<option>고졸</option>
			 	<option>대졸</option>
			 	<option>석사</option>
			 	<option>박사</option>
			 </select>
		</li>
	</ul>
</form>
</body>
</html>