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
                <input type="hidden" name="id" value="${file.id}">
				<input type="text" name="filename" value="${file.filename}">
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
			url: '${ctx}/movieFiles/update',
			type: 'post',
			dataType: 'json',
			data: $('form').serialize(),
			success: function(jsondata){
				if(jsondata.success){
					$('#file-list').find('[data-file-id="' + jsondata.data.id + '"]').find('span').text(jsondata.data.filename);
					$.scojs_modal().close();
				}else{
					
				}
			}
		})
	})
	</script>
	
</body>
</html>



