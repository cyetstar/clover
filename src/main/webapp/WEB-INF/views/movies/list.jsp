<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<title>电影</title>
</head>

<body>
  <%@ include file="/WEB-INF/views/movies/_header.jsp"%>
  <div class="container">
    <fieldset>
      <legend>
        <small>影片清单</small>
      </legend>
      <div class="sort-dropdown dropdown pull-right">
        <a class="dropdown-toggle" data-toggle="dropdown" href="javascript:void()"> <c:choose>
            <c:when test="${page.sort=='year: ASC'}">按年份排序</c:when>
            <c:when test="${page.sort=='rating: ASC'}">按评分排序</c:when>
            <c:otherwise>按默认排序</c:otherwise>
          </c:choose> <b class="caret"></b>
        </a>
        <ul class="dropdown-menu">
          <li class="disabled"><a href="#">选择排序方式</a></li>
          <li class="divider"></li>
          <li><a href="${ctx}/movies?sz=${page.size}&sort=year:desc">按年份排序</a></li>
          <li><a href="${ctx}/movies?sz=${page.size}&sort=rating:desc,numRaters:desc">按评分排序</a></li>
          <li><a href="${ctx}/movies?sz=${page.size}">按默认排序</a></li>
        </ul>
      </div>
      <table class="table table-striped table-hover">
        <thead>
          <tr>
          	<th width="10"></th>
            <th width="24">序号</th>
            <th>影片名（原名）</th>
            <th>年份</th>
            <th>评分</th>
          </tr>
        </thead>
        <tbody>
          <c:if test="${empty page.content}">
            <tr>
              <td colspan="5">没有查询符合条件的记录。</td>
            </tr>
          </c:if>
          <c:if test="${!empty page}">
            <c:forEach items="${page.content}" var="item" varStatus="status">
              <tr>
              	<td><input type="radio" class="pull-left" name="id" value="${item.id}"/></td>
                <td>${status.index + 1}</td>
                <td><a class="view" href="${ctx}/movies/${item.id}">${item.title}</a><c:if test="${!empty item.originalTitle}">（${item.originalTitle}）</c:if></td>
                <td>${item.year}</td>
                <td>${item.rating}</td>
              </tr>
            </c:forEach>
          </c:if>
        </tbody>
      </table>
      <tags:pagination path="${ctx}/movies" page="${page}" paramMap="${params}"/>
      <div id="button-group" class="clearfix pull-left">
        <a class="btn btn-small btn-primary btn-add" href="${ctx}/movies/add">新增</a>
        <a class="btn btn-small btn-primary btn-edit" href="javascript:">编辑</a>
        <a class="btn btn-small btn-primary btn-delete" href="javascript:">删除</a>
      </div>
    </fieldset>
  </div>
  
  <script type="text/javascript">
  	$('.table > tbody').delegate('tr', 'click', function(event){
  		if(!$(event.target).is('.view')){
  			$(this).find(':radio').attr('checked', true);
  		}
  	});
  	$('.btn-edit').on('click',function(){
  		window.location.href = '${ctx}/movies/edit/'+$(':radio:checked').val();
  	})
  	$('.btn-delete').on('click',function(){
  		var $form = $('<form method="post"></form>').attr('action', '${ctx}/movies/delete/'+$(':radio:checked').val());
  		$form.submit();
  	})
  </script>

</body>
</html>