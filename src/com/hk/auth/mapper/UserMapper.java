package com.hk.auth.mapper;

import com.hk.auth.domain.User;
import com.hk.common.mapper.BaseMapper;


/**
 * 用户实体的数据接口
 *
 * @author zhenglian
 * @data 2015年10月15日 下午10:28:33
 */
public interface UserMapper extends BaseMapper<User>{
	
	/**
	 * 根据用户名查询用户
	 * 
	 * @param username
	 * @return
	 * @throws Exception
	 */
	public User findByUsername(String username);
}