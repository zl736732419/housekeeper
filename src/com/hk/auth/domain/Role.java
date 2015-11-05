package com.hk.auth.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * 用户角色表
 *
 * @author zhenglian
 * @data 2015年10月18日 下午3:58:21
 */
public class Role {
	/**
	 * 角色编号
	 */
	private Integer id;

	/**
	 * 角色名称
	 */
	private String name;

	/**
	 * 角色所拥有的用户
	 */
	Set<User> users = new HashSet<User>();

	Set<Resource> resources = new HashSet<Resource>();

	public Role() {
	}

	public Role(Integer id, String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Set<Resource> getResources() {
		return resources;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setResources(Set<Resource> resources) {
		this.resources = resources;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + "]";
	}

	
}
