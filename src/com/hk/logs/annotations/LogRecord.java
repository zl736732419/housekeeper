package com.hk.logs.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 日志标记类，指定业务类是否需要被日志记录
 *
 * @author zhenglian
 * @data 2015年10月18日 上午11:11:03
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = { ElementType.TYPE, ElementType.METHOD })
public @interface LogRecord {

	LogRequiredType record() default LogRequiredType.REQUIRED;

}
