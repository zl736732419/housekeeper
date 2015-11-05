package com.hk.core.logs;

import org.aspectj.lang.JoinPoint;

/**
 * 记录用户操作日志
 *
 * @author zhenglian
 * @data 2015年10月18日 下午3:13:12
 */
public class LogsHandler extends AbstractLogHandler {

	public void addLog(final JoinPoint joinPoint) {
		super.doAfterThrowing(joinPoint, "日志操作成功!");
	}

}
