<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
<title>新增电影</title>
</head>
<body>
  <%@ include file="movieHeader.jsp"%>
  <div class="container">
    <div class="row" id="add-mode">
      <div class="span6"><button class="btn btn-warning">从豆瓣获取</button></div>
      <div class="span6"><button class="btn">手动录入</button></div>
    </div>
  </div>
  
  <form class="form-horizontal hide">
    <fieldset>
      <div id="legend" class="">
        <legend class="">填写电影信息</legend>
      </div>
      <div class="control-group">

        <!-- Text input-->
        <label class="control-label" for="input01">影片名</label>
        <div class="controls">
          <input type="text" placeholder="" class="input-xlarge">
          <p class="help-block"></p>
        </div>
      </div>

      <div class="control-group">

        <!-- Text input-->
        <label class="control-label" for="input01">原片名</label>
        <div class="controls">
          <input type="text" placeholder="" class="input-xlarge">
          <p class="help-block"></p>
        </div>
      </div>

      <div class="control-group">

        <!-- Text input-->
        <label class="control-label" for="input01">又名</label>
        <div class="controls">
          <input type="text" placeholder="" class="input-xlarge">
          <p class="help-block"></p>
        </div>
      </div>
      <div class="control-group">
        <label class="control-label">类型</label>

        <!-- Multiple Checkboxes -->
        <div class="controls">
          <!-- Inline Checkboxes -->
          <label class="checkbox inline"> <input type="checkbox" value="1"> 1
          </label> <label class="checkbox inline"> <input type="checkbox" value="2"> 2
          </label> <label class="checkbox inline"> <input type="checkbox" value="3"> 3
          </label>
        </div>

      </div>
      <div class="control-group">
        <label class="control-label">语言</label>

        <!-- Multiple Checkboxes -->
        <div class="controls">
          <!-- Inline Checkboxes -->
          <label class="checkbox inline"> <input type="checkbox" value="1"> 1
          </label> <label class="checkbox inline"> <input type="checkbox" value="2"> 2
          </label> <label class="checkbox inline"> <input type="checkbox" value="3"> 3
          </label>
        </div>

      </div>
      <div class="control-group">
        <label class="control-label">制片国家/地区</label>

        <!-- Multiple Checkboxes -->
        <div class="controls">
          <!-- Inline Checkboxes -->
          <label class="checkbox inline"> <input type="checkbox" value="1"> 1
          </label> <label class="checkbox inline"> <input type="checkbox" value="2"> 2
          </label> <label class="checkbox inline"> <input type="checkbox" value="3"> 3
          </label>
        </div>

      </div>
      <div class="control-group">

        <!-- Textarea -->
        <label class="control-label">剧情简介</label>
        <div class="controls">
          <div class="textarea">
            <textarea type="" class=""> </textarea>
          </div>
        </div>
      </div>

    </fieldset>
  </form>
</body>
</html>



