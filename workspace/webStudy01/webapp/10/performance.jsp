<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="kr.or.ddit.member.service.MemberServiceImpl"%>
<%@page import="kr.or.ddit.member.service.MemberService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>10/performance.jsp</title>
</head>
<body>
<h4> Performance check(Response Time) </h4>
<pre>
	response time = processing time + latency time
	1. 한번 처리하고 한번 연결수립한 소요 시간 : 16ms, 5ms
	2. 100번 처리하고 100번 연결수립한 소요 시간 : 900ms, 70ms
	3. 100번 처리하고 한번 연결수립한 소요 시간 : 17ms
	<%
		long start = System.currentTimeMillis();
		for(int count=1; count<=100; count++){
			MemberService service = new MemberServiceImpl();
			MemberVO sample = service.retrieveMember("a001");
			out.println(sample);
		}
		long end = System.currentTimeMillis();
	%>
	소요시간 : <%=end-start %>ms
</pre>
</body>
</html>





