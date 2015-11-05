package com.hk.logs.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标记当前类或者方法的日志描述信息
 *
 * @author zhenglian
 * @data 2015年10月18日 上午11:13:55
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = { ElementType.TYPE, ElementType.METHOD })
public @interface LogActionDesc {

	String description() default "";

}
