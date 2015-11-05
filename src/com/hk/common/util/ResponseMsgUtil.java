package com.hk.common.util;

import com.hk.common.domain.ResponseMsg;

/**
 * @author zhenglian
 *
 * 用于创建响应消息的工具类
 * @time 2015-8-12 下午2:08:45
 */
public class ResponseMsgUtil {

	public static final String SUCCESS = "success";
	public static final String ERROR = "error";
	public static final String WARNING = "warning";
	
	/**
	 * 用户自己设置状态和消息
	 * @param status
	 * @param msg
	 * @return
	 */
	public static ResponseMsg buildMsg(String status, String msg) {
		ResponseMsg responseMsg = new ResponseMsg(status, msg);
		return responseMsg;
	}
	
	public static ResponseMsg getSuccessMsg() {
		return new ResponseMsg(SUCCESS, "操作成功！");
	}
	
	public static ResponseMsg getFailedMsg() {
		return new ResponseMsg(ERROR, "操作失败!");
	}
	
	public static ResponseMsg getWarningMsg() {
		return new ResponseMsg(WARNING, "操作异常!");
	}
	
	
}
