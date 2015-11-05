package com.hk.common.domain;

/**
 * @author zhenglian
 * 
 *         返回给客户端的响应状态信息
 * @time 2015-8-12 下午2:07:35
 */
public class ResponseMsg {

	private String status;
	private String msg;

	public ResponseMsg(String status, String msg) {
		this.status = status;
		this.msg = msg;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
