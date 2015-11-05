package com.hk;

import java.util.ArrayList;
import java.util.List;

public class TestMain {
	
	private static List<String> initParameters(String str) throws Exception {
		List<String> params = new ArrayList<String>();
		
		StringBuilder builder = new StringBuilder();//解析str,获取每一个动态变量
		char[] chars = str.toCharArray();
		boolean begin = false;//变量开始
		for(char c : chars) {
			if(begin && c == '{'){
				throw new Exception("解析注解变量值:" + str + "发生异常, 不能连续出现{{");
			}
			if(!begin && c == '}') {
				throw new Exception("解析注解变量值:" + str + "发生异常, 不能连续出现}}");
			}
			if(c == '}') {
				begin = false;
				params.add(builder.toString());
				builder.delete(0, builder.length());//清空
			}
			if(begin) {
				builder.append(c);
			}
			if(c == '{') {
				begin = true;
			}
		}
		
		return params;
	}
	
	public static void main(String[] args) {
		String str = "hello{word}asf{woyo}{adf}";
		List<String> params = null;
		try {
			params = initParameters(str);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(params);
	}
}
