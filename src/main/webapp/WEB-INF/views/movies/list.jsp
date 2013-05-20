<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<title>电影</title>
</head>

<body>
  <%@ include file="movieHeader.jsp"%>
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
            <th>序号</th>
            <th>影片名（原名）</th>
            <th>年份</th>
            <th>评分</th>
          </tr>
        </thead>
        <tbody>
          <c:if test="${empty page.content}">
            <tr>
              <td colspan="4">没有查询符合条件的记录。</td>
            </tr>
          </c:if>
          <c:if test="${!empty page}">
            <c:forEach items="${page.content}" var="item" varStatus="status">
              <tr>
                <td>${status.index + 1}</td>
                <td><a href="${ctx}/movies/${item.id}">${item.title}</a><c:if test="${!empty item.originalTitle}">（${item.originalTitle}）</c:if></td>
                <td>${item.year}</td>
                <td>${item.rating}</td>
              </tr>
            </c:forEach>
        </tbody>
        </c:if>
      </table>
      <tags:pagination path="${ctx}/movies" page="${page}" paramMap="${params}"/>
      <div id="button-group" class="clearfix pull-left">
        <a class="btn btn-small btn-primary" href="${ctx}/movies/new" data-toggle="modal" data-target="#movie-modal">新增</a>
        <a class="btn btn-small btn-primary" href="remote.html" data-toggle="modal" data-target="#modal">编辑</a>
        <a class="btn btn-small btn-primary" href="remote.html" data-toggle="modal" data-target="#modal">删除</a>
      </div>
    </fieldset>
  </div>

  <div id="movie-modal" class="modal hide fade" tabindex="-1" role="dialog">
    <div class="modal-header">
      <button type="button" class="close" data-dismiss="modal">×</button>
      <h3 id="myModalLabel">Modal header</h3>
    </div>
    <div class="modal-body">
      <p>One fine body…</p>
    </div>
    <div class="modal-footer">
      <button class="btn" data-dismiss="modal">Close</button>
      <button class="btn btn-primary">Save changes</button>
    </div>
  </div>
</body>
</html>