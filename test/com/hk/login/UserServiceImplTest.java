package com.hk.login;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hk.auth.domain.User;
import com.hk.auth.query.UserQuery;
import com.hk.auth.service.IUserService;
import com.hk.common.BaseServiceImplTest;
import com.hk.common.pager.Pager;

public class UserServiceImplTest extends BaseServiceImplTest {

	@Autowired
	private IUserService userService;

	@Test
	public void testGetCount() throws Exception {
		
		UserQuery query = new UserQuery();
		query.setNickname("小");
		query.setUsername("zhangs");
		
		int count = userService.getCount(query);
		System.out.println(count);
	}
	
	@Test
	public void testFindAll() throws Exception {
		UserQuery query = new UserQuery();
		query.setNickname("小");
		query.setUsername("zhangs");
		
		Pager<User> pager = userService.findAll(query);
		
		System.out.println(pager);
	}
}
