<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<title>${movie.title} · 电影</title>
</head>

<body>
  <%@ include file="movieHeader.jsp"%>
  <div class="container">
    <div class="row">
      <h3 id="show-title">
        ${movie.title} ${movie.originalTitle}<small>(${movie.year})</small>
      </h3>
      <div id="show-content" class="span9">
        <div class="row">
          <div id="show-poster" class="span2">
            <img alt="" src="${ctx}/${movie.smallPoster}">
            <div>
            	<a href="${ctx}/movies/uploadPoster/${movie.id}">上传海报</a>
            </div>
          </div>
          <div id="show-detail" class="span5">
            <dl class="dl-horizontal">
              <c:if test="${!empty movie.directors}">
                <dt>导演:</dt>
                <dd>
                  <c:forEach items="${movie.directors}" var="item" varStatus="status">
                    <a href="${ctx}/celebrities/${item.celebrity.id}">${item.celebrity.name}</a>
                    <c:if test="${!status.last}">/</c:if>
                  </c:forEach>
                </dd>
              </c:if>
              <c:if test="${!empty movie.casts}">
                <dt>主演:</dt>
                <dd>
                  <c:forEach items="${movie.casts}" var="item" varStatus="status">
                    <a href="${ctx}/celebrities/${item.celebrity.id}">${item.celebrity.name}</a>
                    <c:if test="${!status.last}">/</c:if>
                  </c:forEach>
                </dd>
              </c:if>
              <c:if test="${!empty movie.writers}">
                <dt>编剧:</dt>
                <dd>
                  <c:forEach items="${movie.writers}" var="item" varStatus="status">
                    <a href="${ctx}/celebrities/${item.celebrity.id}">${item.celebrity.name}</a>
                    <c:if test="${!status.last}">/</c:if>
                  </c:forEach>
                </dd>
              </c:if>
              <c:if test="${!empty movie.genres}">
                <dt>类型:</dt>
                <dd>
                  <c:forEach items="${movie.genres}" var="item" varStatus="status">
                    <a href="#">${item.value}</a>
                    <c:if test="${!status.last}">/</c:if>
                  </c:forEach>
                </dd>
              </c:if>
              <c:if test="${!empty movie.countries}">
                <dt>制片国家/地区:</dt>
                <dd>
                  <c:forEach items="${movie.countries}" var="item" varStatus="status">
                    <a href="#">${item.value}</a>
                    <c:if test="${!status.last}">/</c:if>
                  </c:forEach>
                </dd>
              </c:if>
              <c:if test="${!empty movie.languages}">
                <dt>语言:</dt>
                <dd>
                  <c:forEach items="${movie.languages}" var="item" varStatus="status">
                    <a href="#">${item.value}</a>
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
          <div id="rating" class="span2">
            <h4><span class="rating">${movie.rating}</span><i class="icon-refresh hide" title="更新评分"></i></h4>
            <div>(<span class="numRaters">${movie.numRaters}</span>人评价)</div>
          </div>
        </div>
        <p id="show-article">${movie.summary}</p>
      </div>
      <div class="span3"></div>
    </div>
  </div>
<script type="text/javascript">
$('#rating').hover(function(){
	$('.icon-refresh').removeClass('hide');
}, function(){
	$('.icon-refresh').addClass('hide');
})
$('.icon-refresh').on('click', function(){
	$.ajax({
		url : '${ctx}/movies/updateRating',
		data : {id:${movie.id}, doubanId:${movie.doubanId}},
		type : 'post',
		success : function(result){
			if(result.success){
				$('#rating .rating').text(result.data.ave);
				$('#rating .numRaters').text(result.data.num);
			}
		}
	})
	
})
</script>
</body>
</html>