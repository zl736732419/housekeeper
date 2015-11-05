package com.hk.core.filter;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.log4j.Logger;

public class MyRequest extends HttpServletRequestWrapper {
	private static Logger logger = Logger.getLogger(MyRequest.class);
	public static final String TARGET_ENCODING="UTF-8";
	public static final String SRC_ENCODING="ISO-8859-1";//浏览器地址栏默认请求的编码方式为ISO-8859-1
	
	public MyRequest(HttpServletRequest request) {
		super(request);
	}

	/**
	 * 需要手动对数据进行解码
	 * 
	 * @param data 源数据
	 * @param srcEncoding 源编码方式
	 * @param targetEncoding 要转化的目标编码，这里采用UTF-8
	 * @return
	 */
	private String characterEncoding(String data, String srcEncoding, String targetEncoding) {
		String result = null;
		try {
			byte[] bytes = data.getBytes(srcEncoding);
			result = new String(bytes, targetEncoding);
		} catch (UnsupportedEncodingException e) {
			logger.error("对get请求获取的数据" + data + "编码发生异常:" + e.getMessage());
		}
		
		return result;
	}
	
	@Override
	public String getParameter(String name) {
		String value = super.getParameter(name);
		if(value != null) {
			value = characterEncoding(value, SRC_ENCODING, TARGET_ENCODING);
		}
		
		return value;
	}

}
