package com.hk.common.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


/**
 * @author zhenglian
 *
 * 关于json的工具包
 * @time 2015-8-14 下午4:12:36
 */
public class GsonUtil {
	
	public static String toString(Object obj) {
		Gson gson = new GsonBuilder()
			.serializeNulls()
			.setDateFormat("yyyy-MM-dd HH:mm:ss")
			.create();
		return gson.toJson(obj);
	}
	
}
