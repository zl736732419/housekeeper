<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:forEach items="${pager.rows}" var="user" varStatus="num">
	<!-- 一行开始 -->
	<c:if test="${num.count % 5 == 1}">
		<div class="row">
	</c:if>
	<div class="col-xs-2 col-md-2">
		<div class="thumbnail" 
			data-toggle="popover" style="margin-bottom:20px;">
			<div style="width:100%;height:100%;">
				<img src="/users/${user.pic}" style="width:100%;height:100%;">
			</div>
			<div class="select-btn">
				<input type="hidden" value="${user.id}">
				<span class="unselected-icon"></span>
			</div>
		</div>
		<!-- 创建信息提示框 -->
		<div class="popover fade">
			<div class="arrow"></div>
			<h2 class="popover-title" style="margin:0px;padding:0px;">
				<b>用户信息</b>
			</h2>
			<div class="popover-content">
				<table class="table table-bordered" style="padding:0px;margin:0px;">
					<tr>
						<td>用户名</td>
						<td>
							<span class="label label-primary" style="font-size:10pt;">${user.username }</span>
						</td>
					</tr>
					<tr>
						<td>昵称</td>
						<td>${user.nickname }</td>
					</tr>
					<tr>
						<td>性别</td>
						<td>
							<c:choose>
								<c:when test="${user.sex eq 'w'}">
									<span style="color:green">女</span>
								</c:when>
								<c:otherwise>
									<span style="color:red">男</span>
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
					<tr>
						<td>年龄</td>
						<td>${user.age }</td>
					</tr>
					<tr>
						<td>电话</td>
						<td>${user.tel }</td>
					</tr>
				</table>
			</div>
		</div>
	</div>
	<!-- 一行结束 -->
	<c:if test="${num.count % 5 == 0 or num.count == fn:length(pager.rows)}">
		</div>
	</c:if>
</c:forEach>