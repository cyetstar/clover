<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<script src="${ctx}/static/sco.js/sco.modal.js" type="text/javascript"></script>
</head>
<body>
  <div class="modal-box">
    <form id="edit-file">
      <input type="hidden" name="id" value="${file.id}"> 
      <label class="control-label" for="filename"> 
      <input type="text" name="filename" value="${file.filename}">
      </label>
      <button id="edit-file-btn" class="btn btn-primary btn-save">保存</button>
      <button class="btn" data-dismiss="modal">关闭</button>
    </form>
  </div>
<script type="text/javascript">
	$('#edit-file-btn').on('click',function(){
		$.ajax({
			url: '${ctx}/movieFiles/update',
			type: 'post',
			dataType: 'json',
			data: $('form').serialize(),
			success: function(jsondata){
				if(jsondata.success){
					$('#files').find('[data-file-id="' + jsondata.data.id + '"]').find('span').text(jsondata.data.filename);
					$.scojs_modal().close();
				}
			}
		})
	})
</script>
</body>
</html>



