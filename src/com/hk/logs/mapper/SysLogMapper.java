package com.hk.logs.mapper;

import com.hk.common.mapper.BaseMapper;
import com.hk.logs.domain.SysLog;

/**
 * 操作日志的数据接口
 *
 * @author zhenglian
 * @data 2015年10月18日 上午9:43:46
 */
public interface SysLogMapper extends BaseMapper<SysLog>{
	
	/**
	 * 清空日志表中的被删除用户的user_id
	 * 
	 * @param userId
	 */
	public void setUsersNullByUserIds(String userId);
}
