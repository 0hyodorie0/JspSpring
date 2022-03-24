<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp" />
</head>
<body>
<h4> 회원 목록 </h4>
<%
	List<MemberVO> memberList = (List) request.getAttribute("memberList");
%>
<table class="table table-striped">
	<thead class="thead-dark">
		<tr>
			<th>일련번호</th>
			<th>회원아이디</th>
			<th>회원명</th>
			<th>휴대폰</th>
			<th>이메일</th>
			<th>마일리지</th>
			<th>탈퇴여부</th>
		</tr>
	</thead>
	<tbody>
	<%
		if(memberList.isEmpty()){
			%>
			<tr>
				<td colspan="7">조건에 맞는 회원이 없음.</td>
			</tr>
			<%
		}else{
			for(MemberVO member : memberList){
				%>
				<tr class="memberTr" data-mem-id="<%=member.getMemId() %>">
					<td><%=member.getRnum() %></td>
					<td><%=member.getMemId() %></td>
					<td><%=member.getMemName() %></td>
					<td><%=member.getMemHp() %></td>
					<td><%=member.getMemMail() %></td>
					<td><%=member.getMemMileage() %></td>
					<td><%=member.getMemDelete() %></td>
				</tr>
				<%
			}
		}// if end
	%>
	</tbody>
</table>
<script type="text/javascript">
	$(".memberTr").on("click", function(){
		 let memId = $(this).data('memId');
		 location.href="${pageContext.request.contextPath }/member/memberView.do?who="+memId;
	}).css('cursor', 'pointer');
</script>
<jsp:include page="/includee/postScript.jsp" />
</body>
</html>















