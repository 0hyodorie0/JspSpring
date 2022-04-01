<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<jsp:include page="/includee/preScript.jsp" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery.fancytree/2.38.1/skin-win8/ui.fancytree.min.css" integrity="sha512-MDYrzSajmNeJrqH4YNzdIEhhQgFKSa06KJYmcbfK3dEwvureKSjAYlcS1fgnuUb0YPwEFTg+NAFaK6sOE6hF6Q==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.fancytree/2.38.1/jquery.fancytree-all-deps.min.js" integrity="sha512-3TzrcF4+P4XdfddaIQNLGJ2sxRVat01qd/utG1uEK6S6sO9hNSa07sab2PhGAHxcp/mRU69R77YyC7oAiC4yIg==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
</head>
<body>
<div id="tree"></div>
<c:url value="/employee/treeView.do" var="dataURL" />
<script type="text/javascript">
	$("#tree").fancytree({
		source: {
			url:"${dataURL}"
		},
		lazyLoad: function(event, data){
		  console.log(event);
		  console.log(data);
	      var node = data.node;
	      var managerId = node.key;
	      // Load child nodes via Ajax GET /getTreeData?mode=children&parent=1234
	      data.result = {
	        url: "${dataURL}",
	        data: {
	        	managerId : managerId
	        },
	        cache: false
	      };
	  	}
	});
</script>
</body>
</html>












