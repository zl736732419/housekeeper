package com.hk.common.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ModelAttribute;

import com.hk.common.util.ClientUtil;

public abstract class BaseController {

	protected HttpServletResponse response;
	protected HttpServletRequest request;
	
	@ModelAttribute
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	@ModelAttribute
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	/**
	 * 将值放入request请求域中
	 * @param key
	 * @param value
	 */
	protected void putContext(String key, Object value) {
		ClientUtil.getRequest().setAttribute(key, value);
	}
	
	/**
	 * 将数据保存到session域中
	 * @param key
	 * @param value
	 */
	protected void putSessionCtx(String key, Object value) {
		ClientUtil.getRequest().getSession().setAttribute(key, value);
	}
	
}
