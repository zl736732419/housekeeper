package com.hk.auth.service;

import org.springframework.web.multipart.MultipartFile;

import com.hk.auth.domain.User;
import com.hk.common.domain.ResponseMsg;
import com.hk.common.service.IBaseService;

/**
 * 操作用户实体的业务接口
 *
 * @author zhenglian
 * @data 2015年10月15日 下午10:52:12
 */
public interface IUserService extends IBaseService<User> {

	/**
	 * 处理登录
	 * @param username
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public User doLogin(String username, String password) throws Exception;
	
	/**
	 * 保存用户头像
	 * @param file
	 * @param photoName
	 * @param username
	 */
	public ResponseMsg savePhoto(MultipartFile file, String photoName, String username);
	
	/**
	 * 添加用户
	 * 
	 * @param user
	 */
	public void addUser(User user);
	
	/**
	 * 编辑用户
	 * @param user
	 */
	public void updateUser(User user);
	
	/**
	 * 删除用户
	 * @param id
	 */
	public void deleteUser(int id);
	
	
	/**
	 * 批量删除用户
	 * @param ids
	 */
	public void deleteUsers(String ids);
	
	/**
	 * 检查用户是否存在
	 * @param username
	 */
	public User findByUsername(String username);
	
}
