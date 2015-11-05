<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap core CSS -->
<link href="resources/plugins/css/bootstrap.min.css" rel="stylesheet">
<link type="text/css" rel="stylesheet"
	href="resources/plugins/css/jquery.tool.css" />
<link type="text/css" rel="stylesheet"
	href="resources/css/auth/user/user.css" />

<!-- jquery -->
<script type="text/javascript"
	src="resources/plugins/js/jquery-1.9.1.js"></script>
<!-- bootstrap.js -->
<script type="text/javascript"
	src="resources/plugins/js/bootstrap.min.js"></script>
<script type="text/javascript" src="resources/js/util/bootstrap.init.js"></script>
<script type="text/javascript" src="resources/plugins/js/jquery.tool.js"></script>
<script type="text/javascript" src="resources/js/auth/user/list.js"></script>
<script type="text/javascript" src="resources/js/util/util_ajax.js"></script>
</head>
<body>
	<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li class="active"><a href="javascript:void(0)">用户管理 <span
						class="sr-only">(current)</span></a></li>
				<li><a href="javascript:void(0)">角色管理</a></li>
				<li><a href="javascript:void(0)">资源管理</a></li>
			</ul>
		</div>
	</div>
	</nav>

	<form id="pagerForm" action="user" method="post">
		<div class="container-fluid">
			<%@include file="toolbar.jsp"%>
			
			<!-- 显示列表的方式 -->
			<input type="hidden" id="type" name="type" value="${userShowType}">
			
			<div id="list-content">
				<%@include file="list_content.jsp" %>
			</div>
			<div id="thumbnail-content" style="display:none;">
				<%@include file="thumbnail_content.jsp" %>
			</div>
			<%@include file="../../common/footer.jsp"%>
		</div>


	</form>
</body>
</html>