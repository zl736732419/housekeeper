package com.hk.auth.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * 用户实体类
 *
 * @author zhenglian
 * @data 2015年10月15日 下午10:27:43
 */
public class User {
	private Integer id;            //编号
                                   
	private String username;       //用户名
    
	private String nickname;       //昵称

	private String password;       //密码
                                   
	private String sex;            //性别
                                   
	private Integer age;           //年龄,[0,120]
                                   
	private String pic;            //用户头像连接
                                   
	private String tel;            //电话

	private Set<Role> roles = new HashSet<Role>();
	
	public User() {
	}

	public User(String username, String password, String sex, Integer age,
			String pic, String nickname, String tel) {
		this.username = username;
		this.password = password;
		this.sex = sex;
		this.age = age;
		this.pic = pic;
		this.nickname = nickname;
		this.tel = tel;
	}

	public Integer getAge() {
		return age;
	}

	public Integer getId() {
		return id;
	}

	public String getNickname() {
		return nickname;
	}

	public String getPassword() {
		return password;
	}

	public String getPic() {
		return pic;
	}

	public String getSex() {
		return sex;
	}

	public String getTel() {
		return tel;
	}

	public String getUsername() {
		return username;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname == null ? null : nickname.trim();
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public void setPic(String pic) {
		this.pic = pic == null ? null : pic.trim();
	}

	public void setSex(String sex) {
		this.sex = sex == null ? null : sex.trim();
	}

	public void setTel(String tel) {
		this.tel = tel == null ? null : tel.trim();
	}

	public void setUsername(String username) {
		this.username = username == null ? null : username.trim();
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password="
				+ password + ", sex=" + sex + ", age=" + age + ", pic=" + pic
				+ ", nickname=" + nickname + ", tel=" + tel + "]";
	}

}