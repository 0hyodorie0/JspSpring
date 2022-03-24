<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<jsp:include page="/includee/preScript.jsp" />

<%
	String message = (String) session.getAttribute("message");
	if(StringUtils.isNotBlank(message)){
		%>
		<script>
			alert("<%=message %>");
		</script>
		<%
		session.removeAttribute("message"); // flash attribute
	}
%>

</head>
<body>
<%
	MemberVO member = (MemberVO)request.getAttribute("member");
%>
	<table class="table table-bordered">
		<tr>
			<th>회원아이디</th>
			<td><%=member.getMemId()%></td>
		</tr>
		<tr>
			<th>회원명</th>
			<td><%=member.getMemName()%></td>
		</tr>
		<tr>
			<th>주민번호1</th>
			<td><%=member.getMemRegno1()%></td>
		</tr>
		<tr>
			<th>주민번호2</th>
			<td><%=member.getMemRegno2()%></td>
		</tr>
		<tr>
			<th>생일</th>
			<td><%=member.getMemBir()%></td>
		</tr>
		<tr>
			<th>우편번호</th>
			<td><%=member.getMemZip()%></td>
		</tr>
		<tr>
			<th>주소1</th>
			<td><%=member.getMemAdd1()%></td>
		</tr>
		<tr>
			<th>주소2</th>
			<td><%=member.getMemAdd2()%></td>
		</tr>
		<tr>
			<th>집전번</th>
			<td><%=member.getMemHometel()%></td>
		</tr>
		<tr>
			<th>회사전번</th>
			<td><%=member.getMemComtel()%></td>
		</tr>
		<tr>
			<th>휴대폰</th>
			<td><%=member.getMemHp()%></td>
		</tr>
		<tr>
			<th>이메일</th>
			<td><%=member.getMemMail()%></td>
		</tr>
		<tr>
			<th>직업</th>
			<td><%=member.getMemJob()%></td>
		</tr>
		<tr>
			<th>취미</th>
			<td><%=member.getMemLike()%></td>
		</tr>
		<tr>
			<th>기념일</th>
			<td><%=member.getMemMemorial() %></td>
		</tr>
		<tr>
			<th>기념일자</th>
			<td><%=member.getMemMemorialday() %></td>
		</tr>
		<tr>
			<th>마일리지</th>
			<td><%=member.getMemMileage() %></td>
		</tr>
		<tr>
			<th>탈퇴여부</th>
			<td><%=member.getMemDelete() %></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="button" value="수정" class="linkBtn"
					data-href="${pageContext.request.contextPath }/member/memberUpdate.do"
				/>
				<input type="button" value="탈퇴" id="delBtn" 
					class="btn btn-primary" 
				/>
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














