<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/includee/preScript.jsp" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery.fancytree/2.38.1/skin-win8-xxl/ui.fancytree.min.css" integrity="sha512-94Caq+2iDRtltPsQnhk7VXaCCqe5iUnpDJf/MgiczE03bTAHmYE5SLlQjecNDnBwptRrVtuLOBGhlLoF5L9iyA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.fancytree/2.38.1/jquery.fancytree-all-deps.min.js" integrity="sha512-3TzrcF4+P4XdfddaIQNLGJ2sxRVat01qd/utG1uEK6S6sO9hNSa07sab2PhGAHxcp/mRU69R77YyC7oAiC4yIg==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
</head>
<body>
<div id="tree">
<c:url value="/server/browsing.do" var="dataURL"></c:url>
<script>
   $("#tree").fancytree({
      source: {
        url : "${dataURL}"
    },
    lazyLoad: function(event, data){
    	console.log(event);
    	console.log(data);
        var node = data.node;
        var currentPath = node.key;
         data.result = {
          url: "${dataURL}",
          data: {
				currentPath : currentPath
          },
          cache: false
        };
    }
 });
</script>
</div>
</body>
</html>
