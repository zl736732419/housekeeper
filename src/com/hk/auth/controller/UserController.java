package com.hk.auth.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.hk.auth.domain.User;
import com.hk.auth.query.UserQuery;
import com.hk.auth.service.IUserService;
import com.hk.common.controller.BaseController;
import com.hk.common.domain.ResponseMsg;
import com.hk.common.domain.SysConst;
import com.hk.common.pager.Pager;
import com.hk.common.util.GsonUtil;
import com.hk.common.util.ResponseMsgUtil;
import com.hk.common.util.ResponseUtil;
import com.hk.logs.service.ISysLogService;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
	@Autowired
	private IUserService userService;
	
	@Autowired
	private ISysLogService logService;
	
	/**
	 * 获取用户列表信息
	 * @param query
	 * @param type 显示类型
	 * @return
	 */
	@RequestMapping(value="", method={RequestMethod.GET, RequestMethod.POST})
	public String getUserPage(UserQuery query, String type) {
		Pager<User> pager = userService.findAll(query);
		if(!StringUtils.isBlank(type)) {
			putContext(SysConst.USER_SHOW_TYPE, type);
		}
		putContext("pager", pager);
		putContext("query", query);
		
		return "/auth/user/list";
	}
	
	@RequestMapping(value="add", method=RequestMethod.GET)
	public String getAddUserPage(Integer id, String type) {
		//将用户显示的类型存放在session中，后面可能会用到
		putSessionCtx(SysConst.USER_SHOW_TYPE, type);
		if(id != null) {
			User user = userService.findById(id);
			putContext("user", user);
		}
		
		return "/auth/user/add";
	}

	@RequestMapping(value="exist", method=RequestMethod.POST)
	public String exist(String username) {
		boolean exist = false;
		//判断用户名是否存在,用户名必须是唯一的
		User u = userService.findByUsername(username);
		if(u != null) {
			exist = true;
		}
		
		ResponseUtil.writeText(response, exist);
		
		return null;
	}
	
	
	/**
	 * 用户添加后台处理提交数据
	 * 
	 * @auther zhenglian
	 * @date 2015年10月30日 上午10:04:21
	 * 
	 * @param photo
	 * @param user
	 * @return
	 */
	@RequestMapping(value="add", method=RequestMethod.POST)
	public String save(@RequestParam("photo") MultipartFile photo, User user) {
		ResponseMsg msg = null;
		String photoName = null;
		//判断用户名是否存在,用户名必须是唯一的
		User u = userService.findByUsername(user.getUsername());
		if(u != null) {
			msg = ResponseMsgUtil.buildMsg("error", "当前新增用户已经存在");
		}else {
			String originalFileName = photo.getOriginalFilename();
			if(StringUtils.isBlank(originalFileName)) {
				photoName = SysConst.DEFAULT_USER_PHOTO;
			}else {
				photoName = user.getUsername() + originalFileName.substring(originalFileName.indexOf("."));
				msg = userService.savePhoto(photo, photoName, user.getUsername());
			}
		}
		
		if(msg != null) {
			putContext("user", user);
			putContext("error", msg);
			return "/auth/user/add";
		}else {
			user.setPic(photoName);
			userService.addUser(user);
			return "redirect:/user";
		}
	}
	
	@RequestMapping(value="update", method=RequestMethod.POST)
	public String update(@RequestParam("photo") MultipartFile photo, User user) {
		String photoName = null;
		ResponseMsg msg = null;
		String originalFileName = photo.getOriginalFilename();
		if(!StringUtils.isBlank(originalFileName)) {
			photoName = user.getUsername() + originalFileName.substring(originalFileName.indexOf("."));
			msg = userService.savePhoto(photo, photoName, user.getUsername());
		}
		
		User u = userService.findById(user.getId());
		
		if(photoName != null) {
			//更新用户头像路径到对象中
			user.setPic(msg.getMsg());
		}
		userService.updateUser(user);
		
		return "redirect:/user";
	}
	
	/**
	 * 删除其他对象时需要对它级联的表进行处理，这里需要处理日志表，将user_id置为0
	 * @param ids 要删除的用户
	 * @param type 显示类型
	 */
	@RequestMapping(value="delete", method=RequestMethod.POST)
	public void delete(String ids, String type) {
		putSessionCtx(SysConst.USER_SHOW_TYPE, type);
		ResponseMsg msg = null;
		if(StringUtils.isBlank(ids)) {
			msg = ResponseMsgUtil.buildMsg(ResponseMsgUtil.ERROR, "删除失败,被删除用户为空!");
		}else {
			//首先将日志表中的user_id置为NULL
			logService.updateUserIdsNull(ids);
			//删除用户表中的记录
			userService.deleteUsers(ids);
			msg = ResponseMsgUtil.buildMsg(ResponseMsgUtil.SUCCESS, "操作成功!");
		}
		
		ResponseUtil.writeJson(response, GsonUtil.toString(msg));
	}
	
}
