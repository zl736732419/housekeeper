package com.hk.auth.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * 角色管理的用户可访问的资源
 *
 * @author zhenglian
 * @data 2015年10月18日 下午4:01:06
 */
public class Resource {
	/**
	 * 资源id
	 */
	private Integer id;

	/**
	 * 资源名称
	 */
	private String name;

	/**
	 * 资源访问路径
	 */
	private String url;

	private Set<Role> roles = new HashSet<Role>();

	public Resource() {
	}

	public Resource(String name, String url) {
		this.name = name;
		this.url = url;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public String getUrl() {
		return url;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "Resource [id=" + id + ", name=" + name + ", url=" + url + "]";
	}

}
