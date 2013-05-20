<%@ tag language="java" pageEncoding="UTF-8" import="java.util.*,org.springframework.data.domain.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="page" type="org.springframework.data.domain.Page" required="true"%>
<%@ attribute name="path" type="java.lang.String" required="true"%>
<%@ attribute name="paramMap" type="java.util.Map" required="false"%>
<%
	StringBuffer param = new StringBuffer("?");
	if (paramMap != null) {
		for (Object key : paramMap.keySet()) {
			if (paramMap.get(key) != null) {
				param.append(key).append("=").append(paramMap.get(key)).append("&");
			}
		}
	}
	if (page.getSort() != null) {
		param.append("sort").append("=");
		for (Iterator<Sort.Order> iter = page.getSort().iterator(); iter.hasNext();) {
			Sort.Order order = iter.next();
			String direction = order.getDirection().toString().toLowerCase();
			param.append(order.getProperty()).append(":").append(direction).append(",");
		}
		param.replace(param.length() - 1, param.length(), "&");
	}
	String url = path + param.toString();
	request.setAttribute("url", url);
	if (page.hasPreviousPage()) {
		request.setAttribute("first", url + "no=1&sz=" + page.getSize());
		request.setAttribute("prev", url + "no=" + page.getNumber() + "&sz=" + page.getSize());
	}
	if (page.hasNextPage()) {
		request.setAttribute("next", url + "no=" + (page.getNumber() + 2) + "&sz=" + page.getSize());
		request.setAttribute("last", url + "no=" + page.getTotalPages() + "&sz=" + page.getSize());
	}
%>
<ul id="pagination" class="pull-right">
  <li>共有${page.totalElements}记录，每页
    <div class="pagesize-dropdown btn-group">
      <a class="btn btn-mini dropdown-toggle" data-toggle="dropdown" href="#">${page.size}</a>
      <ul class="dropdown-menu pull-right">
        <li><a href="${url}no=1&sz=10">10</a></li>
        <li><a href="${url}no=1&sz=20">20</a></li>
        <li><a href="${url}no=1&sz=50">50</a></li>
      </ul>
    </div> 条 ，总共${page.totalPages}页，当前第<input type="text" value="${page.number + 1}" />页
  </li>
  <c:if test="${page.firstPage}">
    <li><a class="btn btn-mini disabled" href="javascript:">首页</a></li>
    <li><a class="btn btn-mini disabled" href="javascript:">上一页</a></li>
  </c:if>
  <c:if test="${!page.firstPage}">
    <li><a class="btn btn-mini" href="${first}">首页</a></li>
    <li><a class="btn btn-mini" href="${prev}">上一页</a></li>
  </c:if>
  <c:if test="${page.lastPage}">
    <li><a class="btn btn-mini disabled" href="javascript:">下一页</a></li>
    <li><a class="btn btn-mini disabled" href="javascript:">尾页</a></li>
  </c:if>
  <c:if test="${!page.lastPage}">
    <li><a class="btn btn-mini" href="${next}">下一页</a></li>
    <li><a class="btn btn-mini" href="${last}">尾页</a></li>
  </c:if>
</ul>