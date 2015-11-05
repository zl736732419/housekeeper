package com.hk.common.domain;

/**
 * 存放项目中使用到的常量
 *
 * @author zhenglian
 * @data 2015年10月18日 上午9:00:24
 */
public class SysConst {

	/**
	 * 发送响应数据时使用的编码方式
	 */
	public static final String ENCODEING_UTF8 = "UTF-8";
	
	/**
	 * 用户登录成功后放入session的键
	 */
	public static final String LOGIN_USER = "loginUser";
	
	/**
	 * 用户显示的方式
	 */
	public static final String USER_SHOW_TYPE = "userShowType";
	
	/**
	 * 用户头像文件夹
	 */
	public static final String USER_PHOTO_DIR = "users";

	/**
	 * 用户默认头像
	 */
	public static final String DEFAULT_USER_PHOTO = "default.jpg";
	
	/**
	 * 变量开始符号
	 */
	public static final char VARIABLE_START_TAG = '{';
	
	/**
	 * 变量结束符号
	 */
	public static final char VARIABLE_END_TAG = '}';
	
}
