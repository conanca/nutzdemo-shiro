<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<jsp:include page="header.jsp" />

 <ul class="breadcrumb">
      <li>
        <a href="role/all">角色管理</a> <span class="divider">/</span>
      </li>
      <li class="active">添加角色</li>
    </ul>
    <form action="role/add" class="form-vertical">
    <fieldset>
      <legend>添加新角色</legend>
      <div class="control-group">
        <label class="control-label" for="name">名称</label>
        <div class="controls">
          <input type="text" name="name" id="name" class="input-big"/>
        </div>
      </div>
      <div class="control-group">
        <label class="control-label" for="description">说明</label>
        <div class="controls">
          <textarea name="description" id="description" rows="3" class="input-big"></textarea>
        </div>
      </div>
      <div class="form-actions">
        <input type="submit" value="${msg.add}" class="btn btn-primary"/>
        <a href="role/all" class="btn">取消</a>
      </div>
    </fieldset>
    </form>

<jsp:include page="footer.jsp" />