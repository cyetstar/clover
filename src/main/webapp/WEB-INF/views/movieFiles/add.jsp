<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<body>
  <div class="modal-box">
    <form>
      <input type="hidden" name="movie.id" value="${movieId}"> 
      <label for="filename"> 
        <input type="text" id="filename" name="filename" placeholder="文件名">
      </label>
      <button id="add-file-btn" class="btn btn-primary btn-save">保存</button>
      <button class="btn" data-dismiss="modal">关闭</button>
    </form>
  </div>

  <script type="text/javascript">
	$('#add-file-btn').on('click',function(){
		var self = this;
		$.ajax({
			url: '${ctx}/movieFiles/create.json',
			type: 'post',
			data: $(self).closest('form').serialize(),
			success: function(jsondata){
				if(jsondata.status){
					var $li = $('<li class="clearfix" data-file-id="' + jsondata.movieFile.id + '"></li>');
					$li.append('<span>' + jsondata.movieFile.filename + '</span>');
					$li.append('<a href="javascript:" class="delete btn btn-mini btn-danger">删除</a>');
					$li.append('<a href="${ctx}/movieFiles/edit/' + jsondata.movieFile.id + '" data-trigger="modal" data-title="修改电影文件信息" class="edit btn btn-mini">修改</a>')
					$('#files').prepend($li);
					$.scojs_modal().close();
				}else{
					
				}
			}
		})
		return false;
	})
	</script>
</body>
</html>



