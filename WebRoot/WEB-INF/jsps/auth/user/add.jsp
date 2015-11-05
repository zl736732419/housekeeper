<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<title></title>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap core CSS -->
<link href="resources/plugins/css/bootstrap.min.css" rel="stylesheet">
<link type="text/css" rel="stylesheet" href="resources/plugins/css/jquery.tool.css" />
<link href="resources/css/common.css" rel="stylesheet">


<script type="text/javascript"
	src="resources/plugins/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="resources/plugins/js/jquery.tool.js"></script>
<script type="text/javascript"
	src="resources/plugins/js/bootstrap.min.js"></script>
<script type="text/javascript" src="resources/js/util/bootstrap.init.js"></script>
<script type="text/javascript"
	src="resources/plugins/js/jquery.validate.min.js"></script>
<script type="text/javascript" src="resources/js/util/jquery.validate.extend.js"></script>

<script type="text/javascript" src="resources/js/auth/user/add.js"></script>
<script type="text/javascript" src="resources/js/common/common.js"></script>
<script type="text/javascript" src="resources/js/util/util_ajax.js"></script>
<script type="text/javascript" src="resources/js/validates/user.validate.js"></script>

</head>
<body>
	<div class="container">
		<!-- 路径导航 -->
		<ol class="breadcrumb">
			<li><a href="user">用户列表</a></li>
			<li class="active">
				<c:choose>
					<c:when test="${not empty user.id}">
						用户编辑
					</c:when>
					<c:otherwise>
						用户新增
					</c:otherwise>
				</c:choose>
			</li>
		</ol>
		<form id="user_add_form" class="form-horizontal" enctype="multipart/form-data" method="post">
			
			<input type="hidden" id="id" name="id" value="${user.id }"/>
			
			<c:if test="${empty user.id}">
				<div class="form-group">
					<label for="username" class="col-sm-4 control-label">用户名</label>
					<div class="col-sm-5">
						<input type="text" class="form-control" id="username" name="username" value="${user.username }"
							placeholder="请输入用户名">
					</div>
					<div class="col-sm-3">
						<span class="label label-danger error_msg"></span>
					</div>
				</div>
			</c:if>
			<div class="form-group">
				<label for="nickname" class="col-sm-4 control-label">昵称</label>
				<div class="col-sm-5">
					<input type="text" class="form-control" id="nickname" name="nickname" value="${user.nickname }"
						placeholder="请输入昵称">
				</div>
				<div class="col-sm-3">
					<span class="label label-danger error_msg"></span>
				</div>
			</div>
			<c:if test="${empty user.id}">
				<div class="form-group">
					<label for="password" class="col-sm-4 control-label">密码</label>
					<div class="col-sm-5">
						<input type="password" class="form-control" id="password" name="password"
							placeholder="请输入密码">
						<input type="password" style="display:none;"/>
					</div>
					<div class="col-sm-3">
						<span class="label label-danger error_msg"></span>
					</div>
				</div>
				<div class="form-group">
					<label for="confirmpwd" class="col-sm-4 control-label">确认密码</label>
					<div class="col-sm-5">
						<input type="password" class="form-control" id="confirmpwd" name="confirmpwd"
							placeholder="请再次输入密码">
						<input type="password" style="display:none;"/>
					</div>
					<div class="col-sm-3">
						<span class="label label-danger error_msg"></span>
					</div>
				</div>
			</c:if>
			<div class="form-group">
				<label for="tel" class="col-sm-4 control-label">手机号码</label>
				<div class="col-sm-5">
					<input type="text" class="form-control" id="tel" name="tel" value="${user.tel }"
						placeholder="请输入手机号码">
				</div>
				<div class="col-sm-3">
					<span class="label label-danger error_msg"></span>
				</div>
			</div>
			<div class="form-group">
				<label for="age" class="col-sm-4 control-label">年龄</label>
				<div class="col-sm-5">
					<input type="text" class="form-control" id="age" name="age" value="${user.age }"
						placeholder="请输入年龄">
				</div>
				<div class="col-sm-3">
					<span class="label label-danger error_msg"></span>
				</div>
			</div>
			<div class="form-group">
				<label for="sex" class="col-sm-4 control-label">性别</label>
				<div class="col-sm-5">
					<label>
						<c:choose>
							<c:when test="${user.sex eq 'w' }">
								<input type="radio" name="sex" value="m"> 男
			          			<input type="radio" name="sex" value="w" checked="checked"> 女 
							</c:when>
							<c:otherwise>
								<input type="radio" name="sex" value="m" checked="checked"> 男
			          			<input type="radio" name="sex" value="w"> 女 
							</c:otherwise>
						</c:choose>
			        </label>
				</div>
			</div>
			<div class="form-group">
				<label for="photo" class="col-sm-4 control-label">头像</label>
				<div class="col-sm-5">
					<input type="file" id="photo" name="photo" onchange="check_type(this)" accept="image/gif,image/jpeg,image/jpg,image/png">
				</div>
			</div>

			<div class="form-group">
				<div class="col-sm-offset-4 col-sm-2">
					<button type="submit" class="btn btn-primary">保&emsp;存</button>
				</div>
				<div class="col-sm-2">
					<button type="button" class="btn btn-default" onclick="back()">返&emsp;回</button>
				</div>
			</div>
		</form>
		<!-- 显示错误信息 -->
		<div class="row">
			<div class="col-sm-12" style="text-align: center;">
				<span class="label label-danger" style="font-size: 10pt">${error.msg}</span>
			</div>
		</div>
	</div>
</body>
</html>