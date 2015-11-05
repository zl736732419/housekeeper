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
<base href="<%=basePath%>"/>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="resources/css/main/css.css" />
<link type="text/css" rel="stylesheet" href="resources/plugins/css/jquery.tool.css" />
<link type="text/css" rel="stylesheet" href="resources/css/main/smartMenu.css" />

<script type="text/javascript" src="resources/js/util/util.js"></script>
<script type="text/javascript" src="resources/plugins/js/jquery-1.6.2.min.js"></script>
<script type="text/javascript" src="resources/plugins/js/jquery.tool.js"></script>
<script type="text/javascript" src="resources/js/main/shortcut.js"></script>
<script type="text/javascript" src="resources/js/main/templates.js"></script>
<script type="text/javascript" src="resources/js/main/jquery-smartMenu.js"></script>
<script type="text/javascript" src="resources/js/main/core.js"></script>
<title></title>

<script type="text/javascript">
	$(function() {
		 document.body.onselectstart = document.body.ondrag = function () { return false; }
         Core.init();
	});
</script>

</head>

<%@include file="common/dialogs.jsp"%>

<body id="lxcn" style="background: url(resources/images/main/deskbgs/1.jpg) repeat right bottom transparent;">
    <div id="task-bar">
        <ul class="task-window">
        </ul>
        <ul class="task-panel">
            <li class="sys" title="系统设定"><b class="">系统设定</b></li>
        </ul>
    </div>
    <div id="desk">
        <ul>
        </ul>
    </div>
</body>



</html>