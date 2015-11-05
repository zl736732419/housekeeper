$(function() {
//初始化popover插件
	$('button[data-toggle=popover]').mouseover(function() {
		$(this).popover('show');
	});
	$('button[data-toggle=popover]').mouseout(function() {
		$(this).popover('hide');
	});
	
	$('a[data-toggle=popover]').mouseover(function() {
		$(this).popover('show');
	});
	$('a[data-toggle=popover]').mouseout(function() {
		$(this).popover('hide');
	});
	
	$('div[data-toggle=popover]').mouseover(function() {
		$(this).popover('show');
	});
	
	$('div[data-toggle=popover]').mouseout(function() {
		$(this).popover('hide');
	});
});
