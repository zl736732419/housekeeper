package com.hk.auth.query;

import org.apache.commons.lang3.StringUtils;

import com.hk.common.query.ObjectQuery;

/**
 * 用户查询对象
 *
 * @author zhenglian
 * @data 2015年10月19日 下午10:17:42
 */
public class UserQuery extends ObjectQuery {
	private String username;// 用户名
	private String nickname;// 昵称
	private String sex;// 性别
	private Integer age;// 年龄
	private String tel;// 电话

	@Override
	protected void addWhere() {
		if (!StringUtils.isBlank(username)) {
			addWhere("username", username);
		}

		if (!StringUtils.isBlank(nickname)) {
			addWhere("nickname", nickname);
		}

		if (!StringUtils.isBlank(sex)) {
			addWhere("sex", sex);
		}

		if (age != null) {
			addWhere("age", age);
		}

		if (!StringUtils.isBlank(tel)) {
			addWhere("tel", tel);
		}
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

}
