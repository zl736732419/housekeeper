function back() {
	window.history.back();
}

//新增或编辑操作
function save_update() {
	var id = $('#id').val();
	var action = 'user/add';
	if(id != '') {
		action = 'user/update';
	}else {
		action = 'user/add';
	}
	$('#user_add_form')[0].action = action;
	
	$('#user_add_form')[0].submit();
}
