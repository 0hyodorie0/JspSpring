<%@page import="kr.or.ddit.vo.BuyerVO"%>
<%@page import="java.util.Map"%>
<%@page import="kr.or.ddit.vo.PagingVO"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.vo.ProdVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp"/>
</head>
<body>
<!-- model name : prodList -->
<!-- 상품 분류별로 정렬하되, 최근 등록 상품이 먼저 조회됨. -->
<%-- <jsp:useBean id="prodList" class="kr.or.ddit.vo.ProdVO" scope="request"></jsp:useBean> --%>
	<table class="table table-bordered">
		<thead class="thead-dark">
			<tr>
				<th>일련번호</th>
				<th>상품코드</th>
				<th>상품명</th>
				<th>분류명</th>
				<th>거래처명</th>
				<th>총구매자수</th>
			</tr>
		</thead>
		<tbody class="thead-dark">
			<c:set var="prodList" value="${paging.dataList }" />
			<c:choose>
				<c:when test="${empty prodList }">
					<tr>
						<td colspan="6">상품이 존재하지 않습니다.</td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach items="${prodList }" var="prod">
						<c:url value="/prod/prodView.do" var="viewURL">
							<c:param name="what" value="${prod.prodId }"></c:param>
						</c:url>
						<tr class="linkBtn" data-href="${viewURL }">
							<td>${prod['rnum']}</td>
							<td>${prod['prodId']}</td>
							<td>${prod['prodName']}</td>
							<td>${prod['lprodNm']}</td>
							<td>${prod['buyer.buyerName']}</td>
							<td>${prod['memCnt']}</td>
						</tr>						
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="6">
					${paging.pagingHTMLBS}
					<div id="searchDIV">
						<select name="prodLgu">
						</select>
						<select name="prodBuyer">
						</select>
						<input type="text" name ="prodName"/>
						<input type="button" value="검색" id="searchBtn"/>
					</div>
				</td>
			</tr>
		</tfoot>
	</table>
	<form id="searchForm">
		<input type="text" name="page" />
		<input type="text" name="prodLgu" />
		<input type="text" name="prodBuyer" />
		<input type="text" name="prodName" />
	</form>
	<script type="text/javascript">
	$.ajax({
		url : "${pageContext.request.contextPath}/prod/getLprodList.do",
		dataType : "json",
		success : function(resp){
			/* {lprodList:[
				{lprodGu: "P101", lprodNm:"컴퓨터제품"}
			]} */
			let lprodList = resp.lprodList;
			let options = [];
			options.push($("<option>")).attr("value", "").text("상품 분류")
			$(lprodList).each(function(index, lprod){
				let option = $("<option>").attr("value", lprod.lprodGu).text(lprod.lprodNm);
				options.push(option);
			});
			searchDIV.find("[name-prodLgu]").append(options);
		},
		error : function(jqXHR, textStatus, errorThrown){
			console.log(jqXHR);
			console.log(textStatus);
			console.log(errorThrown);
		}
	})
		let searchForm = $("#searchForm");
		let searchDIV = $("#searchDIV");
		
		
		$("[name=prodLgu]").on("change", function(){
			let selectedLgu = $(this).val();
			if(!selectedLgu) {
				
			}else{
				$(prodBuyerTag).find("option").hide();
				$(prodBuyerTag).find("option."+selectedLgu).show();
				$(prodBuyerTag).find("option:first").show();				
			}
		}).val("${paging.detailCondition.prodLgu}");
		let prodBuyerTag = $("[name=prodBuyer]").val("${param.prodBuyer}");
		$("[name=prodName]").val("${detailCondition.prodName}");
		
		
		$(".pagination").on("click", "a", function(){
			let page = $(this).data("page");
			searchForm.find("[name=page]").val(page);
			searchForm.submit();
		});
		$("#searchBtn").on("click", function(){
			let inputs = searchDIV.find("[name]");
			$(inputs).each(function(index, ipt){
// 				console.log(this.name);
				let name = this.name;
				let value = $(this).val();
				searchForm.find("[name="+ name +"]").val(value);
			});
			searchForm.submit();
		});
	</script>
<jsp:include page="/includee/postScript.jsp"/>
</body>
</html>