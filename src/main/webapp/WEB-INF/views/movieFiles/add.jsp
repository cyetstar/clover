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
    <form id="new-file">
      <input type="hidden" name="movieId" value="${movieId}"> 
      <label for="filename"> 
        <input type="text" id="filename" name="filename" placeholder="文件名">
      </label>
      <button id="new-file-btn" class="btn btn-primary btn-save">保存</button>
      <button class="btn" data-dismiss="modal">关闭</button>
    </form>
  </div>

  <script type="text/javascript">
	$('#new-file-btn').on('click',function(){
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



