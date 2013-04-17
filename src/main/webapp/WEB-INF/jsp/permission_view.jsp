<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<jsp:include page="header.jsp" />

<ul class="breadcrumb">
      <li>
        <a href="permission/all">权限管理</a> <span class="divider">/</span>
      </li>
      <li class="active">编辑权限</li>
    </ul>
    <form action="permission/edit" class="form-horizontal">
    <fieldset>
      <legend>编辑权限信息</legend>
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
        <a href="permission/all" class="btn">取消</a>
      </div>
    </fieldset>
    </form>

<jsp:include page="footer.jsp" />