<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>07/viewCookie.jsp</title>
</head>
<body>
<h4>다른 경로에서 확인한 쿠키</h4>
<pre>
	<%
		Cookie[] cookies = request.getCookies();
		if(cookies!=null){
			Cookie finded = null;
			for(Cookie tmp : cookies){
// 				if("korean".equals(tmp.getName())){
// 					finded = tmp;
// 					break;
// 				}
				String name = tmp.getName();
				String value = URLDecoder.decode(tmp.getValue(), "UTF-8");
				out.println(
					String.format("%s : %s", name, value)	
				);
			} // for end
// 			String findedValue = null;
// 			if(finded!=null){
// 				findedValue = URLDecoder.decode(finded.getValue(), "UTF-8");
// 			}
// 			out.println(findedValue);
		}
	%>	
</pre>
</body>
</html>