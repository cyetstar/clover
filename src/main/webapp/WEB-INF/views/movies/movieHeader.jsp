<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div id="sub-nav">
  <div class="container">
    <h3 id="sub-title">电影</h3>
    <form method="post" action="${ctx}/movies">
      <div id="sub-search" class="input-append">
        <input type="hidden" name="sz" value="${page.size}" /> <input class="span3" name="keywords" type="text"
          value="${param.keywords}">
        <button class="btn" type="button">查询</button>
      </div>
    </form>
  </div>
</div>
