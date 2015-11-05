package com.hk.core.logs;

import org.aspectj.lang.JoinPoint;

/**
 * 记录执行出现的异常日志
 *
 * @author zhenglian
 * @data 2015年10月18日 下午3:23:44
 */
public class LogsExceptionHandler extends AbstractLogHandler {

	public void addLog(final JoinPoint joinPoint) {
		super.doAfterThrowing(joinPoint, "发生异常操作!");
	}
	
}
