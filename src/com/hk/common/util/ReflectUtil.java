package com.hk.common.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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

import com.hk.logs.annotations.LogActionDesc;
import com.hk.logs.annotations.LogRecord;
import com.hk.logs.annotations.LogRequiredType;

/**
 * 针对日志的反射工具类
 *
 * @author zhenglian
 * @data 2015年10月18日 上午11:37:00
 */
public class ReflectUtil {

	/**
	 * 根据给定类和方法名获取方法
	 * 
	 * @param cls
	 * @param methodName
	 * @return
	 */
	public static Method getMethod(Class<?> cls, String methodName) {
		Method method = null;
		try {
			method = cls.getMethod(methodName);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return method;
	}
	
	/**
	 * 获取指定类的属性值
	 * 
	 * @param cls 目标类
	 * @param propertyName 属性名
	 * @return
	 */
	public static Object getProperty(Object target, String propertyName) {
		String methodName = "get" + String.valueOf(propertyName.charAt(0)).toUpperCase() + propertyName.substring(1);
		Method method = getMethod(target.getClass(), methodName);
		Object value = null;
		try {
			value = method.invoke(target);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return value;
		
	}

	/**
	 * 获取类的日志描述
	 * 
	 * @param obj
	 * @return
	 */
	public static String getLogDescription(Object obj) {
		LogActionDesc actionDesc = null;// 描述方法或类的注解
		if (obj instanceof Class) {// 获取类的描述
			actionDesc = getAnnotation((Class<?>) obj, LogActionDesc.class);
		} else if (obj instanceof Method) {// 获取方法描述
			actionDesc = getAnnotation((Method) obj, LogActionDesc.class);
		}

		if (actionDesc != null) {
			return actionDesc.description();
		} else {
			return "";
		}
	}

	/**
	 * 获取类的日志标记 如果被日志的类没有标注该注解，默认就是允许记录日志的LogRequiredType.REQUIRED
	 * 
	 * @param obj
	 * @return
	 */
	public static LogRequiredType getLogRequiredType(Object obj) {
		LogRecord logRecord = null;

		if (obj instanceof Class) {// 获取类的描述
			logRecord = getAnnotation((Class<?>) obj, LogRecord.class);
		} else if (obj instanceof Method) {// 获取方法描述
			logRecord = getAnnotation((Method) obj, LogRecord.class);
		}

		if (logRecord != null) {
			return logRecord.record();
		} else {
			return LogRequiredType.REQUIRED;
		}

	}

	@SuppressWarnings("unchecked")
	private static <T> T getAnnotation(AnnotatedElement e,
			Class<? extends Annotation> cls) {
		T t = (T) (e.isAnnotationPresent(cls) ? e.getAnnotation(cls) : null);
		return t;
	}
	
	/**
	 * 得到指定类的某一个方法的形参名列表
	 * 
	 * @param clazz
	 * @param methodName
	 * @return
	 * @throws Exception
	 */
	public static List<String> getMethodParamNames(Class clazz, String methodName)
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
			}
		} catch (NotFoundException e) {
			e.printStackTrace();
		}

		return names;
	}
}
