<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html lang="en">
  <head>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
    <base href="<%=basePath%>" />
    <meta charset="utf-8">
    <title>Steam Flea Market</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le styles -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <style type="text/css">
      body {
        padding-top: 60px;
        padding-bottom: 40px;
      }
    </style>
    <link href="css/bootstrap-responsive.min.css" rel="stylesheet">

    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="js/html5shiv.js"></script>
    <![endif]-->

    <!-- Fav and touch icons -->
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="ico/apple-touch-icon-57-precomposed.png">
    <link rel="shortcut icon" href="ico/favicon.png">
  </head>

  <body>

    <div class="navbar navbar-inverse navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container">
          <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="brand" href="#">Nutz demo for Shiro</a>
          <div class="nav-collapse collapse">
            <ul class="nav">
              <shiro:authenticated>
                <li class="dropdown">
                  <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    系统管理
                    <b class="caret"></b>
                  </a>
                  <ul class="dropdown-menu">
                    <li><a href="user/all">用户管理</a></li>
                    <li><a href="role/all">角色管理</a></li>
                    <li><a href="permission/all">权限管理</a></li>
                  </ul>
               </li>
              </shiro:authenticated>
            </ul>
            <form class="navbar-search pull-left">
              <input type="text" class="search-query span2" placeholder="搜索">
            </form>
            <shiro:guest>
              <form action="login"  class="navbar-form pull-right">
                <input name="username" class="span2" type="text" placeholder="${msg.login_name}">
                <input name="password" class="span2" type="password" placeholder="${msg.login_password}">
                <button type="submit" class="btn">登入</button>
              </form>
            </shiro:guest>
            <shiro:authenticated>
              <ul class="nav pull-right">
                <li><a><shiro:principal/></a></li>
                <li><a href="logout">登出</a></li>
              </ul>
            </shiro:authenticated>
          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div>

    <div class="container">
