<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>07/scopeDesc.jsp</title>
</head>
<body>
<h4>웹 어플리케이션에서 데이터 공유 영역</h4>
<pre>
	Scope(Map) : 4개의 기본 객체가 가진 생명주기가 한정된 저장공간.
	Attribute : Scope 를 통해 공유되는 데이터 (name/value).
	1. pageContext (PageScope) : 하나의 JSP 페이지 내에서만 사용되는 공간.
	2. request(RequestScope) : 하나의 request 내에서만 사용되는 공간.
	3. session(SessionScope) : 하나의 session 내에서만 사용되는 공간.
	4. application(ApplicationScope/ServletContextScope) : 어플리케이션과 동일한 생명주기.
	
	setAttribute(name, value), getAttribute(name), removeAttribute(name)
	
</pre>
</body>
</html>