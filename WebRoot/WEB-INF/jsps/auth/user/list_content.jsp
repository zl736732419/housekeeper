<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="row">
	<table id="users_table" class="table table-hover table-bordered table-striped">
		<thead>
			<tr>
				<th style="text-align: center;"><input type="checkbox" name="ids"></th>
				<th>用户名</th>
				<th>昵称</th>
				<th>性别</th>
				<th>年龄</th>
				<th>电话</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${pager.rows }" var="user">
				<tr>
					<td style="text-align: center;">
						<input type="checkbox" value="${user.id}" name="uid">
					</td>
					<td>${user.username}</td>
					<td>${user.nickname}</td>
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
					<td>${user.age}</td>
					<td>${user.tel}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>