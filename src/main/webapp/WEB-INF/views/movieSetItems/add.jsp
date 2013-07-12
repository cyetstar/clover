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
          <ul id="select-set">
            <c:forEach items="${list}" var="movieSet">
              <li>
              <label class="radio"> 
              <input type="radio" name="setId" value="${movieSet.id}" /><span class="heading">${movieSet.title}</span>
              </label>
              </li>
            </c:forEach>
          </ul>
          <div id="setting">
            <form>
              <a href="javascript:;">编写备注<b class="caret"></b></a>
              <input type="hidden" name="movie.id" value="${movieId}"/>
              <input type="hidden" name="set.id" value=""/>
              <div class="extra">
               <textarea id="comment" name="comment" placeholder="备注"></textarea>
              </div>
              <div>
                <button id="add-item-btn" class="btn btn-primary">保存</button>
                <button class="btn" data-dismiss="modal">关闭</button> 
              </div>
            </form>
          </div>
      </div>
      <div id="form" class="tab-pane">
        <form>
          <label for="title">
            <input type="text" id="title" name="title" placeholder="影集名">
         	</label>
          <label for="summary">
            <textarea id="summary" name="summary"  placeholder="影集介绍"></textarea>
          </label>
          <button id="add-set-btn" class="btn btn-primary">保存</button>
          <button class="btn" data-dismiss="modal">关闭</button> 
        </form>
      </div>
    </div>
  </div>
</body>
<script type="text/javascript">
	$('#select-set').on('click',':radio', function(){
		$('[name="set.id"]').val($(this).val());
	})
	$('#setting form a').toggle(function() {
		$('#setting').addClass('open');
	}, function() {
		$('#setting').removeClass('open');
	})
	$('#add-item-btn').on('click', function(){
		var self = this;
		$.ajax({
			url: '${ctx}/movieSetItems/create.json',
			data: $(self).closest('form').serialize(),
			type: 'post',
			success: function(jsondata){
				if(jsondata.status){
					var text = $('[name="setId"]:checked').next('span').text();
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
	
	$('#add-set-btn').on('click', function(){
		var self = this;
		$.ajax({
			url: '${ctx}/movieSets/create.json',
			data: $(self).closest('form').serialize(),
			type: 'post',
			success: function(jsondata){
				if(jsondata.status){
					var $input = $('<input type="radio" name="set.id"/>').val(jsondata.movieSet.id).attr('checked', true);
					var $span = $('<span class="heading"/>').text(jsondata.movieSet.title);
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



