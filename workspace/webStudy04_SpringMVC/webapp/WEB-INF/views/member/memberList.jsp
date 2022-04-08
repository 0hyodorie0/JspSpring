<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp" />
<script src="${cPath}/resources/js/jquery.form.min.js"></script> 
</head>
<body>
<h4> 회원 목록 </h4>
<table class="table table-striped">
   <thead class="thead-dark">
      <tr>
         <th>일련번호</th>
         <th>회원아이디</th>
         <th>회원명</th>
         <th>지역</th>
         <th>휴대폰</th>
         <th>이메일</th>
         <th>마일리지</th>
         <th>탈퇴여부</th>
      </tr>
   </thead>
   <tbody id="listBody">
      
   </tbody>
   <tfoot>
      <tr>
      <td colspan="8">
       <div id="pagingArea">
<%--                ${paging.pagingHTMLBS } --%>
            </div>
         <div id="searchDIV">
            <select name="searchType">
               <option value>전체</option>
               <option value="NAME">이름</option>
               <option value="ADDRESS">주소</option>
            </select>
            <input type="text" name="searchWord" />
            <input type="button" value="검색" />
         </div>
      </td>
      </tr>
   </tfoot>
</table>
<form id="searchForm">
   <input type="text" name="page" />
   <input type="text" name="searchType" />
   <input type="text" name="searchWord" />
</form>
<script type="text/javascript">
   $(".memberTr").on("click", function(){
       let memId = $(this).data('memId');
       location.href="${pageContext.request.contextPath }/member/memberView.do?who="+memId;
   }).css('cursor', 'pointer');
   
   $("[name=searchType]").val("${paging.simpleCondition.searchType}");
   $("[name=searchWord]").val("${simpleCondition.searchWord}");
   
   const VIEWURLPTRN = "${cPath}/member/memberView.do?who=%V";
   let listBody = $("#listBody");
   let pagingArea = $("#pagingArea");
   
   const searchForm = $("#searchForm").ajaxForm({
      dataType:"json"
      , success : function(paging){
         listBody.empty();
         pagingArea.empty();
         searchForm[0].page.value="";
         let memberList = paging.dataList;
         let trTags = [];
         if(memberList && memberList.length > 0){
            $(memberList).each(function(idx,member){//---------------------------------------------에러날수도있음
               let ViewURL = VIEWURLPTRN.replace("%V", member.memId);
               let trTag = $("<tr>").addClass("linkBtn")
                              .data("memId",ViewURL)
                              .append(
                              $("<td>").html(member.rnum)      
                              ,$("<td>").html(member.memId)      
                              ,$("<td>").html(member.memName)      
                              ,$("<td>").html(member.memAdd1)      
                              ,$("<td>").html(member.memHp)      
                              ,$("<td>").html(member.memMail)      
                              ,$("<td>").html(member.memMileage)      
                              ,$("<td>").html(member.memDelete)      
                              );
               trTags.push(trTag);
            });
         }else{
            let trTag = $("<tr>").html(
               $("<td>").attr("colspan","8")
                     .html("조건에 맞는 회원이 없음")
            )
            trTags.push(trTag);
         }// if end
         listBody.append(trTags);
         pagingArea.html(paging.pagingHTMLBS);
      }//end success
      , resetForm : false
   }).submit();
   
   
   const searchDIV = $("#searchDIV").on("click", "[type=button]", function(){
      let inputs = searchDIV.find(":input[name]");
      $(inputs).each(function(index, ipt){
         let name = this.name;
         let value = $(this).val();
         searchForm.find("[name="+name+"]").val(value);
      });
      searchForm.submit();
   });
   
   pagingArea.on("click", "a", function(){
      let page = $(this).data("page");
      searchForm.find("[name=page]").val(page);
      searchForm.submit();
   });
</script>
<jsp:include page="/includee/postScript.jsp" />
</body>
</html>
