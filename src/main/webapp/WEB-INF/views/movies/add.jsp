<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<title>新增电影</title>
</head>
<body>
	<%@ include file="movieHeader.jsp"%>
	<div class="container">
		<div id="chose-mode" class="row">
			<div class="offset1 span5">
				<fieldset>
					<legend>
						<small>从豆瓣电影获取</small>
					</legend>
					<form action="${ctx}/movies/fetch" method="post">
					<input type="text" name="doubanId" value="" placeholder="输入豆瓣编号" autocomplete="off"/><br />
					<input type="submit" class="btn btn-primary" value="开始获取"/>
					</form>
				</fieldset>
			</div>
			<div class="span5">
				<fieldset>
					<legend>
						<small>资料录入</small>
					</legend>
					<input type="text" name="doubanId" value="" placeholder="输入IMDb编号" autocomplete="off"/>
					<span class="help-block">如果IMDb编号的电影已经添加，将转到电影的编辑页面。</span>
					<button class="btn">新增</button>
				</fieldset>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	
	</script>
</body>
</html>



