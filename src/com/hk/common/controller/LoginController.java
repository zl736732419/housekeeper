package com.hk.common.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hk.auth.domain.User;
import com.hk.auth.service.IUserService;
import com.hk.common.domain.ResponseMsg;
import com.hk.common.domain.SysConst;
import com.hk.common.exception.HandleRuntimeException;
import com.hk.common.util.ClientUtil;
import com.hk.common.util.GsonUtils;
import com.hk.common.util.MD5Util;
import com.hk.common.util.ResponseMsgUtil;
import com.hk.common.util.ResponseUtil;

/**
 * 处理用户登录注销操作
 *
 * @author zhenglian
 * @time 2015年10月16日 下午3:38:24
 */
@Controller
public class LoginController extends BaseController {

	private Logger logger = Logger.getLogger(LoginController.class);

	@Autowired
	private IUserService userService;

	/**
	 * 跳转到登录页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		request.getSession().invalidate();
		return "login";
	}

	/**
	 * 处理用户登录请求
	 * 
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String doLogin(User user) {
		// 将用户传递过来的密码进行加密
		String password = MD5Util.getMD5(user.getPassword());
		User u = null;
		ResponseMsg msg = null;
		try {
			u = userService.doLogin(user.getUsername(), password);
		} catch (Exception e) {
			if (e instanceof HandleRuntimeException) {
				HandleRuntimeException exception = (HandleRuntimeException) e;
				String message = exception.getMessage();
				msg = ResponseMsgUtil.buildMsg(ResponseMsgUtil.ERROR, message);
				ResponseUtil.writeJson(response, GsonUtils.toString(msg));
			} else {
				logger.error("需要进行处理的异常：" + e);
			}

			return null;// 如果出现异常就说明登录失败，不再执行后面的登录成功操作
		}

		putSessionCtx(SysConst.LOGIN_USER, u);

		msg = ResponseMsgUtil.getSuccessMsg();
		ResponseUtil.writeJson(response, GsonUtils.toString(msg));
		logger.info("登录成功!");

		return null;
	}
}
