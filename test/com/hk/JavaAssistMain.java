package com.hk;

import java.util.ArrayList;
import java.util.List;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.Modifier;
import javassist.NotFoundException;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;

public class JavaAssistMain {

	public void test(String arg1, String arg2) {
		System.out.println(arg1 + ", " + arg2);
	}

	public static void main(String[] args) throws Exception {
		JavaAssistMain test = new JavaAssistMain();
		List<String> paramNames = test.getMethodParamNames(JavaAssistMain.class, "test");
		System.out.println(paramNames);
	}

	/**
	 * 得到指定类的某一个方法的形参名列表
	 * 
	 * @param clazz
	 * @param methodName
	 * @return
	 * @throws Exception
	 */
	public List<String> getMethodParamNames(Class clazz, String methodName)
			throws Exception {
		List<String> names = new ArrayList<String>();
		try {
			ClassPool pool = ClassPool.getDefault();
			CtClass cc = pool.get(clazz.getName());
			CtMethod cm = cc.getDeclaredMethod(methodName);
			// 使用javaassist的反射方法获取方法的参数名
			MethodInfo methodInfo = cm.getMethodInfo();
			CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
			LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute
					.getAttribute(LocalVariableAttribute.tag);
			if (attr == null) {
				// exception
				throw new Exception("获取" + clazz.getName() + "类的"
						+ cm.getName() + "方法参数发生异常!");
			}
			String[] paramNames = new String[cm.getParameterTypes().length];
			int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;
			for (int i = 0; i < paramNames.length; i++) {
				paramNames[i] = attr.variableName(i + pos);
				names.add(paramNames[i]);
			// paramNames即参数名
			}

		} catch (NotFoundException e) {
			e.printStackTrace();
		}

		return names;
	}
	
	/**
	 * 获取当前注解描述符中的参数
	 * @param variableMethodDesc 执行方法的描述，其中可能包含变量，需要初始化
	 * @return
	 */
	private List<String> getMethodParams(String variableMethodDesc) throws Exception {
		List<String> params = new ArrayList<>();
		
		char[] chars = variableMethodDesc.toCharArray();
		StringBuilder builder = new StringBuilder();
		boolean paramStart = false;//是否为变量名开始
		
		for(int i = 0 ; i < chars.length; i++) {
			if(chars[i] == '{' && paramStart) {
				throw new Exception("解析注解值:" + variableMethodDesc + "错误,不合法的赋值，不能连续出现两个}");
			}
			
			if(chars[i] == '}' && !paramStart) {
				throw new Exception("解析注解值:" + variableMethodDesc + "错误,不合法的赋值，不能连续出现两个}");
			}
			
			if(chars[i] == '{') {//变量名开始
				paramStart = true;
				continue;
			}
			
			if(chars[i] == '}') {
				paramStart = false;
				params.add(builder.toString().trim());
				builder.delete(0, builder.length());//清空变量构造器，搜索下一个变量
				continue;
			}
			
			if(paramStart) {
				builder.append(chars[i]);
			}
			
		}
		
		return params;
	}
}
