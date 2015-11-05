//获取项目根路径
function getRootPath() {
	var pathName = window.location.pathname.substring(1);
	var webName = pathName == '' ? '' : pathName.substring(0, pathName.indexOf('/'));
	return window.location.protocol + '//' + window.location.host + '/'+ webName + '/';
}

//获取通过连接地址传递过来的参数，针对get方式有效
function getUrlParams() {
    var params = [],
        hash;
    var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
    for (var i = 0; i < hashes.length; i++) {
        hash = hashes[i].split('=');
        params.push(hash[0]);
        params[hash[0]] = hash[1];
    }
    return params;
}

//获取链接上的请求参数
function getUrlParam(name) {
    return getUrlParams()[name];
}

