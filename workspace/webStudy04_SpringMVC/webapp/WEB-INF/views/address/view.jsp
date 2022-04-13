<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h4> 주소록 관리 기능을 전체 비동기 처리로. </h4>
<button id="read" data-gopage="${cPath }/address">주소록 전체 조회</button>
<form action="${cPath }/address/new" method="post" id="addrForm">
	<input type="text" name="addName" placeholder="이름" />
	<input type="text" name="addHp" placeholder="휴대폰" />
	<input type="text" name="address" placeholder="주소" />
	<button type="submit">주소록 등록</button>
</form>
<table>
	<thead>
		<tr>
			<th>주소록ID</th>
			<th>이름</th>
			<th>휴대폰</th>
			<th>주소</th>
		</tr>
	</thead>
	<tbody>
	
	</tbody>
</table>