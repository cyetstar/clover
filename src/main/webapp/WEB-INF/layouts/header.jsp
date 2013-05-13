<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<div id="header" class="navbar navbar-inverse navbar-static-top">
  <div class="navbar-inner ">
    <ul class="nav pull-left">
      <li><a href="${ctx}/">电影</a></li>
      <li><a href="${ctx}/admin/">读书</a></li>
      <li><a href="${ctx}/">音乐</a></li>
      <li><a href="${ctx}/admin/">漫画</a></li>
      <shiro:user>
        <li><a href="${ctx}/logout">退出</a></li>
      </shiro:user>
    </ul>
  </div>
</div>