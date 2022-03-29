<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp"></jsp:include> 
<style>
.linkBtn:hover{
                background-color: #5A8DF3;
                cursor: pointer;
}

</style>

</head>
<body>
<%--거래처정보를 이용 해서 해당 안에테이블을 얹음  --%>


<table class="table table-bordered">
	<thead class="thead-dark">
	<tr>
		<th>상품코드</th>
		<td>${prod.prodId}</td>
	</tr>
	<tr>
		<th>상품명</th>
		<td>${prod.prodName}</td>
	</tr>
	<tr>
		<th>상품분류명</th>
		<td>${prod.lprodNm}</td>
	</tr>
	<tr>
		<th>상품매입가격</th>
		<td>${prod.prodCost}</td>
	</tr>
	<tr>
		<th>상품가격</th>
		<td>${prod.prodPrice}</td>
	</tr>
	<tr>
		<th>상품이미지</th>
		<td>${prod.prodImg}</td>
	</tr>
	<tr>
		<th>상품입고일</th>
		<td>${prod.prodInsdate}</td>
	</tr>
	<tr>
		<th>사이즈</th>
		<td>${prod.prodSize}</td>
	</tr>
	<tr>
		<th>색상</th>
		<td>${prod.prodColor}</td>
	</tr>
	<tr>
		<th>상품간단설명</th>
		<td>${prod.prodOutline}</td>
	</tr>
	<tr>
		<th>상품상세설명</th>
		<td>${prod.prodDetail}</td>
	</tr>
	<tr>
		<th>상품재고</th>
		<td>${prod.prodTotalstock}</td>
	</tr>
	<tr>
		<th>배송방법</th>
		<td>${prod.prodDelivery}</td>
	</tr>

	<tr>
		<th>거래처 정보</th>
		<td>
			<table border="1">
				<thead class="thead-dark">
					<tr>
						<th>거래처코드</th>
						<th>거래처명</th>
						<th>거래처 주소</th>
						<th>연락처</th>
						<th>거래품목수</th>
					</tr>
				</thead>
				<tbody>
				<c:set var ="prod" value="${prod }"></c:set> 
   		 	 	<c:url value="/buyer/buyerView.do" var="viewURL">
   		 	 		<c:param name="what" value="${prod.buyer.buyerId}"></c:param>
   		 	 	</c:url>
   		         <tr class="linkBtn"  data-href="${viewURL}">
					<td>${prod.buyer.buyerId}</td>
					<td>${prod.buyer.buyerName}</td>					
					<td>${prod.buyer.buyerAdd1}</td>
					<td>${prod.buyer.buyerComtel}</td>
					<td>${prod.buyer.prodCnt}</td>
					</tr>
				</tbody>							
			</table>		
		</td>
	</tr>
	<tr>
		<th>구매가</th>
		<td>${prod.prodCost}</td>
	</tr>
	<tr>
		<th>판매가</th>
		<td>${prod.prodPrice}</td>
	</tr>
	<tr>
		<th>세일가</th>
		<td>${prod.prodSale}</td>
	</tr>

	<tr>
		<th>구매자정보</th>
		<td>
			<table border="1">
				<thead class="thead-dark">
					<tr>
						<th>회원명</th>
						<th>휴대폰</th>
						<th>이메일</th>
						<th>탈퇴여부</th>
					</tr>
				</thead>			
				<tbody>
					<c:set var="memberList" value="${prod.memberList}" />
					<c:if test="${empty prod.memberList}">
						<tr>
							<td colspan="4"> 구매자가 없어요 ! </td>
						</tr>
					</c:if>
					<c:if test="${not empty prod.memberList}">
						<c:forEach items="${memberList }" var="member">
						 <c:url value="/member/memberView.do" var="viewURL">
						 	<c:param name="who" value="${member.memId }"></c:param>
						 </c:url>
							<tr class="linkBtn" data-href="${viewURL}">
								<td>${member.memName }</td>
								<td>${member.memHp }</td>
								<td>${member.memMail }</td>
								<td>${member.memDelete ? "탈퇴":"" }</td>
							</tr>		
						</c:forEach>					
					</c:if>
				</tbody>							
			</table>		
		</td>
	</tr>
</thead>
</table>
	<!-- 서버사이드 코드 방식 -->
	<jsp:include page="/includee/postScript.jsp"></jsp:include> 
	
</body>
</html>