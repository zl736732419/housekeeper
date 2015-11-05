package com.hk.logs.service;

import com.hk.auth.domain.User;
import com.hk.common.service.IBaseService;
import com.hk.logs.domain.SysLog;

public interface ISysLogService extends IBaseService<SysLog> {
	/**
	 * 向数据库插入一条日志
	 * 
	 * @param user
	 *            用户
	 * @param ip
	 *            用户ip
	 * @param modelName
	 *            访问的模块
	 * @param methodName
	 *            访问模块下的方法
	 * @param logInfo
	 *            日志信息
	 * @return
	 */
	public void insertLog(User user, String ip, String modelName,
			String methodName, String logInfo);
	
	/**
	 * 根据用户id清除日志中与该用户的关联
	 * 
	 * @param userIds
	 */
	public void updateUserIdsNull(String userIds);
}
