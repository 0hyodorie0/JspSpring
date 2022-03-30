<%@page import="kr.or.ddit.vo.PagingVO"%>
<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp"/>
<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/jquery-3.6.0.min.js"></script>
</head>
<body>
	<h4> 회원 목록 </h4>
	<table class="table table-bordered">
		<thead class="thead-dark">
			<th>일련번호</th>
			<th>회원아이디</th>
			<th>회원명</th>
			<th>지역</th>
			<th>휴대폰</th>
			<th>이메일</th>
			<th>마일리지</th>
			<th>탈퇴여부</th>
		</thead>
		<tbody class="thead-dark">
			<%
				PagingVO<MemberVO> paging = (PagingVO)request.getAttribute("paging");
				
				List<MemberVO> memberList = paging.getDataList();
				if(memberList.isEmpty()){
					%>
						<tr>
							<td colspan="8">조건에 맞는 회원이 없음.</td>
						</tr>
					<%	
				}else{
					for(MemberVO member : memberList){
						%>
						<tr class="memberTr" data-mem-id="<%= member.getMemId() %>">
							<td><%= member.getRnum() %></td>
							<td><%= member.getMemId() %></td>
							<td><%= member.getMemName() %></td>
							<td><%= member.getMemAdd1() %></td>
							<td><%= member.getMemHp() %></td>
							<td><%= member.getMemMail() %></td>
							<td><%= member.getMemMileage() %></td>
							<td><%= member.getMemDelete() %></td>
						</tr>
						<%
					}	
				}
				
			%>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="8">
					<%=paging.getPagingHTML() %>
					<div id="searchDIV">
						<select name="searchType">
							<option value>전체</option>
							<option value="ID">회원 아이디</option>
							<option value="NAME">회원 이름</option>
							<option value="ADDR">회원 주소</option>
						</select>
						<input type="text" name="searchWord"/>
						<input type="button" value="검색" id="searchBtn"/>
					</div>
				</td>
			</tr>
		</tfoot>
	</table>
	<form id="searchForm">
		<input type="text" name="page" hidden/>
		<input type="text" name="searchType" hidden/>
		<input type="text" name="searchWord" hidden/>
	</form>
	
	<script type="text/javascript">
		$(".memberTr").on("click", function(){
			let memId = $(this).data("memId");
			console.log(memId);
			location.href="${pageContext.request.contextPath }/member/memberView.do?who="+memId;
		});
		
		const searchForm = $("#searchForm");
		const searchDIV = $("#searchDIV").on("click", "[type=button]",function(){
			let inputs = searchDIV.find(":input[name]");
			$(inputs).each(function(index, ipt){
				let name = this.name;
				let value = $(this).val();
				searchForm.find("[name="+ name +"]").val(value);
			});
			searchForm.submit();
		});
		
		$("[name=searchType]").val("${param.searchType}");
		$("[name=searchWord]").val("${param.searchWord}");
		
		$(".pagination").on("click", "a", function(){
			let page = $(this).data("page");
			searchForm.find("[name=page]").val(page);
			searchForm.submit();
		});
		
// 		$("#searchBtn").on("click", function(){
// 			let inputs = searchDIV.find("[name]");
// 			$(inputs).each(function(index, ipt){
// 				let name = this.name;
// 				let value = $(this).val();
// 				searchForm.find("[name="+ name +"]").val(value);
// 			});
// 			searchForm.submit();
// 		});

	</script>
</body>
</html>