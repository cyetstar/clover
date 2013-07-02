<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
</head>
<body>
	<form id="form" class="form-horizontal">
		<div class="control-group">
			<label class="control-label" for="title">影集名</label>
			<div class="controls">
				<input type="text" id="title" name="title">
			</div>
		</div>
        <div class="control-group">
          <label class="control-label" for="summary">影集介绍</label>
          <div class="controls">
            <textarea id="summary" name="summary"></textarea>
          </div>
        </div>
	</form>
	<div class="modal-footer">
		<a href="javascript:" class="btn" data-dismiss="modal">关闭</a> 
		<a href="javascript:" id="create-set" class="btn btn-primary btn-save">保存</a>
	</div>
	<script type="text/javascript">
		$('#create-set').on('click', function(){
			$.ajax({
				url: '${ctx}/movieSets/create',
				type: 'post',
				data: $('#form').serialize(),
				success: function(jsondata){
					if(jsondata.success){
						$('#addin').load('${ctx}/movieSets/addIn?movieId=${movieId}')
					}
				}
			})
		})
	</script>
	
</body>
</html>



