<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
</head>
<body>
  <div class="modal-box">
    <ul class="nav nav-pills">
      <li class="active"><a id="for-list" href="#list" data-toggle="pill">选择影集</a></li>
      <li><a id="for-form" href="#form" data-toggle="pill">新增影集</a></li>
    </ul>
    <div class="tab-content">
      <div class="tab-pane active" id="list">
        <form>
          <input type="hidden" name="movie.id" value="${movieId}"/>
          <ul id="select-set">
            <c:forEach items="${list}" var="movieSet">
              <li>
              <label class="radio"> 
              <input type="radio" name="set.id" value="${movieSet.id}" /><span class="heading">${movieSet.title}</span>
              </label>
              </li>
            </c:forEach>
          </ul>
          <div id="setting">
            <a href="javascript:;">编写备注<b class="caret"></b></a>
            <div class="extra">
             <textarea id="comment" name="comment" placeholder="备注"></textarea>
            </div>
            <div>
              <button id="new-item-btn" class="btn btn-primary">保存</button>
              <button class="btn" data-dismiss="modal">关闭</button> 
            </div>
          </div>
        </form>
      </div>
      <div id="form" class="tab-pane">
        <form id="new-set">
          <label for="title">
            <input type="text" id="title" name="title" placeholder="影集名">
         	</label>
          <label for="summary">
            <textarea id="summary" name="summary"  placeholder="影集介绍"></textarea>
          </label>
          <button id="new-set-btn" class="btn btn-primary">保存</button>
          <button class="btn" data-dismiss="modal">关闭</button> 
        </form>
      </div>
    </div>
  </div>
</body>
<script type="text/javascript">
	$('#setting > a').toggle(function() {
		$('#setting').addClass('open');
	}, function() {
		$('#setting').removeClass('open');
	})
	$('#new-item-btn').on('click', function(){
		$.ajax({
			url: '${ctx}/movieSets/addIn',
			data: $('#list form').serialize(),
			dataType: 'json',
			type: 'post',
			success: function(jsondata){
				if(jsondata.success){
					var text = $('#list :checked').next('span').text();
					var $li = $('<li/>');
					var $span = $('<span class="heading"></span>');
					var $a = $('<a/>').attr('href', '#').attr('title', text).text(text);
					$span.append($a).appendTo($li);
					$('#sets').prepend($li)
					$.scojs_modal().close();
				}
			}
		})
		return false;
	})
	
	$('#new-set-btn').on('click', function(){
		$.ajax({
			url: '${ctx}/movieSets/create',
			data: $('#form form').serialize(),
			dataType: 'json',
			type: 'post',
			success: function(jsondata){
				if(jsondata.success){
					var $input = $('<input type="radio" name="set.id"/>').val(jsondata.data.id).attr('checked', true);
					var $span = $('<span class="heading"/>').text(jsondata.data.title);
					var $label = $('<label class="radio"></label>').append($input).append($span);
					$('<li></li>').prependTo('#list ul').append($label);
					$('#for-list').tab('show');
				}
			}
		})
		return false;
	})
</script>
</html>



