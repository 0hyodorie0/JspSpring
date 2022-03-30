<%@page import="java.net.URLDecoder"%>
<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>08/cookieDesc.jsp</title>
</head>
<body>
<h4>Cookie</h4>
<pre>
	1. 쿠키 생성(name, value), optional attributes..
		1) name(required) : 자바의 변수명에 사용되는 문자열로 구성.
		2) value(required) : 특수문자를 포함하는 경우, URL encoding 방식으로 인코딩이 필요함.
		3) domain[host](optional) : 쿠키의 재전송 여부 결정에 사용되는 조건.
			ex) www.naver.com, .naver.com, .naver.co.kr
			www.naver.com(GTLD)
			www.naver.co.kr(NTLD)
			
			blog.naver.com
			mail.naver.com
			news.naver.com
		4) path(optional)  : 쿠키의 재전송 여부 결정에 사용되는 조건.
							기본값으로 쿠키를 생성할때 경로가 적용됨.	
							다음번 요청이 path 로 설정된 경로의 하위로 발생하는 경우에만 재전송.
		5) maxAge/expires(optional)	: 쿠키의 만료시한 설정. 기본값으로 세션의 만료시한이 적용됨.	 			
	2. 응답데이터에 포함되어 전송
	<%
		Cookie sampleCookie = new Cookie("sample", "sampleValue");
		response.addCookie(sampleCookie); // 20개 한
		String value = URLEncoder.encode("한글 쿠키", "UTF-8");
		Cookie koreanCookie = new Cookie("korean", value);
		response.addCookie(koreanCookie);
		Cookie pathCookie = new Cookie("pathCookie", "PathCookieValue");
		pathCookie.setPath(request.getContextPath()+"/07");
		response.addCookie(pathCookie);
		Cookie allPathCookie = new Cookie("allPathCookie", "AllPathCookieValue");
		allPathCookie.setPath("/");
		response.addCookie(allPathCookie);
		Cookie longLiveCookie = new Cookie("longLive", "Long~Live~");
		longLiveCookie.setMaxAge(60*60*24*7);
		response.addCookie(longLiveCookie);
	%>
	
	3. 쿠키 저장소에 저장
	4. 다음번 요청을 통해 서버로 재전송
	
	5. 요청데이터에 포함된 쿠키를 통해 상태복원
	<a href="viewCookie.jsp">쿠키 확인(동일 경로)</a>
	<a href="../07/viewCookie.jsp">쿠키 확인(다른 경로)</a>
</pre>
</body>
</html>















