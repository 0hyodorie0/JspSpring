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
<pre>
기존 입력 데이터 : <%=request.getAttribute("sample") %>
에러 정보 : <%=request.getAttribute("errors") %>
</pre>
<!--  이름, 생일, 나이, 학력, 자격증, 경력사항, 취미 -->
<form action="<%=request.getContextPath() %>/03/sampleProcess.do" method="post">
	<ul>
		<li>
			이름 : <input type="text" name="name" required />
		</li>
		<li>
			생일 : <input type="date" name="birth" />
		</li>
		<li>
			나이 : <input type="number" name="age" />
		</li>
		<li>
			학력 : 
			<select name="grade" required>
				<option value>최종학력</option>
				<option value="G001">고졸</option>
				<option value="G002">대졸</option>
				<option value="G003">석사</option>
				<option value="G004">박사</option>
			</select>
		</li>
		<li>
			자격증 :
			<select name="license" multiple size="10">
				<option value="L001">정보처리산업기사</option>
				<option value="L002">정보처리기사</option>
				<option value="L003">정보보안산업기사</option>
				<option value="L004">정보보안기사</option>
				<option value="L005">SQLD</option>
				<option value="L006">SQLP</option>
			</select>
		</li>
		<li>
			경력사항 :
			<textarea rows="5" cols="100" name="career"></textarea>
		</li>
		<li>
			 취미 :
			 <input type="checkbox" name="hobby" value="movie"/>영화감상
			 <input type="checkbox" name="hobby" value="game" />게임
			 <input type="checkbox" name="hobby" value="book"/>독서
 		</li>
 		<li>
 			성별 :
 			<input type="radio" name="gen" value="M"/>남
 			<input type="radio" name="gen" value="F"/>여
 		</li>
 		<li>
 			<input type="submit" value="등록" />
 			<input type="reset" value="취소" />
 			<input type="button" value="일반버튼" />
 		</li>
	</ul>
</form>
</body>
</html>










