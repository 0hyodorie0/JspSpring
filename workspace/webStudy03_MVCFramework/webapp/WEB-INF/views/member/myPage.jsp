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
	<c:remove var="message" scope="session"/>
</c:if>
</head>
<body>
	<table class="table table-bordered">
		<tr>
			<th>회원아이디</th>
			<td>${member.memId }</td>
		</tr>
		<tr>
			<th>회원이미지</th>
			<td>
				<img src="data:image/*;base64,${member.memImgBase64 }" />
			</td>
		</tr>
		<tr>
			<th>회원명</th>
			<td>${member.memName }</td>
		</tr>
		<tr>
			<th>주민번호1</th>
			<td>${member.memRegno1 }</td>
		</tr>
		<tr>
			<th>주민번호2</th>
			<td>${member.memRegno2 }</td>
		</tr>
		<tr>
			<th>생일</th>
			<td>${member.memBir }</td>
		</tr>
		<tr>
			<th>우편번호</th>
			<td>${member.memZip }</td>
		</tr>
		<tr>
			<th>주소1</th>
			<td>${member.memAdd1 }</td>
		</tr>
		<tr>
			<th>주소2</th>
			<td>${member.memAdd2 }</td>
		</tr>
		<tr>
			<th>집전번</th>
			<td>${member.memHometel }</td>
		</tr>
		<tr>
			<th>회사전번</th>
			<td>${member.memComtel }</td>
		</tr>
		<tr>
			<th>휴대폰</th>
			<td>${member.memHp }</td>
		</tr>
		<tr>
			<th>이메일</th>
			<td>${member.memMail }</td>
		</tr>
		<tr>
			<th>직업</th>
			<td>${member.memJob }</td>
		</tr>
		<tr>
			<th>취미</th>
			<td>${member.memLike }</td>
		</tr>
		<tr>
			<th>기념일</th>
			<td>${member.memMemorial }</td>
		</tr>
		<tr>
			<th>기념일자</th>
			<td>${member.memMemorialday }</td>
		</tr>
		<tr>
			<th>마일리지</th>
			<td>${member.memMileage }</td>
		</tr>
		<tr>
			<th>탈퇴여부</th>
			<td>${member.memDelete }</td>
		</tr>
		<tr>
			<td colspan="2">
				
				<input type="button" value="수정" class="btn btn-primary linkBtn"
					data-href="<c:url value="/member/memberUpdate.do" />"
				/>
				<input type="button" value="탈퇴" id="delBtn" 
					class="btn btn-danger" 
				/>
			</td>
		</tr>
		<tr>
			<th>구매정보</th>
			<td>
				<table class="table table-bordered">
					<thead class="thead-dark">
						<tr>
							<th>상품코드</th>
							<th>상품명</th>
							<th>상품분류명</th>
							<th>상품거래처명</th>
							<th>구매가</th>
							<th>판매가</th>
						</tr>
					</thead>
					<tbody>	
						<c:set var="prodList" value="${member.prodList }" />
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
										<td>${prod.lprodNm }</td>
										<td>${prod.buyerName }</td>
										<td>${prod.prodCost }</td>
										<td>${prod.prodPrice }</td>
										<td>${prod.prodRate }</td>
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr>
									<td colspan="7">구매정보 없음..</td>
								</tr>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
			</td>
		</tr>
	</table>
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <form method="post" action="${pageContext.request.contextPath }/member/memberDelete.do">
	      <div class="modal-body">
	        	비밀번호 : <input type="password" name="memPass" class="form-control"/>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
	        <button type="submit" class="btn btn-danger">탈퇴</button>
	      </div>
      </form>
    </div>
  </div>
</div>
	<script type="text/javascript">
		
		$("#delBtn").on("click", function(){
			exampleModal.modal('show');
		});

		let exampleModal = $("#exampleModal").on("hidden.bs.modal", function(){
			$(this).find("form")[0].reset();
		});
	</script>
	<jsp:include page="/includee/postScript.jsp" />
</body>
</html>














