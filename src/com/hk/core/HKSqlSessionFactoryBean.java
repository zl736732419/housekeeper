package com.hk.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.ClassUtils;

/**
 * 重写sqlSessionFactoryBean,使其支持对别名通配符的配置方式
 * 通过配置的通配符包字符串，到项目WEB-INF/lib/classes下编译好的类二进制文件中去找
 *
 * @author zhenglian
 * @time 2015年10月16日 上午9:19:08
 */
public class HKSqlSessionFactoryBean extends SqlSessionFactoryBean {
	@Override
	public void setTypeAliasesPackage(String typeAliasesPackage) {
		//可能会配置多个含通配符的包
		String[] packageStrs = org.springframework.util.StringUtils.tokenizeToStringArray(typeAliasesPackage, ConfigurableApplicationContext.CONFIG_LOCATION_DELIMITERS);
		
		List<String> packages = new ArrayList<String>();
		for(String basePackage : packageStrs) {
			String packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + resolveBasePackage(basePackage) + "/**/domain";
			try {
				Resource[] resources = new PathMatchingResourcePatternResolver().getResources(packageSearchPath);
				for(Resource resource : resources) {
					String path = StringUtils.substringAfter(resource.getFile().getPath(), "classes\\").replace("\\", ".");
					packages.add(path);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		super.setTypeAliasesPackage(StringUtils.join(packages, ","));
	}

	private String resolveBasePackage(String basePackage) {
		return ClassUtils.convertClassNameToResourcePath(new StandardEnvironment().resolveRequiredPlaceholders(basePackage));
	}
}
