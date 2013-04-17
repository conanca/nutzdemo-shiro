<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<jsp:include page="header.jsp" />

<ul class="breadcrumb">
    <li class="active">角色管理</li>
  </ul>
  <shiro:hasPermission name="role:create">
    <a href="role/p_add" class="btn btn-primary">添加角色</a><br/>
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
    <c:forEach var="role" items="${obj}">
      <tr>
        <td><a href="role/view?id=${role.id}">${role.name}</a></td>
        <td>${role.description}</td>
        <td><a href="role/view?id=${role.id}"><i class="icon-edit"></i>&nbsp;${msg.edit}</a></td>
        <td><a href="role/delete?id=${role.id}"><i class="icon-remove"></i>&nbsp;${msg.delete}</a></td>
      </tr>
    </c:forEach>
  </table>

<jsp:include page="footer.jsp" />