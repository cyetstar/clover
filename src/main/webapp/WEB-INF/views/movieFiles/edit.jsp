<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<body>
  <div class="modal-box">
    <form>
      <input type="hidden" name="id" value="${movieFile.id}"> 
      <label class="control-label" for="filename"> 
      <input type="text" name="filename" value="${movieFile.filename}">
      </label>
      <button id="edit-file-btn" class="btn btn-primary btn-save">保存</button>
      <button class="btn" data-dismiss="modal">关闭</button>
    </form>
  </div>
<script type="text/javascript">
	$('#edit-file-btn').on('click',function(){
		var self = this;
		$.ajax({
			url: '${ctx}/movieFiles/update.json',
			type: 'post',
			data: $(self).closest('form').serialize(),
			success: function(jsondata){
				if(jsondata.status){
					$('#files').find('[data-file-id="' + jsondata.movieFile.id + '"]').find('span').text(jsondata.movieFile.filename);
					$.scojs_modal().close();
				}
			}
		})
		return false;
	})
</script>
</body>
</html>



