<%@page import="java.io.InputStream"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>07/servletContextDesc.jsp</title>
</head>
<body>
<h4>ServletContext application</h4>
<pre>
	: 서버와 현재 어플리케이션에 대한 정보를 가진 객체(Singleton).
	
	1. Mime type 조회. <%=application.getMimeType("chopa.jpg") %>
	2. 서버의 정보 조회. <%=application.getServerInfo() %>
		<%=application.getMajorVersion() %>.<%=application.getMinorVersion() %>
		<%=application == getServletContext() %>
		<%=application.hashCode() %>
	3. log 기록 
	<%
		application.log("기록용 로그 메시지");
	%>	
	4(*****). 웹리소스 확보
	<%=application.getRealPath("/resources/images/chopa.jpg") %>
	<%
		String realPathSrc = application.getRealPath("/resources/images/chopa.jpg"); 
		File source = new File(realPathSrc);
		String realPathDestFolder = application.getRealPath("/07");
		File destFolder = new File(realPathDestFolder);
		File destFile = new File(destFolder, source.getName()); 
		byte[] buffer = new byte[1024];
		int length = -1;
		try(
// 			FileInputStream fis = new FileInputStream(source);
			InputStream fis = application.getResourceAsStream(realPathSrc);
			FileOutputStream fos = new FileOutputStream(destFile);	
		){
			while((length = fis.read(buffer))!=-1){
				fos.write(buffer, 0, length);
			}
			out.println("destination exist : "+destFile.exists());
			out.println(destFile.getAbsolutePath());
		}
	%>
</pre>
<img src="<%=request.getContextPath() %>/07/chopa.jpg" />
</body>
</html>









