<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<title>上传电影海报</title>
<link href="${ctx}/static/jquery-jcrop/jquery.Jcrop.min.css" type="text/css" rel="stylesheet" />
<script src="${ctx}/static/jquery-jcrop/jquery.Jcrop.js" type="text/javascript"></script>
</head>
<body>
	<%@ include file="movieHeader.jsp"%>
	<div class="container">
		<form id="upload-form" action="${ctx}/movies/uploadPoster/${movie.id}" enctype="multipart/form-data" method="post" class="form-horizontal">
			<fieldset>
				<legend>
					<small>上传电影海报</small>
				</legend>
				<c:if test="${success}">
					<div class="alert alert-block">
						<button type="button" class="close" data-dismiss="alert">&times;</button>
						<strong>操作成功！</strong>电影海报已保存。
					</div>
				</c:if>
				<div class="control-group">
					<span class="control-label">1. 上传海报</span>
					<div id="file-control" class="controls">
						<div class="btn">
							<span>选择文件</span><input type="file" name="file" class="file" />
						</div>
						<span class="help-inline"></span>
					</div>
					<div class="controls">
						<input type="submit" value="上传文件" class="btn btn-primary" />
						<div class="help-block">你可以上传JPG、JPEG、GIF、PNG或BMP文件。</div>
					</div>
				</div>
			</fieldset>
		</form>

		<form id="crop-form" action="${ctx}/movies/cropPoster/${movie.id}" method="post" class="form-horizontal">
			<input type="hidden" name="x" /> 
			<input type="hidden" name="y" /> 
		    <input type="hidden" name="width" />
			<input type="hidden" name="height" />
			<div class="control-group">
				<span class="control-label">2. 编辑海报</span>
				<div class="controls">
					<div id="crop-area" class="clearfix">
						<div class="origin">
							<img alt="" src="${ctx}/${originPoster}">
						</div>
						<div class="preview">
							<img alt="" src="${ctx}/${smallPoster}">
						</div>
					<div class="help-block">随意拖拽或缩放大图中的虚线方格，预览的小图即为保存后的小头像图标。</div>
					</div>
				</div>
			</div>
			<div class="form-actions">
				<input id="submit_btn" class="btn btn-primary" type="submit" value="保存海报" />
			</div>
		</form>
	</div>
	
	<script type="text/javascript">
		$(":file").on("change", function(){
			var name = $(this).val();
			name = name.substring(name.lastIndexOf("\\")+1, name.length);
			$("#file-control .help-inline").text(name);
		})
		
		$('.origin img').load(function() {
			var horizontal = this.width > this.height;
			var width = horizontal ? 160 : 120;
			var height = horizontal ? 120 : 160;
			$('.preview').css({width : width + 'px', height : height + 'px'});
					
			$(this).Jcrop({
				onChange : showPreview,
				onSelect : showPreview,
				aspectRatio : horizontal ? 1.333 : 0.75
			});
			
			function showPreview(coords) {
				if (parseInt(coords.w) > 0) {
					var widgetSize = this.getWidgetSize();
					var rx = width / coords.w;
					var ry = height / coords.h;
					$('.preview img').css({
						maxWidth: "inherit",
						width : Math.round(widgetSize[0] * rx) + 'px',
						height : Math.round(widgetSize[1] * ry) + 'px',
						marginLeft : '-' + Math.round(rx * coords.x) + 'px',
						marginTop : '-' + Math.round(ry * coords.y) + 'px'
					});
					$("input[name='x']").val(coords.x);
					$("input[name='y']").val(coords.y);
					$("input[name='width']").val(coords.w);
					$("input[name='height']").val(coords.h);
				}
			}
	   });
		
		
	</script>
</body>
</html>



