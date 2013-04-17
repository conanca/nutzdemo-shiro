<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<jsp:include page="header.jsp" />

  <ul class="breadcrumb">
    <li class="active">用户管理</li>
  </ul>
  <shiro:hasPermission name="user:create">
    <a href="user/p_add" class="btn btn-primary">添加新用户</a><br/>
  </shiro:hasPermission>
  <table class="table table-striped">
    <thead>
    <tr>
        <th>账户</th>
        <th>性别</th>
        <th>备注</th>
        <th>编辑</th>
        <th>删除</th>
      </tr>
    </thead>
    <c:forEach var="user" items="${obj}">
      <tr>
        <td><a href="user/view?id=${user.id}">${user.name}</a></td>
        <td>${user.sex}</td>
        <td>${user.description}</td>
        <td><a href="user/view?id=${user.id}"><i class="icon-edit"></i>&nbsp;${msg.edit}</a></td>
        <td><a href="user/delete?id=${user.id}"><i class="icon-remove"></i>&nbsp;${msg.delete}</a></td>
      </tr>
    </c:forEach>
  </table>

<jsp:include page="footer.jsp" />