<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page session="true"%>
<%@ page isELIgnored="false"%>
<script type="text/javascript">
	function go(no) {
		$('#pageNo').val(no);
		//search();//方法在query.jsp中定义
		$('#pagerForm').submit();
	}

	//只能输入数字
	function is_number(e) {
		//IE 中 Event 对象使用 keyCode 获得键入字符的 Unicode 编码  
		//DOM 中 Event 对象 使用 charCode 获得键入字符的 Unicode 编码  
		var char_code = e.charCode ? e.charCode : e.keyCode;
		//Unicode 编码中， 0~9 的编码的十进制 是 48~57 之间 ， 0为 48， 9 为57  
		if ((48 <= char_code && char_code <= 57) || (char_code == 8)
				|| (char_code >= 37 && char_code <= 40)) {
			return true;
		} else {
			return false;
		}
	}

	//页面手动跳转
	function jump() {
		var num = $('#num').val();
		if (num == '') {
			num = 1;
		}
		go(num);
	}

	$(function() {
		var pageSize = $(size).val();
		var options = $('#pageSize')[0].options;
		$(options).each(function() {
			var value = $(this)[0].value;
			if (value == pageSize) {
				$(this)[0].selected = true;
				return;
			}
		});
	});
</script>
<div class="row">
	<div class="col-sm-12" style="text-align:center">
		<input id="pageNo" type="hidden" name="pageNo" value="${pager.pageNo}">
		<input id="size" type="hidden" value="${pager.pageSize}">
		<ul class="pagination" style="padding: 0px;">
			<c:choose>
				<c:when test="${pager.pageNo <= 1}">
					<li class="disabled"><a href="javascript:go(1)"
						data-container="body" data-toggle="popover" data-placement="top"
						data-content="首页">&laquo;</a></li>
					<li class="disabled"><a href="javascript:go(${pager.pageNo-1})"
						data-container="body" data-toggle="popover" data-placement="top"
						data-content="上一页">&lt;</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="javascript:go(1)" data-container="body"
						data-toggle="popover" data-placement="bottom" data-content="首页">&laquo;</a></li>
					<li><a href="javascript:go(${pager.pageNo-1})"
						data-container="body" data-toggle="popover" data-placement="bottom"
						data-content="上一页">&lt;</a></li>
				</c:otherwise>
			</c:choose>
			<%-- 构建分页页面选项卡，最多为5页 --%>
			<c:choose>
				<c:when test="${pager.totalPages <= 5}">
					<c:forEach begin="1" step="1" end="${pager.totalPages }" var="item">
						<c:choose>
							<c:when test="${item == pager.pageNo}">
								<li class="active"><a href="javascript:go(${item})">${item}</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="javascript:go(${item})">${item}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</c:when>
				<%-- 页面超过5页--%>
				<c:otherwise>
					<c:choose>
						<%--  这里已经是最后两页这种情况--%>
						<c:when test="${pager.totalPages - pager.pageNo <= 1} ">
							<c:forEach begin="${pager.totalPages - 4 }" step="1"
								end="${pager.totalPages}" var="item">
								<c:choose>
									<c:when test="${item == pager.pageNo}">
										<li class="active"><a href="javascript:go(${item})">${item}</a></li>
									</c:when>
									<c:otherwise>
										<li><a href="javascript:go(${item})">${item}</a></li>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<c:choose>
								<%-- 当前页为前两页其中之一 --%>
								<c:when test="${pager.pageNo == 1 or pager.pageNo == 2}">
									<c:forEach begin="1" step="1" end="5" var="item">
										<c:choose>
											<c:when test="${item == pager.pageNo}">
												<li class="active"><a href="javascript:go(${item})">${item}</a></li>
											</c:when>
											<c:otherwise>
												<li><a href="javascript:go(${item})">${item}</a></li>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</c:when>
								<%-- 1,2,c,4,5 当前页始终显示在中央 --%>
								<c:otherwise>
									<c:forEach begin="${pager.pageNo - 2}" step="1"
										end="${pager.pageNo + 2}" var="item">
										<c:choose>
											<c:when test="${item == pager.pageNo}">
												<li class="active"><a href="javascript:go(${item})">${item}</a></li>
											</c:when>
											<c:otherwise>
												<li><a href="javascript:go(${item})">${item}</a></li>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</c:otherwise>
							</c:choose>
						</c:otherwise>
					</c:choose>
				</c:otherwise>
			</c:choose>
			<c:choose>
				<c:when test="${pager.pageNo >= pager.totalPages}">
					<li class="disabled"><a href="javascript:go(${pager.pageNo+1})"
						data-container="body" data-toggle="popover" data-placement="bottom"
						data-content="下一页">&gt;</a></li>
					<li class="disabled"><a
						href="javascript:go(${pager.totalPages})" data-container="body"
						data-toggle="popover" data-placement="bottom" data-content="尾页">&raquo;</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="javascript:go(${pager.pageNo+1})"
						data-container="body" data-toggle="popover" data-placement="top"
						data-content="下一页">&gt;</a></li>
					<li><a href="javascript:go(${pager.totalPages})"
						data-container="body" data-toggle="popover" data-placement="top"
						data-content="尾页">&raquo;</a></li>
				</c:otherwise>
			</c:choose>
		</ul>
		<div class="pagination">
			<input id="num" value='${pager.pageNo }' type="text"
				class="form-control" onkeypress="return is_number(event);"
				style="width: 50px; height: 33px; float: left; margin-left: 10px;" />
	
			<select id="pageSize" name="pageSize" class="form-control"
				style="margin-left: 5px; display: inline-block; width:50px; padding:0; height: 33px; float: left;">
				<option value="10">10</option>
				<option value="20">20</option>
				<option value="30">30</option>
				<option value="50">50</option>
			</select>
	
			<button class="btn btn-primary"
				style="height: 33px; float: left; margin-left: 10px;"
				onclick="jump();">跳转</button>
	
			<div style="float:left;margin-left:10px;height:33px;line-height: 33px;">
				<span>共 </span> <span class="label label-info"
					style="font-size: 10pt;">${pager.totalPages}</span> <span> 页</span>
				&emsp; <span>查询到 </span> <span class="label label-success"
					style="font-size: 10pt;">${pager.totalRows}</span> <span> 条记录</span>
			</div>
		</div>
	</div>
</div>