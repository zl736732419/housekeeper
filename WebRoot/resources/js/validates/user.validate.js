$(function() {
	$("#user_add_form").validate({
		rules : {
			username : {
				required : true,
				maxlength : 20,
				notexist : true
			},
			nickname : {
				required : true
			},
			password : {
				required : true,
				maxlength : 20
			},
			confirmpwd : {
				required : true,
				equalTo : '#password'
			},
			tel : {
				phone : true
			},
			age : {
				digitals : true,
				min : 1,
				max : 120
			}
		},
		messages : {
			username : {
				required : "请输入用户名",
				maxlength : "用户名长度最多20位",
			},
			nickname : {
				required : "请输入昵称"
			},
			password : {
				required : "请输入密码",
				maxlength : "密码长度最多20位"
			},
			confirmpwd : {
				required : "请再次输入密码",
				equalTo : "两次输入的密码不一致!"
			},
			tel : {
				phone : '请输入正确的手机号'
			},
			age : {
				digitals : "请输入0-9的数字",
				min : "年龄必须大于0",
				max : "年龄最大不超过120"
			}
			
		},
		errorPlacement : function(error, element) {
			$(element).parent().parent().find('span').text($(error).text());
		},
		highlight : function(element, errorClass) {
			$(element).parent().parent().addClass('has-error');
		},
		unhighlight : function(element, errorClass) {
			$(element).parent().parent().find('span').text('');
			$(element).parent().parent().removeClass('has-error');
		},
		invalidHandler : function(){
			return false;//验证不正确，阻止表单提交
		},
		submitHandler : function(form) {
			save_update();
		}
	});
});