<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<jsp:include page="header.jsp" />

 <ul class="breadcrumb">
    <li class="active">权限管理</li>
  </ul>
  <shiro:hasPermission name="permission:create">
    <a href="permission/p_add" class="btn btn-primary">添加权限</a><br/>
  </shiro:hasPermission>
  <table class="table table-striped">
    <thead>
    <tr>
        <th>名称</th>
        <th>说明</th>
        <th>编辑</th>
        <th>删除</th>
      </tr>
    </thead>
    <c:forEach var="permission" items="${obj}">
      <tr>
        <td><a href="permission/view?id=${permission.id}">${permission.name}</a></td>
        <td>${permission.description}</td>
        <td><a href="permission/view?id=${permission.id}"><i class="icon-edit"></i>&nbsp;${msg.edit}</a></td>
        <td><a href="permission/delete?id=${permission.id}"><i class="icon-remove"></i>&nbsp;${msg.delete}</a></td>
      </tr>
    </c:forEach>
  </table>

<jsp:include page="footer.jsp" />