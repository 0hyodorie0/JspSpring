<%@page import="kr.or.ddit.vo.ProdVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp"></jsp:include>
</head>
<body>
<jsp:useBean id="prod" class="kr.or.ddit.vo.ProdVO" scope="request" />
<table class="table table-bordered">
		<tr>
			<th>상품코드</th>
			<td><%=prod.getProdId()%></td>
		</tr>
		<tr>
			<th>상품명</th>
			<td><%=prod.getProdName()%></td>
		</tr>
		<tr>
			<th>상품분류명</th>
			<td><%=prod.getLprodNm()%></td>
		</tr>
		<tr>
			<th>거래처 정보</th>
			<td>
				<table class="table table-bordered">
					<thead class="thead-dark">
						<tr>
							<th>거래처 코드</th>
							<th>거래처명</th>
							<th>거래처 주소</th>
							<th>연락처</th>
							<th>거래품목수</th>
						</tr>
					</thead>
					<tbody>
						<%
							String viewURL = request.getContextPath()
									+ "/buyer/buyerView.do?what="
									+ prod.getBuyer().getBuyerId();
						%>
						<tr class="linkBtn"
							data-href="<%=viewURL%>">
							<td><%=prod.getBuyer().getBuyerId() %></td>
							<td><%=prod.getBuyer().getBuyerName() %></td>
							<td><%=prod.getBuyer().getBuyerAdd1() %></td>
							<td><%=prod.getBuyer().getBuyerComtel() %></td>
							<td><%=prod.getBuyer().getProdCnt() %></td> 
						</tr>
					</tbody>
				</table>
			</td>
		</tr>
		<tr>
			<th>구매가</th>
			<td><%=prod.getProdCost()%></td>
		</tr>
		<tr>
			<th>판매가</th>
			<td><%=prod.getProdPrice()%></td>
		</tr>
		<tr>
			<th>세일가</th>
			<td><%=prod.getProdSale()%></td>
		</tr>
		<tr>
			<th>기본정보</th>
			<td><%=prod.getProdOutline()%></td>
		</tr>
		<tr>
			<th>상세정보</th>
			<td><%=prod.getProdDetail()%></td>
		</tr>
		<tr>
			<th>상품이미지</th>
			<td><%=prod.getProdImg()%></td>
		</tr>
		<tr>
			<th>총재고</th>
			<td><%=prod.getProdTotalstock()%></td>
		</tr>
		<tr>
			<th>입고일</th>
			<td><%=prod.getProdInsdate()%></td>
		</tr>
		<tr>
			<th>적정재고</th>
			<td><%=prod.getProdProperstock()%></td>
		</tr>
		<tr>
			<th>크기</th>
			<td><%=prod.getProdSize()%></td>
		</tr>
		<tr>
			<th>색상</th>
			<td><%=prod.getProdColor()%></td>
		</tr>
		<tr>
			<th>배송방법</th>
			<td><%=prod.getProdDelivery()%></td>
		</tr>
		<tr>
			<th>단위</th>
			<td><%=prod.getProdUnit() %></td>
		</tr>
		<tr>
			<th>입고량</th>
			<td><%=prod.getProdQtyin() %></td>
		</tr>
		<tr>
			<th>판매량</th>
			<td><%=prod.getProdQtysale() %></td>
		</tr>
		<tr>
			<th>마일리지</th>
			<td><%=prod.getProdMileage() %></td>
		</tr>
</table>
 <jsp:include page="/includee/postScript.jsp"></jsp:include>
</body>
</html>