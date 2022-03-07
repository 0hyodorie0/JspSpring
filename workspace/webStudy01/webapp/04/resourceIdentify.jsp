<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>04/resourceIdentify.jsp</title>
<style>
	img{
		width:100px;
		height:100px;
	}
</style>
</head>
<body>
	<h4>자원의 종류와 식별 방법</h4>
	<pre>
		자원의 종류 : 자원의 경로표기 기준
		1. file system resource : D:\contents\Desert.jpg
		2. class path resource : /kr/or/ddit/images/Tulips.jpg
		3. web resource : http://localhost/webStudy01/resources/images/flower-1.jpg
		
		web resource 접근 형태
		
		URI(Uniform Resource Identifier) 
		URL(Uniform Resource Locator) 
		URN(Uniform Resource Name)
		URC(Uniform Resource Content)
		
		URL : http://IP[domain]:port
		
		절대경로
			1) client side : contentPath를 포함한 이후의 경로.
			2) server side : contentPath 이후의 경로.
		상대경로 : 현재 위치를 기준으로 자원의 경로 표현.
	</pre>
	<img src="../resources/images/flower-1.jpg"/>
	<img src="/resources/images/flower-1.jpg/resources/images/flower-1.jpg"/>
	<img src="<%=request.getContextPath() %>/resources/images/flower-1.jpg"/>
	<img src="/webStudy01/resources/images/flower-1.jpg"/>
	<img src="//localhost/webStudy01/resources/images/flower-1.jpg"/>
	<img src="http://localhost/webStudy01/resources/images/flower-1.jpg"/>
</body>
</html>