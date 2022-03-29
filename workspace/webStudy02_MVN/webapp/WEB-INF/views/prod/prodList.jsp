<%@page import="kr.or.ddit.vo.BuyerVO"%>
<%@page import="java.util.Map"%>
<%@page import="kr.or.ddit.vo.PagingVO"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.vo.ProdVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
			<%
				PagingVO<ProdVO> paging = (PagingVO)request.getAttribute("paging");
				List<ProdVO> prodList = paging.getDataList();
				if(prodList.isEmpty()){
					%>
					<tr>
						<td colspan="6">상품이 존재하지 않습니다.</td>
					</tr>
					<%
				}else{
					
					for(ProdVO prod : prodList){
						String viewURL = request.getContextPath() + 
								         "/prod/prodView.do?what" +
								         prod.getProdId();
						%>
						<tr>
							<td><%=prod.getRnum() %></td>
							<td><%=prod.getProdId() %></td>
							<td><%=prod.getProdName() %></td>
							<td><%=prod.getLprodNm() %></td>
							<td><%=prod.getBuyer().getBuyerName() %></td>
							<td><%=prod.getMemcnt() %></td>
						</tr>
						
						<%
					}
				}
				
			%>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="6">
					<%=paging.getPagingHTML() %>
					<div id="searchDIV">
						<select name="prodLgu">
							<%--
								List<Map<String,Object>> lprodList = (List) request.getAttribute("lprodList");
								for(Map<String,Object> lprod : lprodList){
									--%>
									<%-- <option value="<%=lprod.get("lprodGu") %>"><%=lprod.get("lprodNm") %></option> --%>
									<%--
								}
							--%>
						</select>
						<select name="prodBuyer">
							<%--
								List<BuyerVO> buyerList = (List)request.getAttribute("buyerList");
								for(BuyerVO buyer : buyerList){
									--%>
									<%-- <option value="<%=buyer.getl%>"></option> --%>
									<%--
								}
							--%>
							
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