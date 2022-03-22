<%@page import="kr.or.ddit.vo.DataBasePropertyVO"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.db.ConnectionFactory"%>
<%@page import="java.sql.ResultSetMetaData"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>09/jdbcDesc.jsp</title>
</head>
<body>
<h4>JDBC(Java DataBase Connectivity)</h4>
<pre>
	1. 드라이버를 빌드패스에 추가
	2. 드라이버 로딩
	3. Connection 수립
	4. 쿼리 객체 생성
		1) Statement
		2) PreparedStatement(선컴파일된 쿼리 객체)
		3) CallableStatement : 프로시저나 함수를 호출할때 사용하는 객체
	5. 쿼리 실행(CRUD)
		1) ResultSet executeQuery : SELECT
		2) int executeUpdate : INSERT, UPDATE, DELETE
	6. 쿼리 결과 (결과 집합-ResultSet)
	7. 자원 해제(close)
</pre>
<%
 	String[] headers = (String[]) request.getAttribute("headers");
	List<DataBasePropertyVO> dataList =(List) request.getAttribute("dataList");
%>
<form>
	<input type="text" name="propertyName" placeholder="검색할 프로퍼티명"/>
	<input type="submit" value="검색" />
</form>
<table>
	<thead>
		<tr>
		<%
			for(String header : headers){
				%>
				<th><%=header %></th>
				<%
			}
		%>
		</tr>
	</thead>
	<tbody>
	<%
	for(DataBasePropertyVO tmp : dataList){
		%>
		<tr>
			<td><%=tmp.getPropertyName() %></td>
			<td><%=tmp.getPropertyValue() %></td>
			<td><%=tmp.getDescription() %></td>
		</tr>
		<%
	}
	%>
	</tbody>
</table>

</body>
</html>













