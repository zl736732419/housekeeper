$(function() {
	$("#login_form").validate({
		rules : {
			username : {
				required : true,
				maxlength : 20
			},
			password : {
				required : true,
				maxlength : 20
			}
		},
		messages : {
			username : {
				required : "请输入用户名",
				maxlength : "用户名长度最多20位"
			},
			password : {
				required : "请输入密码",
				maxlength : "密码长度最多20位"
			}
		},
		errorPlacement : function(error, element) {
			$(element).attr('placeholder', $(error).text());
		},
		highlight : function(element, errorClass) {
			$(element).css('border-color', 'red');
		},
		unhighlight : function(element, errorClass) {
			$(element).removeClass(errorClass);
			$(element).css('border-color', '#ded6d6');
		},
		invalidHandler : function(){
			return false;//验证不正确，阻止表单提交
		},
		submitHandler : function(form) {
			doLogin();
			return false;//阻止表单自动提交
		}
		
	});
});