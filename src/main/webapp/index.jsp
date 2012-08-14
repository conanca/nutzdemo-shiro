<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Nutzdemo - Shiro</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<style>
body {
	padding-top: 60px;
	/* 60px to make the container go all the way to the bottom of the topbar */
}
</style>
<link href="css/bootstrap-responsive.min.css" rel="stylesheet">
<link rel="shortcut icon" href="ico/favicon.ico">
<link rel="apple-touch-icon-precomposed" sizes="114x114" href="ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72" href="ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed" href="ico/apple-touch-icon-57-precomposed.png">
</head>
<body>
  <div class="navbar navbar-fixed-top">
    <div class="navbar-inner">
      <div class="container">
        <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </a>
        <a class="brand" href="#">Nutzdemo-shiro</a>
        <div class="nav-collapse">
          <shiro:authenticated>
          <ul class="nav">
            <li><a href="user/all">用户管理</a></li>
            <li><a href="role/all">角色管理</a></li>
            <li><a href="permission/all">权限管理</a></li>
          </ul>
          <ul class="nav pull-right">
            <li><a>欢迎, <shiro:principal/></a></li>
            <li><a href="logout">登出</a></li>
          </ul>
          </shiro:authenticated>
        </div><!--/.nav-collapse -->
      </div>
    </div>
  </div>
  <div class="container">
  
    <div class="hero-unit">
      <p>这是一个结合 Nutz 使用 Shiro 进行登录验证和鉴权的 Demo</p>
      <shiro:authenticated>
        <p>欢迎, <shiro:principal/></p>
        <p>您可以通过点击顶部导航，进行不同的操作</p>
      </shiro:authenticated>
      <shiro:guest>
        <p>请您先登录</p>
        <p>管理员用户/密码 : admin/123</p>
      </shiro:guest>
    </div>
  
  <shiro:guest>
  <form action="login" class="form-vertical">
    <fieldset>
      <legend>请登录</legend>
      <div id="alert">
      ${obj}
      </div>
      <div class="control-group">
        <label class="control-label" for="username">${msg.login_name}</label>
        <div class="controls">
          <input type="text" name="username" id="username" class="input-big"/>
        </div>
      </div>
      <div class="control-group">
        <label class="control-label" for="password">${msg.login_password}</label>
        <div class="controls">
          <input type="password" name="password" id="password" class="input-big"/>
        </div>
      </div>
      <div class="form-actions">
        <input type="submit" value="${msg.login_submit}" class="btn btn-primary"/>
      </div>
    </fieldset>
  </form>
  </shiro:guest>
  </div>
  <script src="js/jquery-1.7.2.min.js"></script>
  <script src="js/bootstrap.min.js"></script>
  <script type="text/javascript">
    var msg = '${obj}';
    if(msg){
    	$('#alert').addClass('alert alert-error');
    	$('#alert').append('<a class="close" data-dismiss="alert">×</a>');
    }
  </script>
</body>
</html>
