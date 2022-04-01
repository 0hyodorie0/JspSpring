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
<table class="table table-bordered">
		<tr>
			<th>상품코드</th>
			<td>${prod.prodId }</td>
		</tr>
		<tr>
			<th>상품명</th>
			<td>${prod.prodName }</td>
		</tr>
		<tr>
			<th>상품분류명</th>
			<td>${prod.lprodNm }</td>
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
						<c:set var="buyer" value="${prod.buyer }" />
						<c:url value="/buyer/buyerView.do" var="viewURL">
							<c:param name="what" value="${buyer.buyerId }" />
						</c:url>
						<tr class="linkBtn"
							data-href="${viewURL }"
						>
							<td>${buyer.buyerId }</td>
							<td>${buyer.buyerName }</td>
							<td>${buyer.buyerAdd1 }</td>
							<td>${buyer.buyerComtel }</td>
							<td>${buyer.prodCnt }</td>
						</tr>
					</tbody>
				</table>
			</td>
		</tr>
		<tr>
			<th>구매가</th>
			<td>${prod.prodCost }</td>
		</tr>
		<tr>
			<th>판매가</th>
			<td>${prod.prodPrice }</td>
		</tr>
		<tr>
			<th>세일가</th>
			<td>${prod.prodSale }</td>
		</tr>
		<tr>
			<th>기본정보</th>
			<td>${prod.prodOutline }</td>
		</tr>
		<tr>
			<th>상세정보</th>
			<td>${prod.prodDetail }</td>
		</tr>
		<tr>
			<th>상품이미지</th>
			<td>${prod.prodImg }</td>
		</tr>
		<tr>
			<th>총재고</th>
			<td>${prod.prodTotalstock }</td>
		</tr>
		<tr>
			<th>입고일</th>
			<td>${prod.prodInsdate }</td>
		</tr>
		<tr>
			<th>적정재고</th>
			<td>${prod.prodProperstock }</td>
		</tr>
		<tr>
			<th>크기</th>
			<td>${prod.prodSize }</td>
		</tr>
		<tr>
			<th>색상</th>
			<td>${prod.prodColor }</td>
		</tr>
		<tr>
			<th>배송방법</th>
			<td>${prod.prodDelivery }</td>
		</tr>
		<tr>
			<th>단위</th>
			<td>${prod.prodUnit }</td>
		</tr>
		<tr>
			<th>입고량</th>
			<td>${prod.prodQtyin }</td>
		</tr>
		<tr>
			<th>판매량</th>
			<td>${prod.prodQtysale }</td>
		</tr>
		<tr>
			<th>마일리지</th>
			<td>${prod.prodMileage }</td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="button" class="btn btn-primary linkBtn" value="목록으로"  
					data-href="<c:url value='/prod/prodList.do'/>"
				/>
			</td>
		</tr>
		<tr>
			<th>구매자 정보</th>
			<td>
				<table class="table table-bordered">
					<thead class="thead-dark">
						<tr>
							<th>회원명</th>
							<th>휴대폰</th>
							<th>이메일</th>
							<th>탈퇴여부</th>
						</tr>
					</thead>
					<tbody>
						<c:set var="memberList" value="${prod.memberList }"/>
						<c:if test="${empty memberList }">
							<tr>
								<td colspan="4">구매자 없음.</td>
							</tr>
						</c:if>
						<c:if test="${not empty memberList}">
							<c:forEach items="${memberList }" var="member">
								<c:url value="/member/memberView.do" var="viewURL">
									<c:param name="who" value="${member.memId }" />
								</c:url>
								<tr class="linkBtn" data-href="${viewURL }">
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
</table>
<jsp:include page="/includee/postScript.jsp" />
</body>
</html>














