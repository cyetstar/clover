<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<title>${movie.title} · 电影</title>
<link href="${ctx}/static/sco.js/scojs.css" type="text/css" rel="stylesheet" />
<script src="${ctx}/static/sco.js/sco.modal.js" type="text/javascript"></script>
</head>

<body>
  <%@ include file="/WEB-INF/views/movies/_header.jsp"%>
  <div class="container">
    <div class="row">
      <h3 id="show-title">
        ${movie.title} ${movie.originalTitle}<small>(${movie.year})</small>
      </h3>
      <div id="show-content" class="span9">
        <div id="show-detail" class="row">
          <div id="show-poster" class="span2">
            <img alt="" src="${smallAccessPath}/${movie.poster}">
            <div id="poster-bottom">
            	<a href="${ctx}/movies/uploadPoster/${movie.id}">上传海报</a>
            </div>
          </div>
          <div id="show-items" class="span5">
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
          <div class="btn-group">
            <a class="btn btn-mini dropdown-toggle" data-toggle="dropdown">更多操作
              <span class="caret"></span>
            </a>
            <ul class="dropdown-menu">
              <li><a href="${ctx}/movies/add">新增</a></li>
              <li><a href="${ctx}/movies/edit/${movie.id}">编辑</a></li>
              <li><a href="javascript:;" id="delete-movie">删除</a></li>
            </ul>
          </div>
        </div>
        <p id="show-article">${movie.summary}</p>
        <div id="show-files" class="clearfix">
          <h5>电影文件<small class="pull-right"><a href="${ctx}/movieFiles/add?movieId=${movie.id}" data-trigger="modal" data-title="添加电影文件信息" id="add-file">添加文件</a></small></h5>
          <ul id="files">
            <c:forEach items="${files}" var="file">
              <li class="clearfix" data-file-id="${file.id}">
              <span>${file.filename}</span> 
              <a href="javascript:" class="delete-file btn btn-mini btn-danger">删除</a> 
              <a href="${ctx}/movieFiles/edit/${file.id}" data-trigger="modal" data-title="修改电影文件信息" class="edit btn btn-mini">修改</a>
              </li>
            </c:forEach>
          </ul>
        </div>
      </div>
      <div class="span3">
      <div>
      	<h5>影集<small class="pull-right"><a href="${ctx}/movieSetItems/add?movieId=${movie.id}" data-trigger="modal" data-title="选择影集">加入影集</a></small></h5>
   		<ul id="sets">
          <c:forEach items="${movieSets}" var="movieSet">
          <li><span class="heading"><a href="#" title="${movieSet.title}">${movieSet.title}</a></span></li>
          </c:forEach>
        </ul>
      </div>
      </div>
    </div>
  </div>
<script type="text/javascript">
$('#delete-movie').on('click', function(){
	var $form = $('<form/>', {method: 'post', action: '${ctx}/movies/delete/${movie.id}'});
	$form.submit();
})
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

$('#files').on('click', '.delete-file', function(){
	var self = this;
	var $li = $(self).closest('li');
	var id = $li.attr('data-file-id');
	$.ajax({
		url:'${ctx}/movieFiles/delete/' + id + '.json',
		type:'post',
		success:function(jsondata){
			if(jsondata.status){
				$li.slideUp('slow', function(){
					$(this).remove();
				});
			}
		}
	})
})
</script>
</body>
</html>