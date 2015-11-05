package com.hk.core.logs;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javassist.ClassPool;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;

import com.hk.auth.domain.User;
import com.hk.common.domain.SysConst;
import com.hk.common.util.ClientUtil;
import com.hk.common.util.ReflectUtil;
import com.hk.logs.annotations.LogRequiredType;
import com.hk.logs.service.ISysLogService;

/**
 * 抽象日志处理切面类 该类为所有日志处理切面的父类，定义了通用的处理方式
 * 
 * @author zhenglian
 * @data 2015年10月18日 上午10:53:16
 */
public abstract class AbstractLogHandler {

	@Autowired
	private ISysLogService logService;
	/**
	 * 指定处理日志的线程池
	 */
	@Autowired
	@Qualifier("logTaskExecutor")
	private TaskExecutor executor;

	/**
	 * 方法的日志处理
	 * 
	 * @param joinPoint
	 * @param message
	 */
	public void doAfterThrowing(final JoinPoint joinPoint, final String message) {
		final Class<?> cls = joinPoint.getTarget().getClass();// 得到当前执行方法的类
		final Object[] args = joinPoint.getArgs();
		final User user = ClientUtil.getLoginUser();
		final String ip = ClientUtil.getClientIp();
		// 通过新起一个线程来操作日志记录
//		executor.execute(new Runnable() {
//			@Override
//			public void run() {
				try {
					// 判断当前类是否允许记录日志
					LogRequiredType clsLogRequiredType = ReflectUtil
							.getLogRequiredType(cls);
					if (clsLogRequiredType == LogRequiredType.IGNORE) {// 如果不需要记录日志
						return;
					}

					// 获取类的日志说明
					String clsStr = ReflectUtil.getLogDescription(cls);

					// 获取访问方法名
					String methodName = joinPoint.getSignature().getName();

					// 获取方法参数类型
					Class<?>[] types = ((CodeSignature) joinPoint
							.getStaticPart().getSignature())
							.getParameterTypes();
					
					// 得到当前访问的方法
					Method method = cls.getMethod(methodName, types);

					// 获取当前方法日志标记
					LogRequiredType methodLogRequiredType = ReflectUtil
							.getLogRequiredType(method);

					if (methodLogRequiredType == LogRequiredType.IGNORE) {
						return;
					}

					// 得到当前方法的日志记录信息
					String variableMethodDesc = ReflectUtil
							.getLogDescription(method);

//					String methodDesc = initParamValues(cls, methodName, variableMethodDesc, args, types);
					
					// 插入日志记录
					logService.insertLog(user, ip, clsStr,
							variableMethodDesc, message);
				} catch (Exception e) {
					e.printStackTrace();
				}

//			}
//		});
		
	}
	
	/** 
	 * 初始化注解方法中的参数
	 * @param clazz 目标类
	 * @param methodName 方法名
	 * @param variableMethodDesc 参数化的方法描述
	 * @param args 实际传递过来的参数值
	 * @param types 实际参数的类型
	 */
	private String initParamValues(Class clazz, String methodName, String variableMethodDesc, Object[] args, Class<?>[] types) throws Exception {
		Set<String> params = getMethodParams(variableMethodDesc);
		//没有变量出现
		if(params.isEmpty()) {
			return variableMethodDesc;
		}
		
		//出现变量，需要为变量赋值
		//获取方法形参名
		List<String> paramNames = ReflectUtil.getMethodParamNames(clazz, methodName);
		
		for(String param : params) {
			
			Object value = null;
			for(int i = 0; i < paramNames.size(); i++) {
				String paramName = paramNames.get(i);
				if(param.indexOf(".") == -1) {//普通类型参数，直接赋值
					if(param.equals(paramName)) {
						value = args[i];
						break;
					}
				}else {//user.username，其中包含对象
					if(param.contains(paramName)) {
						String[] paramArr = param.substring(param.indexOf(".") + 1).split(".");
						value = args[i];
						for(int j = 0; j < paramArr.length; j++) {
							String propertyName = paramArr[j];
							Object propertyValue = ReflectUtil.getProperty(value, propertyName);
							if(j+1 < paramArr.length) {
								value = propertyValue;//获取下一级属性值
							}
						}
						break;
					}
				}
			}
			
			variableMethodDesc.replaceAll(SysConst.VARIABLE_START_TAG + param + SysConst.VARIABLE_END_TAG, value.toString());
		}
		
		return variableMethodDesc;
	}
	
	/**
	 * 获取当前注解描述符中的参数
	 * @param variableMethodDesc 执行方法的描述，其中可能包含变量，需要初始化
	 * @return
	 */
	private Set<String> getMethodParams(String variableMethodDesc) throws Exception {
		Set<String> params = new HashSet<>();
		
		char[] chars = variableMethodDesc.toCharArray();
		StringBuilder builder = new StringBuilder();
		boolean paramStart = false;//是否为变量名开始
		
		for(int i = 0 ; i < chars.length; i++) {
			if(chars[i] == SysConst.VARIABLE_START_TAG && paramStart) {
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
				params.add(builder.toString());
				builder.delete(0, builder.length());//清空变量构造器，搜索下一个变量
			}
			
			if(paramStart) {
				builder.append(chars[i]);
			}
		}
		
		return params;
	}
}
