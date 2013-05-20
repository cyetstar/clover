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
    <div class="row">
      <div class="span9">
        <h3>${movie.title}<small>${movie.originalTitle}</small></h3>
        <div class="row">
          <div class="span3">
            <img style="width:120px;height:160px;" alt="" src="${ctx}/static/poster/movie/${image}">
          </div>
          <div class="span6">
            <dl class="dl-horizontal">
              <c:if test="${!empty movie.directors}">
                <dt>导演:</dt>
                <dd>
                  <c:forEach items="${movie.directors}" var="item" varStatus="status">${item.celebrity.name}
                  <c:if test="${!status.last}">/</c:if>
                  </c:forEach>
                </dd>
              </c:if>
              <c:if test="${!empty movie.casts}">
                <dt>主演:</dt>
                <dd>
                  <c:forEach items="${movie.casts}" var="item" varStatus="status">${item.celebrity.name}
                  <c:if test="${!status.last}">/</c:if>
                  </c:forEach>
                </dd>
              </c:if>
              <c:if test="${!empty movie.writers}">
                <dt>编剧:</dt>
                <dd>
                  <c:forEach items="${movie.writers}" var="item" varStatus="status">${item.celebrity.name}
                  <c:if test="${!status.last}">/</c:if>
                  </c:forEach>
                </dd>
              </c:if>
              <c:if test="${!empty movie.genres}">
                <dt>类型:</dt>
                <dd>
                  <c:forEach items="${movie.genres}" var="item" varStatus="status">${item.genre}
                  <c:if test="${!status.last}">/</c:if>
                  </c:forEach>
                </dd>
              </c:if>
              <c:if test="${!empty movie.countries}">
                <dt>制片国家/地区:</dt>
                <dd>
                  <c:forEach items="${movie.countries}" var="item" varStatus="status">${item.country}
                <c:if test="${!status.last}">/</c:if>
                  </c:forEach>
                </dd>
              </c:if>
              <c:if test="${!empty movie.languages}">
                <dt>语言:</dt>
                <dd>
                  <c:forEach items="${movie.languages}" var="item" varStatus="status">${item.language}
                <c:if test="${!status.last}">/</c:if>
                  </c:forEach>
                </dd>
              </c:if>
              <c:if test="${!empty movie.durations}">
                <dt>片长:</dt>
                <dd>
                  <c:forEach items="${movie.durations}" var="item" varStatus="status">${item}
                  <c:if test="${!status.last}">/</c:if>
                  </c:forEach>
                </dd>
              </c:if>
              <c:if test="${!empty movie.akas}">
                <dt>又名:</dt>
                <dd>
                  <c:forEach items="${movie.akas}" var="item" varStatus="status">${item.title}
                  <c:if test="${!status.last}">/</c:if>
                  </c:forEach>
                </dd>
              </c:if>
              <c:if test="${!empty movie.imdb}">
                <dt>IMDb链接:</dt>
                <dd>
                  <a target="_blank" href="http://www.imdb.com/title/${movie.imdb}">${movie.imdb}</a>
                </dd>
              </c:if>
              <c:if test="${!empty movie.doubanId}">
                <dt>豆瓣链接:</dt>
                <dd>
                  <a target="_blank" href="http://movie.douban.com/subject/${movie.doubanId}">${movie.doubanId}</a>
                </dd>
              </c:if>
            </dl>

          </div>
        </div>
        <p>${movie.summary}</p>
      </div>
      <div class="span3"></div>
    </div>

  </div>
</body>
</html>