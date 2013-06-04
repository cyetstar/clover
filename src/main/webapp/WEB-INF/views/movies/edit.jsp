<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<title>编辑电影</title>
</head>
<body>
	<%@ include file="movieHeader.jsp"%>
	<div class="container">
		<fieldset>
			<legend>
				<small>编辑“${movie.title}”电影</small>
			</legend>
			<form action="${ctx}/movies/update" method="post" class="form-horizontal form-inline">
				<input type="hidden" name="id" value="${movie.id}" />
				<div class="control-group">
					<label class="control-label" for="originalTitle">原名</label>
					<div class="controls">
						<input type="text" id="originalTitle" value="${movie.originalTitle}">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="title">简体中文名</label>
					<div class="controls">
						<input type="text" id="title" value="${movie.title}">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">又名</label>
					<c:if test="${empty movie.akas}">
						<div class="controls">
							<input type="hidden" name="akaId"> 
							<input type="text" name="akaTitle">
							<span class="help-inline sign-help-inline">
								<b class="plus" title="添加更多">&#43;</b>
							</span>
						</div>
					</c:if>
					<c:forEach items="${movie.akas}" var="aka" varStatus="st">
						<div class="controls">
							<input type="hidden" name="akaId" value="${aka.id}"> 
							<input type="text" name="akaTitle" value="${aka.title}">
							<span class="help-inline sign-help-inline">
								<c:if test="${st.first}">
									<b class="plus" title="添加更多">&#43;</b>
								</c:if>
								<c:if test="${!st.first}">
									<b class="minus" title="删除">&minus;</b>
								</c:if>
							</span>
						</div>
					</c:forEach>
				</div>
				<div class="control-group">
					<label class="control-label" for="imdb">IMDb编号</label>
					<div class="controls">
						<input type="text" id="imdb" name="imdb" value="${movie.imdb}">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">导演</label>
					<c:if test="${empty movie.directors}">
						<div class="controls">
							<input type="hidden" name="directorId"> 
							<input type="hidden" name="directorCelebrityId"> 
							<input type="text" name="directorName" class="typeahead" autocomplete="off"> 
							<span class="help-inline sign-help-inline">
								<b class="plus" title="添加更多">&#43;</b>
							</span>
						</div>
					</c:if>
					<c:forEach items="${movie.directors}" var="director" varStatus="st">
						<div class="controls">
							<input type="hidden" name="directorId" value="${director.id}">
							<input type="hidden" name="directorCelebrityId" value="${director.celebrity.id}"> 
							<input type="text" name="directorName" value="${director.celebrity.name}" class="typeahead" autocomplete="off"> 
							<span class="help-inline sign-help-inline"> 
								<c:if test="${st.first}">
									<b class="plus" title="添加更多">&#43;</b>
								</c:if> <c:if test="${!st.first}">
									<b class="minus" title="删除">&minus;</b>
								</c:if>
							</span>
						</div>
					</c:forEach>
				</div>
				<div class="control-group">
					<label class="control-label">编剧</label>
					<c:if test="${empty movie.writers}">
						<div class="controls">
							<input type="hidden" name="writerId"> 
							<input type="hidden" name="writerCelebrityId"> 
							<input type="text" name="writerName" class="typeahead" autocomplete="off"> 
							<span class="help-inline sign-help-inline">
								<b class="plus" title="添加更多">&#43;</b>
							</span>
						</div>
					</c:if>
					<c:forEach items="${movie.writers}" var="writer" varStatus="st">
						<div class="controls">
							<input type="hidden" name="writerId" value="${writer.id}"> 
							<input type="hidden" name="writerCelebrityId" value="${writer.celebrity.id}"> 
							<input type="text" name="writerName" value="${writer.celebrity.name}" class="typeahead"autocomplete="off">
							<span class="help-inline sign-help-inline"> 
								<c:if test="${st.first}">
									<b class="plus" title="添加更多">&#43;</b>
								</c:if> <c:if test="${!st.first}">
									<b class="minus" title="删除">&minus;</b>
								</c:if>
							</span>
						</div>
					</c:forEach>
				</div>
				<div class="control-group">
					<label class="control-label">主演</label>
					<c:if test="${empty movie.casts}">
						<div class="controls">
							<input type="hidden" name="castId"> 
							<input type="hidden" name="castCelebrityId"> 
							<input type="text" name="castName" class="typeahead" autocomplete="off"> 
							<span class="help-inline sign-help-inline">
								<b class="plus" title="添加更多">&#43;</b>
							</span>
						</div>
					</c:if>
					<c:forEach items="${movie.casts}" var="cast" varStatus="st">
						<div class="controls">
							<input type="hidden" name="castId" value="${cast.id}"> 
							<input type="hidden" name="castCelebrityId" value="${cast.celebrity.id}"> 
							<input type="text" name="castName" value="${cast.celebrity.name}" class="typeahead"	autocomplete="off"> 
							<span class="help-inline sign-help-inline"> 
								<c:if test="${st.first}">
									<b class="plus" title="添加更多">&#43;</b>
								</c:if> <c:if test="${!st.first}">
									<b class="minus" title="删除">&minus;</b>
								</c:if>
							</span>
						</div>
					</c:forEach>
				</div>
				<div class="control-group">
					<label class="control-label">类型</label>
					<div class="controls">
						<c:forEach items="${genres}" var="genre">
							<label class="checkbox inline"> 
							<input type="checkbox" name="movieGenreId" value="${genre.id}"
								<c:forEach items="${movie.genres}" var="checked">
									<c:if test="${checked.id eq genre.id}">checked="checked"</c:if>
								</c:forEach> />${genre.value}
							</label>
						</c:forEach>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">制片国家/地区</label>
					<div class="controls">
						<c:forEach items="${countries}" var="country">
							<label class="checkbox inline country-label"> 
							<input type="checkbox" name="movieCountryId" value="${country.id}"
								<c:forEach items="${movie.countries}" var="checked">
									<c:if test="${checked.id eq country.id}">checked="checked"</c:if>
								</c:forEach> />${country.value}
							</label>
						</c:forEach>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">语言</label>
					<div class="controls">
						<c:forEach items="${languages}" var="language" varStatus="st">
							<label class="checkbox inline language-label"> 
							<input type="checkbox" name="movieLanguageId" value="${language.id}"
								<c:forEach items="${movie.languages}" var="checked">
									<c:if test="${checked.id eq language.id}">checked="checked"</c:if>
								</c:forEach> />${language.value}
							</label>
							<c:if test="${(st.index+1) % 8 == 0}">
								<br />
							</c:if>
						</c:forEach>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">片长</label>
					<div class="controls">
						<input type="text" value="${movie.duration}">
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">剧情简介</label>
					<div class="controls">
						<textarea rows="7" style="width: 350px">${movie.summary}</textarea>
					</div>
				</div>
				<div class="form-actions">
					<button class="btn btn-primary">保存</button>
					<button class="btn">取消</button>
				</div>
			</form>
		</fieldset>
	</div>
	<script type="text/javascript">
		var options = {
			source: function (query, process) {
		        return $.get('${ctx}/celebrities/autocomplete', { name : query }, function (data) {
		            return process(data);
		        });
		    },
		    updater: function(item){
		    	return item.name;
		    },
		    matcher: function (item) {
		        return ~getName(item).toLowerCase().indexOf(this.query.toLowerCase());
	      	},
	      	sorter: function (items) {
	            var beginswith = []
	            , caseSensitive = []
	            , caseInsensitive = []
	            , item

	          while (item = items.shift()) {
	            if (!getName(item).toLowerCase().indexOf(this.query.toLowerCase())) beginswith.push(item)
	            else if (~getName(item).indexOf(this.query)) caseSensitive.push(item)
	            else caseInsensitive.push(item)
	          }

	          return beginswith.concat(caseSensitive, caseInsensitive)
	        },
	        highlighter: function (item) {
	            var query = this.query.replace(/[\-\[\]{}()*+?.,\\\^$|#\s]/g, '\\$&')
	            return getName(item).replace(new RegExp('(' + query + ')', 'ig'), function ($1, match) {
	              return '<strong>' + match + '</strong>'
	            })
	          }
		} 
		
		$(function(){
			$('.plus').on('click', function(){
				var $clone = $(this).closest('.control-group').find('.controls').last().clone();
				$clone.find(':input').val('').end().find('.sign-help-inline').html('<b class="minus">&minus;</b>');
				$clone.find('.typeahead').typeahead(options);
				$(this).closest('.control-group').append($clone);
			})
			$('.minus').live('click', function(){
				$(this).closest('.controls').remove();
			})
			$('.typeahead').live('change', function(event, data){
				var val = data ? data.id : '';
				$(this).prev().val(val);
			}).typeahead(options);
		})
		
		function getName(item){
			var name = item.name || '';
			name += ' ';
			name += item.nameEn || '';
			return name;
		}
	</script>
</body>
</html>



