<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<h4> 회원 목록 </h4>
<table class="table table-striped">
	<thead class="thead-dark">
		<tr>
			<th>일련번호</th>
			<th>회원아이디</th>
			<th>회원명</th>
			<th>지역</th>
			<th>휴대폰</th>
			<th>이메일</th>
			<th>마일리지</th>
			<th>탈퇴여부</th>
		</tr>
	</thead>
	<tbody>
		<c:set var="memberList" value="${paging.dataList }" />
		<c:if test="${empty memberList }">
			<tr>
				<td colspan="8">조건에 맞는 회원이 없음.</td>
			</tr>
		</c:if>
		<c:if test="${not empty memberList}">
			<c:forEach items="${memberList }" var="member">
				<tr class="memberTr" data-mem-id="${member.memId }">
					<td>${member.rnum }</td>
					<td>${member.memId }</td>
					<td>${member.memName }</td>
					<td>${member.memAdd1 }</td>
					<td>${member.memHp }</td>
					<td>${member.memMail }</td>
					<td>${member.memMileage }</td>
					<td>${member.memDelete }</td>
				</tr>
			</c:forEach>
		</c:if>	
	</tbody>
	<tfoot>
		<tr>
		<td colspan="8">
			${paging.pagingHTMLBS }
			<div id="searchDIV">
				<select name="searchType">
					<option value>전체</option>
					<option value="NAME">이름</option>
					<option value="ADDRESS">주소</option>
				</select>
				<input type="text" name="searchWord" />
				<input type="button" value="검색" />
			</div>
		</td>
		</tr>
	</tfoot>
</table>
<form id="searchForm">
	<input type="hidden" name="page" />
	<input type="hidden" name="searchType" />
	<input type="hidden" name="searchWord" />
</form>
<script type="text/javascript">
	$(".memberTr").on("click", function(){
		 let memId = $(this).data('memId');
		 location.href="${pageContext.request.contextPath }/member/memberView.do?who="+memId;
	}).css('cursor', 'pointer');
	
	$("[name=searchType]").val("${paging.simpleCondition.searchType}");
	$("[name=searchWord]").val("${simpleCondition.searchWord}");
	
	const searchForm = $("#searchForm");
	const searchDIV = $("#searchDIV").on("click", "[type=button]", function(){
		let inputs = searchDIV.find(":input[name]");
		$(inputs).each(function(index, ipt){
			let name = this.name;
			let value = $(this).val();
			searchForm.find("[name="+name+"]").val(value);
		});
		searchForm.submit();
	});
	$(".pagination").on("click", "a", function(){
		let page = $(this).data("page");
		searchForm.find("[name=page]").val(page);
		searchForm.submit();
	});
</script>















