<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.vo.ProdVO"%>
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
	<!-- model name : buyer -->
	<!-- 거래처의 상세정보와 거래처 분류명, 해당 거래처와의 거래 품목(상품코드, 상품명, 구매가, 판매가)를 함께 조회. -->
	<jsp:useBean id="buyer" class="kr.or.ddit.vo.BuyerVO" scope="request" />
	<table class="table table-bordered">
		<tr>
			<th>거래처코드</th>
			<td><%=buyer.getBuyerId()%></td>
		</tr>
		<tr>
			<th>거래처명</th>
			<td><%=buyer.getBuyerName()%></td>
		</tr>
		<tr>
			<th>분류명</th>
			<td><%=buyer.getLprodNm()%></td>
		</tr>
		<tr>
			<th>거래품목</th>
			<td>
				<table class="table table-bordered">
					<thead class="thead-dark">
						<tr>
							<th>상품코드</th>
							<th>상품명</th>
							<th>구매가</th>
							<th>판매가</th>
							<th>구매율</th>
						</tr>
					</thead>
					<tbody>
						<%
						List<ProdVO> prodList = buyer.getProdList();
						if(prodList.isEmpty()){
							%>
							<tr>
								<td colspan="4">거래 품목 없음.</td>
							</tr>
							<%
						}else{
							for(ProdVO prod : prodList){
								String viewURL = request.getContextPath()
													+"/prod/prodView.do?what="
													+prod.getProdId();           
								%>
								<tr class="linkBtn"
									data-href="<%=viewURL %>"
								>
									<td><%=prod.getProdId() %></td>
									<td><%=prod.getProdName() %></td>
									<td><%=prod.getProdCost() %></td>
									<td><%=prod.getProdPrice() %></td>
									<td><%=prod.getProdRate() %></td>
								</tr>
								<%
							}
						}
						%>
					</tbody>
				</table>
			</td>
		</tr>
		<tr>
			<th>거래은행</th>
			<td><%=buyer.getBuyerBank()%></td>
		</tr>
		<tr>
			<th>계좌번호</th>
			<td><%=buyer.getBuyerBankno()%></td>
		</tr>
		<tr>
			<th>계좌주</th>
			<td><%=buyer.getBuyerBankname()%></td>
		</tr>
		<tr>
			<th>우편번호</th>
			<td><%=buyer.getBuyerZip()%></td>
		</tr>
		<tr>
			<th>주소1</th>
			<td><%=buyer.getBuyerAdd1()%></td>
		</tr>
		<tr>
			<th>주소2</th>
			<td><%=buyer.getBuyerAdd2()%></td>
		</tr>
		<tr>
			<th>전화번호</th>
			<td><%=buyer.getBuyerComtel()%></td>
		</tr>
		<tr>
			<th>팩스번호</th>
			<td><%=buyer.getBuyerFax()%></td>
		</tr>
		<tr>
			<th>이메일</th>
			<td><%=buyer.getBuyerMail()%></td>
		</tr>
		<tr>
			<th>담당자</th>
			<td><%=buyer.getBuyerCharger()%></td>
		</tr>
		<tr>
			<th>내선번호</th>
			<td><%=buyer.getBuyerTelext()%></td>
		</tr>
	</table>
<jsp:include page="/includee/postScript.jsp" />	
</body>
</html>