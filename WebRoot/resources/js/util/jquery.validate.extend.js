$(function() {
	//数字验证，只能是[0,9]的数字
	jQuery.validator.addMethod('digitals', function(value, element){
		return this.optional(element) || /^\d+$/.test(value); 
	}, "只能输入0到9的数字!");
	
	
	//验证手机号码
	jQuery.validator.addMethod('phone', function(value, element) {
		var tel = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
		return this.optional(element) || tel.test(value);
	}, "请输入正确手机号码!");
	
	//验证用户是否存在
	jQuery.validator.addMethod('notexist', function(value, element) {
		var param = 'username=' + value;
		var result = true;
		AjaxUtil.send(param, 'user/exist', 'post', 'text', false, function(exist) {
			if(exist === 'true') {//数据库中存在
				result = false;
			}
		});
		
		return result;
	}, "用户已存在!");
});