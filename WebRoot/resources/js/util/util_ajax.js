(function() {
	function AjaxUtil() {
		var ajax = this;
		
		ajax.send = function(params, url, method, dataType, async, callback) {
			$.ajax({
				type : method,
				dataType : dataType,
				async : async,
				data : params,
				url : url,
				success : function(data) {
					callback(data);
				}
			});
		}
		
		
	}
	
	window.AjaxUtil = new AjaxUtil();
})();