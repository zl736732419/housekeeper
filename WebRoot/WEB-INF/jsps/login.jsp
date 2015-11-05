<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link type="text/css" rel="stylesheet"
	href="resources/plugins/css/supersized.css">
<link type="text/css" rel="stylesheet"
	href="resources/plugins/css/jquery.loadmask.css">
<link type="text/css" rel="stylesheet"
	href="resources/css/login/style.css">

<script type="text/javascript"
	src="resources/plugins/js/jquery-1.9.1.js"></script>
<script type="text/javascript"
	src="resources/plugins/js/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="resources/plugins/js/supersized.3.2.7.min.js"></script>
<script type="text/javascript"
	src="resources/plugins/js/supersized-init.js"></script>
<script type="text/javascript"
	src="resources/plugins/js/jquery.loadmask.js"></script>
<script type="text/javascript" src="resources/js/util/util.js"></script>
<script type="text/javascript" src="resources/js/util/util_ajax.js"></script>
<script type="text/javascript" src="resources/js/validates/login.validate.js"></script>
<script type="text/javascript" src="resources/js/login.js"></script>

<title>用户登录</title>
</head>
<body>
	<div class="main">
		<div class="login-form">
			<h1>登录管家卫士</h1>
			<div class="head">
				<img src="resources/images/user.png" alt="" />
			</div>
			<form id="login_form" action="login">
				<input id="username" name="username" type="text" class="text" placeholder="请输入用户名">
				<input type="text" class="text" style="display:none;">
				<input id="password" name="password" type="password" placeholder="请输入密码">
				<input type="text" class="password" style="display:none;">
				<div class="submit">
					<input type="submit" value=" 登&emsp;录 ">
				</div>
				
				<span id="error_msg" style="color:red; margin:0px auto;"></span>
			</form>
		</div>
	</div>

</body>
</html>