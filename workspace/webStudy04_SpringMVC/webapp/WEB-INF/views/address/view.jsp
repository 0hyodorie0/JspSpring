<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="${cPath}/resources/js/jquery.form.min.js"></script> 
<h4>주소록 관리 기능을 전체 비동기 처리로 </h4>
<button id="read" data-gopage="${cPath }/address">주소록 전체 조회</button>
<form action="${cPath }/address/new" method="post" id="addrForm">
   <input type="text" name="addName" placeholder="이름">
   <input type="text" name="addHp" placeholder="휴대폰">
   <input type="text" name="address" placeholder="주소">
   <button type="submit">주소록 등록</button>
</form>
<table class="table table-striped">
   <thead class="thead-dark">
      <tr>
         <th>주소록ID</th>      
         <th>이름</th>      
         <th>휴대폰</th>      
      </tr>
   </thead>   
   <tbody id="listBody">
   </tbody>
</table>
<div id="detailArea">

</div>
<script type="text/javascript">
   let addrForm = $("#addrForm").ajaxForm({
      dataType : "json"
      , success : function(resultMap){
         if(resultMap.result=="OK"){
         listBody.empty();
         readBtn.trigger("click");
         }else{
        	 detailArea.html(JSON.stringify( resultMap.errors));
         }
      }, resetForm: true
      
   });

   let listBody = $("#listBody").on("click","tr", function(){
	   let addId = $(this).data("addId");
	   $.ajax({
		   url:"${cPath}/address/"+addId,
		   dataType:"json",
		   success:function(addVO){
			   detailArea.html(
					addVO.addId+", "+addVO.addName+", "
					+addVO.addHp+", "+addVO.address
			   );
		   },
		   error : function(jqXHR, textStatus, errorThrown) {
				console.log(jqXHR);
				console.log(textStatus);
				console.log(errorThrown);
			}
	   })
   });
   
   let readBtn= $("#read").on("click",function(){
      let url = $(this).data("gopage");
      $.ajax({
         url : url,
         dataType : 'json'
         ,success : function(list){
//              listBody.empty();
             let trTags = [];
             $.each(list, function(idx, addVO) {
               let trTag =  $("<tr>").append(
                            $("<td>").html(addVO.addId)
                            ,$("<td>").html(addVO.addName)
                            ,$("<td>").html(addVO.addHp)
                         ).data("addId", addVO.addId);
               trTags.push(trTag);
             }); //each
             listBody.append(trTags);
         }//end success
         ,error : function(jqXHR, textStatus, errorThrown) {
            console.log(jqXHR);
            console.log(textStatus);
            console.log(errorThrown);
         }
      });
   })
   
   
      
   
</script>