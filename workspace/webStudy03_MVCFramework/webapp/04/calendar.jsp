<%@page import="java.util.Locale"%>
<%@page import="java.text.DateFormatSymbols"%>
<%@page import="java.util.Calendar"%>
<%@page import="static java.util.Calendar.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");

	String yearStr = request.getParameter("year");
	String monthStr = request.getParameter("month");
	String language = request.getParameter("language");
	Locale clientLocale = request.getLocale();
	if(language!=null && !language.isEmpty()){
		clientLocale = Locale.forLanguageTag(language);
	}
	Calendar cal = getInstance();
	if(yearStr!=null && yearStr.matches("\\d{4}") 
			&& monthStr!=null && monthStr.matches("\\d{1,2}") ){
		cal.set(YEAR, Integer.parseInt(yearStr));
		cal.set(MONTH, Integer.parseInt(monthStr));
	}
	
	cal.set(Calendar.DAY_OF_MONTH, 1);
	int firstDay = cal.get(Calendar.DAY_OF_WEEK);
	int offset = firstDay - 1;
	int lastDate = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	
	int year = cal.get(Calendar.YEAR);
	int month = cal.get(Calendar.MONTH);
	
	cal.add(Calendar.MONTH, -1);
	int beforeYear = cal.get(Calendar.YEAR);
	int beforeMonth = cal.get(Calendar.MONTH);
	cal.add(Calendar.MONTH, 2);
	int nextYear = cal.get(Calendar.YEAR);
	int nextMonth = cal.get(Calendar.MONTH);
	cal.add(Calendar.MONTH, -1);
	
	DateFormatSymbols dfs = new DateFormatSymbols(clientLocale);
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	th,td{
		border: 1px solid black;
	}
	table{
		border-collapse: collapse;
		width: 100%;
		min-height: 500px;
	}
	.red{
		color: red;
	}
	.blue{
		color: blue;
	}
</style>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	$(function(){
		let calForm = $("#calForm");
		calForm.find("input[name=year]").val("<%=year %>");
		calForm.find("select[name=month]").val("<%=month %>");
		calForm.find(":input[name]").on("change", function(){
			calForm.submit();
		});
		$("a.changeA").on("click", function(event){
			event.preventDefault();
			let year = $(this).data("year");
			calForm.find("input[name=year]").val(year);
			let month = $(this).data("month");
			calForm.find("select[name=month]").val(month);
			calForm.find("select[name=month]").trigger("change");
			return false;
		});
	});
</script>
</head>
<body>
<a href="#" class="changeA" data-year="<%=beforeYear %>" data-month="<%=beforeMonth %>">이전달</a>
<h4><%=String.format("%tc", cal) %></h4>
<a href="#" class="changeA" data-year="<%=nextYear %>" data-month="<%=nextMonth %>">다음달</a>

<form action="calendar.jsp" id="calForm" method="get">
year : <input type="number" name="year" />
month : 
<select name="month">
<%
	String[] months = dfs.getMonths();
	for(int idx = JANUARY; idx <= DECEMBER; idx++){
		out.println(
			String.format("<option value='%d'>%s</option>", idx, months[idx])		
		);
	}
%>
</select>
언어 : 
<select name="language">
<%
	Locale[] locales = Locale.getAvailableLocales();
	for(Locale tmp : locales){
		String text = tmp.getDisplayLanguage(tmp);
		if(text==null || text.isEmpty()) continue;
		String selected = clientLocale.equals(tmp) ? "selected" : "";
		String value = tmp.toLanguageTag();
		out.println(
			String.format("<option value='%s' %s>%s</option>", value, selected, text)		
		);
	}
%>
</select>
</form>

<table>
<thead>
<tr>
<%
	String[] weekDays = dfs.getWeekdays();
	for(int week = Calendar.SUNDAY; week<=Calendar.SATURDAY; week++){
		out.println(
			String.format("<th>%s</th>", weekDays[week])	
		);
	}
%>
</tr>
</thead>
<tbody>
<%
	
	int count = 1;
	for(int row=1; row<=6; row++){
		out.println("<tr>");
		for(int column=1; column<=7; column++){
			int dateCount = count++ - offset;
			String dateStr = dateCount>0 && dateCount<=lastDate ?
					dateCount+"" : "&nbsp;";
			out.println(
				String.format("<td>%s</td>", dateStr)		
			);
		}
		out.println("</tr>");
	}
%>
</tbody>
</table>
</body>










</html>