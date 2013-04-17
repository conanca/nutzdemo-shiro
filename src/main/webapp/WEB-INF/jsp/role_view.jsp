<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<jsp:include page="header.jsp" />

 <ul class="breadcrumb">
      <li>
        <a href="role/all">角色管理</a> <span class="divider">/</span>
      </li>
      <li class="active">编辑角色</li>
    </ul>
    <div class="row">
      <div class="span6">
    <form action="role/edit" class="form-horizontal">
    <fieldset>
      <legend>编辑角色信息</legend>
      <input type="hidden" name="id" value="${obj.id}"/>
      <div class="control-group">
        <label class="control-label" for="name">名称</label>
        <div class="controls">
          <input type="text" name="name" id="name" value="${obj.name}" class="input-big"/>
        </div>
      </div>  
      <div class="control-group">
        <label class="control-label" for="description">说明</label>
        <div class="controls">
          <textarea name="description" id="description" rows="3" class="input-big">${obj.description}</textarea>
        </div>
      </div>
      <div class="form-actions">
        <input type="submit" value="${msg.edit}" class="btn btn-primary"/>
        <a href="role/all" class="btn">取消</a>
      </div>
    </fieldset>
    </form>
      </div>
      <div class="span6">
    <form action="role/addPermission" class="well form-vertical">
    <fieldset>
      <legend>给角色 ${obj.name} 分配新权限</legend>
      <input type="hidden" name="roleId" value="${obj.id}"/>
      <div class="control-group">
        <label class="control-label" for="permissionId">权限</label>
        <div class="controls">
          <select name="permissionId" id="permissionId">
            <option value="0" selected>请选择欲分配的权限</option>
          </select>
        </div>
      </div>
      <div class="">
        <input type="submit" value="分配" class="btn btn-primary"/>
      </div>
    </fieldset>
    </form>
      </div>
    </div>
    
    
    <hr/>
    <h2>${obj.name}拥有的权限:</h2>
    <table class="table table-striped">  
    <thead>
    <tr>
        <th>名称</th>
        <th>备注</th>
        <th>移除</th>
      </tr>
    </thead>
    <c:forEach var="permission" items="${obj.permissions}">
      <tr>
        <td>${permission.name}</td>
        <td>${permission.description}</td>
        <td><a href="role/removePermission?roleId=${obj.id}&permissionId=${permission.id}"><i class="icon-remove"></i>&nbsp;移除</a></td>
      </tr>
    </c:forEach>
    </table>

<jsp:include page="footer.jsp" />
  <script type="text/javascript">
  $.getJSON('permission/map',function(response){
    $.each(response,function(value,text){
      var newopt='<option value="'+value+'">'+text+'</option>';
        $("#permissionId").append(newopt);
    });
  });
  </script>