$(function() {
	//默认将焦点交由username管理
	var KEY_UP = 40;
	var KEY_DOWN = 38;
	// 进入页面后，直接把焦点交给username
	$('#username').focus();
	// 监听上下按键
	$(document).keydown(function(event) {
		var isFocus = false;
		if (KEY_UP == event.keyCode) {// 用户按上键
			isFocus = $('#username').is(':focus');
			if (isFocus) {
				$('#password').focus();
			}
		} else if (KEY_DOWN == event.keyCode) {// 用户按下键
			isFocus = $('#password').is(':focus');
			if (isFocus) {
				$('#username').focus();
			}
		}
	});
});

//根据用户名和密码进行登录操作
function doLogin() {
	var form = window.document.forms[0];
	var url = form.action;
	var params = $(form).serialize();
	$('.login-form').mask('正在登录中...');
	AjaxUtil.send(params, url, 'post', 'json', false, function(data) {
		$('.login-form').unmask();
		var status = data.status;
		if(status == 'success') {
			window.location.href='main';
		}else {
			var msg = data.msg;
			$('#error_msg').html(msg);
		}
	});
}

