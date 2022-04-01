<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
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
	<table class="table table-bordered">
		<tr>
			<th>거래처코드</th>
			<td>${buyer.buyerId }</td>
		</tr>
		<tr>
			<th>거래처명</th>
			<td>${buyer.buyerName }</td>
		</tr>
		<tr>
			<th>분류명</th>
			<td>${buyer.lprodNm }</td>
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
						<c:set var="prodList" value="${buyer.prodList }" />
						<c:choose>
							<c:when test="${not empty prodList }">
								<c:forEach items="${prodList }" var="prod">
									<c:url value="/prod/prodView.do" var="viewURL">
										<c:param name="what" value="${prod.prodId }" />
									</c:url>
									<tr class="linkBtn"
										data-href="${viewURL }"
									>
										<td>${prod.prodId }</td>
										<td>${prod.prodName }</td>
										<td>${prod.prodCost }</td>
										<td>${prod.prodPrice }</td>
										<td>${prod.prodRate }</td>
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr>
									<td colspan="5">거래 품목 없음.</td>
								</tr>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
			</td>
		</tr>
		<tr>
			<th>거래은행</th>
			<td>${buyer.buyerBank }</td>
		</tr>
		<tr>
			<th>계좌번호</th>
			<td>${buyer.buyerBankno }</td>
		</tr>
		<tr>
			<th>계좌주</th>
			<td>${buyer.buyerBankname }</td>
		</tr>
		<tr>
			<th>우편번호</th>
			<td>${buyer.buyerZip }</td>
		</tr>
		<tr>
			<th>주소1</th>
			<td>${buyer.buyerAdd1 }</td>
		</tr>
		<tr>
			<th>주소2</th>
			<td>${buyer.buyerAdd2 }</td>
		</tr>
		<tr>
			<th>전화번호</th>
			<td>${buyer.buyerComtel }</td>
		</tr>
		<tr>
			<th>팩스번호</th>
			<td>${buyer.buyerFax }</td>
		</tr>
		<tr>
			<th>이메일</th>
			<td>${buyer.buyerMail }</td>
		</tr>
		<tr>
			<th>담당자</th>
			<td>${buyer.buyerCharger }</td>
		</tr>
		<tr>
			<th>내선번호</th>
			<td>${buyer.buyerTelext }</td>
		</tr>
	</table>
<jsp:include page="/includee/postScript.jsp" />	
</body>
</html>