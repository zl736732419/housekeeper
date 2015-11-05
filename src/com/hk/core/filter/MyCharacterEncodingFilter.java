package com.hk.core.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Administrator
 *
 * 用于处理请求传递的参数中文乱码问题
 * @time 2015年8月19日 下午4:16:23
 */
public class MyCharacterEncodingFilter implements Filter {
	
	private static final String GET_METHOD = "get";
	private static final String POST_METHOD = "post";
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		String methodType = request.getMethod();
		if(GET_METHOD.equalsIgnoreCase(methodType)) {//如果是get请求，需要手动设置编码方式
			request = new MyRequest(request);
		}else if(POST_METHOD.equalsIgnoreCase(methodType)) {//如果是post请求，直接可以设置setCharacterEncoding
			request.setCharacterEncoding("UTF-8");
		}
		
		chain.doFilter(request, resp);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
	
	@Override
	public void destroy() {
	}
}
