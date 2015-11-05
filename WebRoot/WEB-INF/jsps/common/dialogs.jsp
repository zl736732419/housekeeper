<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- 修改密码弹出框 -->
<div class="modal fade" id="pwdmodal" tabindex="-1" role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<form class="form-horizontal">
				<div class="form-group">
					<label for="password" class="col-sm-2 control-label">密码</label>
					<div class="col-sm-10">
						<input type="password" class="form-control" id="password"
							placeholder="请输入新密码">
					</div>
				</div>
				<div class="form-group">
					<label for="confirmpwd" class="col-sm-2 control-label">确认密码</label>
					<div class="col-sm-10">
						<input type="password" class="form-control" id="confirmpwd"
							placeholder="请再输入一次">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="submit" class="btn btn-info">保&nbsp;存</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>