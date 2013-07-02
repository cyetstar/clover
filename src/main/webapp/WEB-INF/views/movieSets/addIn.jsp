<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
</head>
<body>
<div id="addin">
  <a id="add-set" href="javascript:;" class="btn btn-mini">新增影集</a>
  <div class="scroll">
  <ul>
    <c:forEach items="${page.content}" var="movieSet">
      <li>
        <span class="heading"><a href="">${movieSet.title}</a></span>
        <p>${movieSet.summary}</p>
      </li>
    </c:forEach>
  </ul>
  </div>
</div>
</body>
<script type="text/javascript">
	$('#add-set').on('click', function(){
		$('#addin').load('${ctx}/movieSets/add?movieId=${movieId}');
	})
</script>
</html>



