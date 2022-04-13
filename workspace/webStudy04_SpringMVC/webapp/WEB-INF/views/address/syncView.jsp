<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<h4><spring:message code="hi"/></h4>
<spring:message code="address.addId" var="addIdMsg" />
<spring:message code="address.addName" var="addNameMsg" />
<spring:message code="address.addHp" var="addHpMsg" />
<spring:message code="address.address" var="addressMsg" />

<div>
	<form:form modelAttribute="address" action="${cPath }/address/addInsert.do" method="post">
		<form:input path=""/>
	</form:form>
</div>
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
