//检查表单上传文件中所选的文件类型是否满足要求
//检查上传文件类型
function check_type(input) {

	var accepts = $(input).attr('accept');
	if (accepts == undefined || accepts == '') {
		return;
	}

	var arr = accepts.split(",");

	var value = $(input).val();// 获取上传文件的名称
	var suffix = value.substring(value.indexOf(".") + 1).toLowerCase();

	var valid = false;// 上传的文件是否符合格式要求
	$.each(arr, function(i, val) {
		if (val.indexOf(suffix) != -1) {// 说明是要求格式中的其中一种
			valid = true;

			return false;
		}
	});

	if(!valid) {
		//清空所选
		$(input)[0].outerHTML += '';   
		$(input)[0].value ="";
		ZENG.msgbox.show("选择的文件不符合格式要求!", 1, 2000);
		
	}
	
}