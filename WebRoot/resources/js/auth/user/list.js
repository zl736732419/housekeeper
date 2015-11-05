function getPopoverTitle(target) {
	var title = $(target).siblings('div.popover').find('h2.popover-title').html();
  	return title;
};

function getPopoverContent(target) {
	var content = $(target).siblings('div.popover').find('div.popover-content').html();
  	return content;
};

$(function() {
	//表头复选框点击事件
	$('input[name=ids]').click(function() {
		var checked = $(this).is(':checked');
		var checkboxs = $('#users_table').find('input[name=uid]');
		if(checked) {
			//全选
			for(var i = 0; i < checkboxs.length; i++) {
				var checkbox = $(checkboxs[i])[0];
				checkbox.checked = true;
				var tr = $(checkbox).parent().parent();
				$(tr).addClass("info");
			}
		}else {
			//全不选
			for(var i = 0; i < checkboxs.length; i++) {
				var checkbox = $(checkboxs[i])[0];
				checkbox.checked = false;
				var tr = $(checkbox).parent().parent();
				$(tr).removeClass("info");
			}
		}
	});
	
	$('.thumbnail').each(function(){
		$(this).popover({
		    html: true,
		    animation: true,
		   	container: 'body',
		    title: getPopoverTitle(this),
		    content: getPopoverContent(this),
			placement : 'right'
		}).css({
			margin:0,
			padding:0
		});
	});
	
	//缩略图点击事件
	$('.thumbnail').click(function() {
		var span = $(this).find('span');//找到当前图标span元素判断当前用户是否被选中
		var removeCls = null;
		var addCls = null;
		if(span.hasClass('selected-icon')) {//之前已经被选中
			removeCls = 'selected-icon';
			addCls = 'unselected-icon';
		}else { //没有被选中
			removeCls = 'unselected-icon';
			addCls = 'selected-icon';
		}
		
		$(span).removeClass(removeCls).addClass(addCls);
		
	});
	
	//单独点击列表记录中的复选框
	$('input[name=uid]').click(function() {
		var checked = $(this).is(':checked');
		//如果选中了该行记录，就将改行添加高亮
		var tr = $(this).parent().parent();
		if(checked) {
			$(tr).addClass("info");
		}else {
			$(tr).removeClass("info");
		}
		
		//判断所有复选框是否都选中了。如果都选中了就把表头checkbox选中
		var checkboxs = $('#users_table').find('input[name=uid]');
		var allchecked = true;
		for(var i = 0; i < checkboxs.length; i++) {
			var checkbox = $(checkboxs[i])[0];
			var checked = $(checkbox).is(':checked');
			if(!checked) {
				allchecked = false;
				break;
			}
		}
		
		if(!allchecked) {
			$('input[name=ids]')[0].checked = false;
		}else {
			$('input[name=ids]')[0].checked = true;
		}
	});
	
	//这里初始化显示内容的类型
	init_show_type();
	
});
//这里初始化显示内容的类型
function init_show_type() {
	var type = $('#type').val();
	if(type == '') {//默认以列表方式显示
		type = 'thumbnail';
	}
	
	show_type(type);
}

//展现数据的方式
function show_type(type) {
	///将当前显示的方式记下来后面编辑和删除会使用到
	$('#type').val(type);
	var selectedCls = 'btn btn-primary';
	var unselectedCls = 'btn btn-default';
	
	var selectedBtn = null;
	var unselectedBtn = null;
	
	var showContent = null;
	var hideContent = null;
	
	if(type == 'list') {//按照列表方式展示
		selectedBtn = '#list-btn';
		unselectedBtn = '#thumbnail-btn';
		
		showContent = '#list-content';
		hideContent = '#thumbnail-content';
	}else {
		selectedBtn = '#thumbnail-btn';
		unselectedBtn = '#list-btn';
		
		showContent = '#thumbnail-content';
		hideContent = '#list-content';
	}
	
	$(selectedBtn).attr('class', selectedCls);
	$(unselectedBtn).attr('class', unselectedCls);
	
	$(showContent).css({
		display : 'block'
	});
	
	$(hideContent).css({
		display : 'none'
	});
}

//新增用户
function add_user() {
	//得到当前显示的类型
	var type = $('#type').val();
	window.location.href = "user/add?type=" + type;
}

//编辑用户
function edit_user() {
	//得到当前显示的类型
	var type = $('#type').val();
	var uid = null;
	if(type == 'list') { //列表显示
		//获取用户选择的要编辑的用户
		var checkboxs = $('input[name=uid]:checked');
		if(checkboxs.length != 1) {
			ZENG.msgbox.show("请选择一条记录进行编辑操作！", 1, 2000);
			return;
		}
		uid = $(checkboxs)[0].value;
	}else { //缩略图显示
		var spans = $('.select-btn').find('span.selected-icon');
		if(spans.length != 1) {
			ZENG.msgbox.show("请选择一条记录进行编辑操作！", 1, 2000);
			return;
		}
		
		uid = $(spans).find('input').val();
	}
	
	
	window.location.href = "user/add?id=" + uid + "&type=" + type;
}

//删除用户
function delete_user() {
	var type = $('#type').val();
	//获取选择要删除的用户id
	var ids = [];
	if(type == 'list') { //列表显示
		var checkboxs = $('input[name=uid]:checked');
		if(checkboxs.length == 0) {
			ZENG.msgbox.show("请选择一条记录进行删除操作！", 1, 2000);
			return;
		}
		
		$.each(checkboxs, function(i, checkbox) {
			var id = $(checkbox).val();
			ids.push(id);
		});
	}else {
		var spans = $('.select-btn').find('span.selected-icon');
		if(spans.length == 0) {
			ZENG.msgbox.show("请选择一条记录进行删除操作！", 1, 2000);
			return;
		}
		
		$(spans).each(function() {
			var id = $(this).siblings('input').val();
			ids.push(id);
		});
	}
	var params = {
		ids : ids.join(','),
		type : type
	}
	
	AjaxUtil.send(params, 'user/delete', 'post', 'json', false, function(data) {
		var status = data.status;
		ZENG.msgbox.show(data.msg, 4, 2000);
		if(status == 'success') {
			window.location.href='user';//重新加载
		}
	});
	
	
}