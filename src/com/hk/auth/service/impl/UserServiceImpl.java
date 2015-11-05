package com.hk.auth.service.impl;

import java.io.File;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.hk.auth.domain.User;
import com.hk.auth.mapper.UserMapper;
import com.hk.auth.service.IUserService;
import com.hk.common.domain.ResponseMsg;
import com.hk.common.domain.SysConst;
import com.hk.common.exception.HandleRuntimeException;
import com.hk.common.mapper.BaseMapper;
import com.hk.common.service.impl.BaseServiceImpl;
import com.hk.common.util.ClientUtil;
import com.hk.common.util.MD5Util;
import com.hk.common.util.ResponseMsgUtil;
import com.hk.common.util.UploadFileUtil;
import com.hk.logs.annotations.LogActionDesc;

/**
 * 用户实体业务接口实现类
 *
 * @author zhenglian
 * @data 2015年10月15日 下午10:53:32
 */
@Transactional
@Service
@LogActionDesc(description = "用户管理")
public class UserServiceImpl extends BaseServiceImpl<User> implements
		IUserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	protected BaseMapper<User> getBaseMapper() {
		return userMapper;
	}

	@Override
	public User doLogin(String username, String password) throws Exception {
		// 非空验证
		if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
			return null;
		}

		// 业务验证，验证当前用户是否存在，并且输入密码是否正确
		User user = userMapper.findByUsername(username);
		if (user == null) {
			throw new HandleRuntimeException("当前登录用户" + username + "不存在!");
		}

		if (!password.equals(user.getPassword())) {
			throw new HandleRuntimeException("登录密码错误!");
		}

		return user;
	}

	@Override
	@LogActionDesc(description = "保存用户头像")
	public ResponseMsg savePhoto(MultipartFile photo, String photoName, String username) {
		ResponseMsg msg = null;
		//tomcat/webapps/housekeeper/
		String path = ClientUtil.getRequest().getServletContext().getRealPath("/").replace("\\", File.separator);
		String rootPath = path.substring(0, path.lastIndexOf("webapps"));
		String localDir = rootPath + SysConst.USER_PHOTO_DIR;
		try {
			UploadFileUtil.uploadFile(photo, localDir, photoName);
		} catch (Exception e) {
			e.printStackTrace();
			msg = ResponseMsgUtil.buildMsg("error", "保存头像失败,请稍候再试!");
		}
		return msg;
		
	}

	@Override
	@LogActionDesc(description = "保存用户")
	public void addUser(User user) {
		
		//将用户密码加密
		String password = MD5Util.getMD5(user.getPassword());
		user.setPassword(password);
		
		userMapper.save(user);
	}

	@Override
	@LogActionDesc(description = "修改用户")
	public void updateUser(User user) {
		//获取数据库id用户
		User dbUser = userMapper.findById(user.getId());
		//将值更新
		dbUser.setNickname(user.getNickname());
		dbUser.setTel(user.getTel());
		dbUser.setSex(user.getSex());
		dbUser.setAge(user.getAge());
		String pic = user.getPic();
		if(!StringUtils.isBlank(pic)) {
			dbUser.setPic(pic);
		}
		
		userMapper.update(user);
	}

	@Override
	@LogActionDesc(description = "删除用户")
	public void deleteUser(int id) {
		userMapper.deleteById(id);
	}

	@Override
	public User findByUsername(String username) {
		return userMapper.findByUsername(username);
	}

	@Override
	@LogActionDesc(description = "删除多个用户")
	public void deleteUsers(String ids) {
		userMapper.deleteByIds(ids);
	}

}
