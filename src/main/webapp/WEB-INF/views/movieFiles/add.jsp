<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<script src="${ctx}/static/sco.js/sco.modal.js" type="text/javascript"></script>
</head>
<body>
	<form id="save-file-form" class="form-horizontal form-modal">
		<div class="control-group">
			<label class="control-label" for="filename">文件名</label>
			<div class="controls">
                <input type="hidden" name="movieId" value="${movieId}">
				<input type="text" id="filename" name="filename">
			</div>
		</div>
	</form>
	<div class="modal-footer">
		<a href="javascript:" class="btn" data-dismiss="modal">关闭</a> 
		<a href="javascript:" class="btn btn-primary btn-save">保存</a>
	</div>
	<script type="text/javascript">
	$('.modal-footer .btn-save').on('click',function(){
		$.ajax({
			url: '${ctx}/movieFiles/create',
			type: 'post',
			dataType: 'json',
			data: {movieId: '${movieId}', filename: $('#filename').val()},
			success: function(jsondata){
				if(jsondata.success){
					var $li = $('<li class="clearfix" data-file-id="' + jsondata.data.id + '"></li>');
					$li.append('<span>' + jsondata.data.filename + '</span>');
					$li.append('<a href="javascript:" class="delete btn btn-mini btn-danger">删除</a>');
					$li.append('<a href="${ctx}/movieFiles/edit/' + jsondata.data.id + '" data-trigger="modal" data-title="修改电影文件信息" class="edit btn btn-mini">修改</a>')
					$('#file-list').prepend($li);
					$.scojs_modal().close();
				}else{
					
				}
			}
		})
	})
	</script>
	
</body>
</html>



