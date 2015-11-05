package com.hk.logs.domain;

import java.util.Date;

/**
 * 操作日志实体
 *
 * @author zhenglian
 * @data 2015年10月18日 上午9:39:34
 */
public class SysLog {
	private Integer id;
	private Integer userId;
	private String username;
	private String logInfo;
	private String modelName;
	private String methodName;
	private String ip;
	private Date logTime;

	public SysLog() {
	}

	public SysLog(Integer userId, String username, String logInfo,
			String modelName, String methodName, String ip, Date logTime) {
		this.userId = userId;
		this.username = username;
		this.logInfo = logInfo;
		this.modelName = modelName;
		this.methodName = methodName;
		this.ip = ip;
		this.logTime = logTime;
	}

	public Integer getId() {
		return id;
	}

	public String getIp() {
		return ip;
	}

	public String getLogInfo() {
		return logInfo;
	}

	public Date getLogTime() {
		return logTime;
	}

	public String getMethodName() {
		return methodName;
	}

	public String getModelName() {
		return modelName;
	}

	public Integer getUserId() {
		return userId;
	}

	public String getUsername() {
		return username;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setLogInfo(String logInfo) {
		this.logInfo = logInfo;
	}

	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "SysLog [id=" + id + ", userId=" + userId + ", username="
				+ username + ", logInfo=" + logInfo + ", modelName="
				+ modelName + ", methodName=" + methodName + ", ip=" + ip
				+ ", logTime=" + logTime + "]";
	}

}
