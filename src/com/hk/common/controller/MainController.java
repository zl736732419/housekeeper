package com.hk.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 跳转到主页控制器
 *
 * @author zhenglian
 * @data 2015年10月18日 下午9:13:02
 */
@Controller
public class MainController {
	
	
	@RequestMapping(value="/main", method=RequestMethod.GET)
	public String main() {
		
		
		return "main";
	}
	
}
