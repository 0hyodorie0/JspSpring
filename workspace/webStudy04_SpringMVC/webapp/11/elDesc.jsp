<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Collections"%>
<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>11/elDesc.jsp</title>
</head>
<body>
<h4>EL(Expression Language)</h4>
<pre>
	: 데이터 조회(출력)을 목적으로 한 스크립트 형태의 언어, 표현식의 대안.
	
	1. 속성 데이터 확보 , model 2 구조에서 scope 데이터(attribute) 활용에 사용됨.
	2. (속성을 대상 )연산자 지원
		1) 산술연산자  <% pageContext.setAttribute("number", "2"); %>
			${ 4 / 3 }, ${4 + number }
		2) 논리연산자 : &&(and), ||(or), !(not)
			${ true and true }, ${true or false }, ${false or logical }	
		3) 비교연산자 : > (gt), < (lt), >= (ge), <= (le), == (eq), != (ne)
		4) 단항연산자 : empty 
		<% 
			//pageContext.setAttribute("logical", "   ");
			List list = new ArrayList();
			list.add("element");
			pageContext.setAttribute("logical", list);
		%>
			${empty logical }, ${not empty logical }
		5) 3항 연산자 : 논리식? 참 : 거짓	
			${ empty logical ? '비어있다':'비어있지않다' }
	3. (속성을 대상 )객체 구조에 대한 접근 지원
		<%
			MemberVO member = new MemberVO("a001", "java");
			pageContext.setAttribute("member", member);
		%>
		${member }, ${member.getMemId() }, ${member.memId }, ${member['memId'] }
		${member.memPass }, ${member['memPass'] }
	4. (속성을 대상 )집합 객체에 대한 접근 지원
		<%
			String[] array = new String[]{"element1", "element2"};
			pageContext.setAttribute("array", array);
			List<String> list2= Collections.singletonList("listElement1");	
			pageContext.setAttribute("list2", list2);
			Set<String> set = Collections.singleton("setElement");
			pageContext.setAttribute("set", set);
			Map<String, Object> map = new HashMap<>();
			map.put("key1", "value1");
			map.put("key-2", "value2");
			pageContext.setAttribute("map", map);
		%>
		${array[1] }, ${array[4] }
		${list2.get(0) }, ${list2[0] }, ${list2[4] }, \${list2.get(4) }
		${set }
		${map.get("key1" }, ${map['key1'] }, ${map.key1 }
		${map.get("key-2") }, ${map['key-2'] }, ${map.key-2 }
	5. EL 기본 객체 (11) 지원 : <a href="elObject.jsp">기본객체(EL)</a>
		pageScope, requestScope, sessionScope, applicationScope
</pre>
<%
	pageContext.setAttribute("sample", "page scope sample");
	session.setAttribute("sample", "session scope sample");
	application.setAttribute("sample", "application scope sample");
%>
-->${sample }, ${sessionScope.sample }, ${applicationScope.sample }, ${requestScope.sample }
</body>
</html>











