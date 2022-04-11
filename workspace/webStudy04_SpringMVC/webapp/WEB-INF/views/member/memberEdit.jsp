<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<form method="post" enctype="multipart/form-data">
	<table class="table table-bordered">
		<tr>
			<th>회원아이디</th>
			<td>
				<input class="form-control" required type="text"
					name="memId" value="${member.memId }" />
				<span>${errors['memId'] }</span>
			</td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td>
				<input class="form-control" required type="text" name="memPass"/>
				<span>${errors['memPass'] }</span>
			</td>
		</tr>
		<tr>
			<th>회원이미지</th>
			<td>
				<c:if test="${not empty member.memImg }">
					<img src="data:image/*;base64,${member.memImgBase64 }" />
				</c:if>
				<input type="file" name="memImage" class="form-control"/>
			</td>
		</tr>
		<tr>
			<th>회원명</th>
			<td>
				<input class="form-control" required type="text"
					name="memName" value="${member.memName }" />
				<span>${errors['memName'] }</span>
			</td>
		</tr>
		<tr>
			<th>주민번호1</th>
			<td>
				<input class="form-control" type="text" name="memRegno1"
					value="${member.memRegno1 }" />
				<span>${errors['memRegno1'] }</span>
			</td>
		</tr>
		<tr>
			<th>주민번호2</th>
			<td>
				<input class="form-control" type="text" name="memRegno2"
					value="${member.memRegno2 }" />
				<span>${errors['memRegno2'] }</span>
			</td>
		</tr>
		<tr>
			<th>생일</th>
			<td>
				<input class="form-control" type="date" name="memBir"
					value="${member.memBir }" />
				<span>${errors['memBir'] }</span>
			</td>
		</tr>
		<tr>
			<th>우편번호</th>
			<td>
				<input class="form-control" required type="text"
					name="memZip" value="${member.memZip }" />
				<span>${errors['memZip'] }</span>
			</td>
		</tr>
		<tr>
			<th>주소1</th>
			<td>
				<input class="form-control" required type="text"
					name="memAdd1" value="${member.memAdd1 }" />
				<span>${errors['memAdd1'] }</span>
			</td>
		</tr>
		<tr>
			<th>주소2</th>
			<td>
				<input class="form-control" required type="text"
					name="memAdd2" value="${member.memAdd2 }" />
				<span>${errors['memAdd2'] }</span>
			</td>
		</tr>
		<tr>
			<th>집전번</th>
			<td>
				<input class="form-control" type="text" name="memHometel"
					value="${member.memHometel }" />
				<span>${errors['memHometel'] }</span>
			</td>
		</tr>
		<tr>
			<th>회사전번</th>
			<td>
				<input class="form-control" type="text" name="memComtel"
					value="${member.memComtel }" />
				<span>${errors['memComtel'] }</span>
			</td>
		</tr>
		<tr>
			<th>휴대폰</th>
			<td>
				<input class="form-control" type="text" name="memHp"
					value="${member.memHp }" />
				<span>${errors['memHp'] }</span>
			</td>
		</tr>
		<tr>
			<th>이메일</th>
			<td>
				<input class="form-control" required type="text"
					name="memMail" value="${member.memMail }" />
				<span>${errors['memMail'] }</span>
			</td>
		</tr>
		<tr>
			<th>직업</th>
			<td>
				<input class="form-control" type="text" name="memJob"
					value="${member.memJob }" />
				<span>${errors['memJob'] }</span>
			</td>
		</tr>
		<tr>
			<th>취미</th>
			<td>
				<input class="form-control" type="text" name="memLike"
					value="${member.memLike }" />
				<span>${errors['memLike'] }</span>
			</td>
		</tr>
		<tr>
			<th>기념일</th>
			<td>
				<input class="form-control" type="text" name="memMemorial"
					value="${member.memMemorial }" />
				<span>${errors['memMemorial'] }</span>
			</td>
		</tr>
		<tr>
			<th>기념일자</th>
			<td>
				<input class="form-control" type="date"
					name="memMemorialday" value="${member.memMemorialday }" />
				<span>${errors['memMemorialday'] }</span>
			</td>
		</tr>
		<tr>
			<th>마일리지</th>
			<td>${member.memMileage }</td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="저장" class="btn btn-success" /> 
				<input type="reset" value="취소" class="btn btn-secondary"/>
			</td>
		</tr>
	</table>
</form>





