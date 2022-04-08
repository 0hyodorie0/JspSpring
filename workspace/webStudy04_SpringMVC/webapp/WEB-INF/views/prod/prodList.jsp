<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp" />
<script type="text/javascript" src="${cPath }/resources/js/jquery.form.min.js"></script>
</head>
<body>
   <!-- model name : prodList -->
   <!-- 상품 분류별로 정렬화되, 최근 등록 상품이 먼저 조회됨. -->
   <table class="table table-bordered">
      <thead class="thead-dark">
         <tr>
            <th>일련번호</th>
            <th>상품코드</th>
            <th>상품명</th>
            <th>분류명</th>
            <th>거래처명</th>
            <th>총구매자수</th>
            <th>마일리지</th>
         </tr>
      </thead>
      <tbody id="listBody">
         <c:set var="prodList" value="${paging.dataList }"></c:set>
         <c:choose>
            <c:when test="${empty prodList} ">
               <tr>
                  <td colspan="6">조건에 맞는 상품이 없습니다.</td>
               </tr>
            </c:when>

            <c:otherwise>
               <c:forEach items="${prodList}" var="prod">
                  <c:url value="/prod/prodView.do" var="viewURL">
                     <c:param name="what" value="${prod.prodId }"></c:param>
                  </c:url>
                  <tr class="linkBtn" data-href="${viewURL }">
                     <td>${prod.rnum }</td>
                     <td>${prod.prodId }</td>
                     <td>${prod.prodName }</td>
                     <td>${prod.lprodNm }</td>
                     <td>${prod.buyer.buyerName }</td>
                     <td>${prod.memCnt }</td>
                     <td>${prod.prodMileage }</td>
                  </tr>
               </c:forEach>
            </c:otherwise>
         </c:choose>

      </tbody>
      <tfoot>
         <tr>
            <td colspan="6">
                  <div id="pagingArea">
                     ${paging.pagingHTMLBS }
                  </div>
                  <div id="searchDIV">
                     <select name="prodLgu">
                     </select>
                     <select name="prodBuyer">
                     </select>
                     <input type="text" name="prodName" />
                     <input type="button" value="검색" id="searchBth"/>
                  </div>
               </td>
            </tr>
         </tfoot>
      </table>


   <form id="searchForm">
      <input type="text" name="page">
      <input type="text" name="prodLgu">
      <input type="text" name="prodBuyer">
      <input type="text" name="prodName">
   </form>
   
   <script type="text/javascript">
      $.ajax({
         url : "${pageContext.request.contextPath}/prod/getLprodList.do",
         dataType : "json",
         success : function(resp) {
            let lprodList = resp.lprodList;
            let options = [];
            options.push($("<option>").attr("value", "").text("상품분류"));
            $(lprodList).each(
                  function(index, lprod) {
                     let option = $("<option>").attr("value",
                           lprod.lprodGu).text(lprod.lprodNm);
                     options.push(option);
                  });
            searchDIV.find("[name=prodLgu]").append(options);
            $(prodLguTag).val("${paging.detailCondition.prodLgu}");
            $(prodLguTag).trigger("change");
         },
         error : function(jqXHR, textStatus, errorThrown) {
            console.log(jqXHR);
            console.log(textStatus);
            console.log(errorTheown);
         }
      });
      const VIEWURLPTRN = "${cPath}/prod/prodView.do?what=%V";
      let listBody = $("#listBody");
      let pagingArea = $("#pagingArea");
      
      let searchForm = $("#searchForm").ajaxForm({
         dataType:"json"
         , success: function(paging){
            listBody.empty();
            pagingArea.empty();
            searchForm[0].page.value="";
            
            let prodList = paging.dataList;
            let trTags = [];
            if(prodList && prodList.length > 0){
               $.each(prodList,function(idx, prod){
                  let viewURL = VIEWURLPTRN.replace("%V",prod.prodId);
                  let trTag = $("<tr>").addClass("linkBtn").data("href",viewURL).append(
                           $("<td>").html(prod.rnum)
                           ,$("<td>").html(prod.prodId)
                           ,$("<td>").html(prod.prodName)
                           ,$("<td>").html(prod.lprodNm)
                           ,$("<td>").html(prod.buyer.buyerName)
                           ,$("<td>").html(prod.memCnt)
                           ,$("<td>").html(prod.prodMileage)
                  );
                  trTags.push(trTag);
               });
            }else{
               let trTag = $("<tr>").html($("<td>").attr("colspan","6").html("조건에 맞는 상품이 없음."));
               trTags.push(trTag);
            }// if end
            listBody.append(trTags);
            pagingArea.html(paging.pagingHTMLBS);
         }// success end
         , resetForm : false
      }).submit();
      
      
      let searchDIV = $("#searchDIV");

      let prodLguTag = $("[name=prodLgu]").on("change",function() {
         let selectedLgu = $(this).val();
            $.ajax({
               url : "${pageContext.request.contextPath}/prod/getBuyerList.do",
               data : {
                     lprodGu : selectedLgu
                     },
               dataType : "json",
               success : function(resp) {
               searchDIV.find("[name=prodBuyer]").empty();
                  let buyerList = resp;
                  let options = [];
                  options.push($("<options>").attr("value", "").text("거래처"));
                     $(buyerList).each(function(index,buyer) {
                        let option = $("<option>").attr("value",buyer.buyerId).addClass(buyer.buyerLgu)
                        .text(buyer.buyerName);
                        options.push(option)
                     });
                  searchDIV.find("[name=prodBuyer]").append(options);
                  $(prodBuyerTag).val("${param.prodBuyer}");
                  },
               cache : true,
               error : function(jqXHR, textStatus,errorThrown) {
                  console.log(jqXHR);
                  console.log(textStatus);
                  console.log(errorTheown);
                  }
               });
            });
      let prodBuyerTag = $("[name=prodBuyer]");
   
      $("[name=prodName]").val("${paging.detailCondition.prodName}");

      pagingArea.on("click", "a", function() {
         let page = $(this).data("page");
         searchForm.find("[name=page]").val(page);
         searchForm.submit();
      });
      $("#searchBth").on("click", function() {
         let inputs = searchDIV.find("[name]");
         $(inputs).each(function(index, ipt) {
            let name = this.name;
            let value = $(this).val();
            searchForm.find("[name=" + name + "]").val(value);
         });
         searchForm.submit();
      });
      
      
   </script>
   <jsp:include page="/includee/postScript.jsp" />
</body>
</html>
