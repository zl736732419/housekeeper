<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="row">
	<div class="form-inline">
		<div class="col-md-7 col-sm-7">
			<div class="form-group">
			   <input name="username" value="${query.username }" type="text" class="form-control" size="5" placeholder="用户名">
			</div>
			<div class="form-group">
			   <input name="nickname" value="${query.nickname }" type="text" class="form-control" size="5" placeholder="昵称">
			</div>
			<div class="form-group">
			   <select name="sex" class="form-control">
			   		<option value="" selected="selected">选择性别</option>
			   		<option value="m">男</option>
			   		<option value="w">女</option>
			   </select>
			</div>
			<div id="age_group" class="form-group">
			   <input name="age" value="${query.age }" type="text" class="form-control" size="2" placeholder="年龄">
			</div>
			<div class="form-group">
			   <input name="tel" value="${query.tel }" type="text" class="form-control" size="6" placeholder="电话">
			</div>
			<button type="submit" class="btn btn-primary">查&nbsp;询</button>
		</div>
	</div>
	
	<div class="col-md-3 col-sm-3">
		<div class="btn-group" role="group" style="float:right;">
			<button type="button" class="btn btn-default" data-container="body"
				data-toggle="popover" data-placement="bottom" onclick="add_user()"
				data-content="新增用户">
				<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
			</button>
			<button type="button" class="btn btn-default" data-container="body"
				data-toggle="popover" data-placement="bottom" onclick="edit_user()"
				data-content="修改用户">
				<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
			</button>
			<button type="button" class="btn btn-default" data-container="body"
				data-toggle="popover" data-placement="bottom" onclick="delete_user()"
				data-content="删除用户">
				<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
			</button>
		</div>
	</div>
	
	<div class="col-md-2 col-sm-2">
		<div class="btn-group" role="group" aria-label="...">
			<button id="list-btn" type="button" class="btn btn-primary" data-container="body"
				data-toggle="popover" data-placement="bottom"
				data-content="以列表方式显示" onclick="show_type('list')">
				<span class="glyphicon glyphicon-list" aria-hidden="true"></span>
			</button>
			<button id="thumbnail-btn" type="button" class="btn btn-default" data-container="body"
				data-toggle="popover" data-placement="bottom"
				data-content="以缩略图方式显示" onclick="show_type('thumbnail')">
				<span class="glyphicon glyphicon-th-large" aria-hidden="true"></span>
			</button>
		</div>
	</div>
</div>
</br>
