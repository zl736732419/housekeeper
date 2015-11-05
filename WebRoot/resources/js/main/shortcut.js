//id,iconName,iconUrl,url,width,height
//桌面图标
var shortcut = [
//[0,"我的电脑",getRootPath() + "resources/images/main/icos/01.png","http://www.tudou.com",1000,500],
//[1,"我的图片",getRootPath() + "resources/images/main/icos/02.png","http://www.w2bc.com/",1000,500],
//[2,"我的音乐",getRootPath() + "resources/images/main/icos/03.png","http://www.w2bc.com/",1000,500],
//[3,"我的指南",getRootPath() + "resources/images/main/icos/04.png","http://www.w2bc.com/",800,500],
//[4,"应用程序",getRootPath() + "resources/images/main/icos/05.png","http://www.w2bc.com/",600,500],
//[5,"我的私信",getRootPath() + "resources/images/main/icos/06.png","http://www.w2bc.com/",1000,500],
//[6,"色盘",getRootPath() + "resources/images/main/icos/07.png","http://www.w2bc.com/",1000,500],
//[7,"云上传",getRootPath() + "resources/images/main/icos/08.png","http://www.w2bc.com/",1000,500],
//[8,"云下载",getRootPath() + "resources/images/main/icos/09.png","http://www.w2bc.com/",1000,500],
//[9,"行程",getRootPath() + "resources/images/main/icos/10.png","http://www.w2bc.com/",1000,500],
//[10,"记事本",getRootPath() + "resources/images/main/icos/11.png","http://www.w2bc.com/",900,540],
[0,"权限管理",getRootPath() + "resources/images/main/icos/auth_mgt.png",getRootPath() + "user",900,540]
];

//桌面背景
var desktopbgs = [
    {
    	title : '背景1',
    	src : getRootPath() + "resources/images/main/deskbgs/1.jpg"
    },
    {
    	title : '背景2',
    	src : getRootPath() + "resources/images/main/deskbgs/2.jpg"
    }
];

//跳转到用户登录
function login() {
	window.location.href="login";
}

//修改密码
function changepwd() {
	
}

//右侧窗口下弹出框
var taskmenus = [{
	title : '切换用户',
	fn : 'login()'
}, {
	title : '修改密码',
	fn : 'void(0)',
	dialog : 'pwdmodal'
}];

