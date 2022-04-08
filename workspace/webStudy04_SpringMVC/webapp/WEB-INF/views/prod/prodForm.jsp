<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp" />
<c:if test="${not empty message }">
	<script type="text/javascript">
		alert("${message }");
	</script>
</c:if>
</head>
<body>
	<form method="post" enctype="multipart/form-data">
	<input ${command eq "UPDATE" ? "required" : "" } type="hidden" name="prodId" value="${prod.prodId }"/>
	<table class="table table-bordered">
		<tr>
			<th>상품명</th>
			<td>
				<input class="form-control" required type="text"
				name="prodName" value="${prod.prodName }" />
				<span>${errors.prodName }</span>
			</td>
		</tr>
		<tr>
			<th>분류</th>
			<td>
				<select name="prodLgu" class="form-control">
					<option value>상품분류</option>
					<c:forEach items="${lprodList }" var="lprod">
						<option value="${lprod['lprodGu'] }">
							${lprod.lprodNm }
						</option>
					</c:forEach>
				</select>
				<span>${errors['prodLgu'] }</span>
			</td>
		</tr>
		<tr>
			<th>거래처</th>
			<td>
				<select name="prodBuyer" class="form-control">
					<option value>거래처</option>
					<c:forEach items="${buyerList }" var="buyer">
						<option value="${buyer.buyerId }" class="${buyer.buyerLgu }">
							${buyer['buyerName'] }
						</option>
					</c:forEach>
				</select>
				<span>${errors['prodBuyer'] }</span>
			</td>
		</tr>
		<tr>
			<th>구매가</th>
			<td>
				<input class="form-control" required type="number"
				name="prodCost" value="${prod.prodCost }" />
				<span>${errors['prodCost'] }</span>
			</td>
		</tr>
		<tr>
			<th>판매가</th>
			<td>
				<input class="form-control" required type="number"
				name="prodPrice" value="${prod.prodPrice }" />
				<span>${errors['prodPrice'] }</span>
			</td>
		</tr>
		<tr>
			<th>세일가</th>
			<td>
				<input class="form-control" required type="number"
				name="prodSale" value="${prod.prodSale }" />
				<span>${errors['prodSale'] }</span>
			</td>
		</tr>
		<tr>
			<th>기본정보</th>
			<td>
				<input class="form-control" required type="text"
				name="prodOutline" value="${prod.prodOutline }" />
				<span>${errors['prodOutline'] }</span>
			</td>
		</tr>
		<tr>
			<th>상세정보</th>
			<td>
				<input class="form-control" type="text" name="prodDetail"
				value="${prod.prodDetail }" />
				<span>${errors['prodDetail'] }</span>
			</td>
		</tr>
		<tr>
			<th>상품이미지</th>
			<td>
				<input class="form-control" ${command eq "INSERT" ? "required" : "" } type="file"
				name="prodImage" />
				<span>${errors.prodImg }</span>
			</td>
		</tr>
		<tr>
			<th>총재고</th>
			<td>
				<input class="form-control" required type="number"
				name="prodTotalstock" value="${prod.prodTotalstock }" />
				<span>${errors['prodTotalstock'] }</span>
			</td>
		</tr>
		<tr>
			<th>입고일</th>
			<td>
				<input class="form-control" type="date" name="prodInsdate"
				value="${prod.prodInsdate }" />
				<span>${errors.prodInsdate }</span>
			</td>
		</tr>
		<tr>
			<th>적정재고</th>
			<td>
				<input class="form-control" required type="number"
				name="prodProperstock" value="${prod.prodProperstock }" />
				<span>${errors['prodProperstock'] }</span>
			</td>
		</tr>
		<tr>
			<th>크기</th>
			<td>
				<input class="form-control" type="text" name="prodSize"
				value="${prod.prodSize }" />
				<span>${errors['prodSize'] }</span>
			</td>
		</tr>
		<tr>
			<th>색상</th>
			<td>
				<input class="form-control" type="text" name="prodColor"
				value="${prod.prodColor }" />
				<span>${errors['prodColor'] }</span>
			</td>
		</tr>
		<tr>
			<th>배송방법</th>
			<td>
				<input class="form-control" type="text" name="prodDelivery"
				value="${prod.prodDelivery }" />
				<span>${errors['prodDelivery'] }</span>
			</td>
		</tr>
		<tr>
			<th>단위</th>
			<td>
				<input class="form-control" type="text" name="prodUnit"
				value="${prod.prodUnit }" />
				<span>${errors['prodUnit'] }</span>
			</td>
		</tr>
		<tr>
			<th>입고량</th>
			<td>
				<input class="form-control" type="number" name="prodQtyin"
				value="${prod.prodQtyin }" />
				<span>${errors['prodQtyin'] }</span>
			</td>
		</tr>
		<tr>
			<th>판매량</th>
			<td>
				<input class="form-control" type="number" name="prodQtysale"
				value="${prod.prodQtysale }" />
				<span>${errors['prodQtysale'] }</span>
			</td>
		</tr>
		<tr>
			<th>마일리지</th>
			<td>
				<input class="form-control" type="number" name="prodMileage"
				value="${prod.prodMileage }" />
				<span>${errors['prodMileage'] }</span>
			</td>
		</tr>
		<tr>	
			<td colspan="2">
				<input type="submit" value="저장" class="btn btn-success"/>
				<input type="reset" value="취소" class="btn btn-warning"/>
			</td>
		</tr>
	</table>
	</form>
<script type="text/javascript">
	let prodBuyerTag = $("[name=prodBuyer]").val("${prod.prodBuyer }");
	$("[name=prodLgu]").on("change", function(){
		let selectedLgu = $(this).val();
		if(!selectedLgu){
			$(prodBuyerTag).find("option").show();
		}else{
			$(prodBuyerTag).find("option").hide();
			$(prodBuyerTag).find("option."+selectedLgu).show();
			$(prodBuyerTag).find("option:first").show();
		}
	}).val("${prod.prodLgu }");

</script>
<jsp:include page="/includee/postScript.jsp" />
</body>
</html>

















