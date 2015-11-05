package com.hk.auth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hk.auth.domain.Role;
import com.hk.auth.mapper.RoleMapper;
import com.hk.auth.service.IRoleService;
import com.hk.common.mapper.BaseMapper;
import com.hk.common.service.impl.BaseServiceImpl;

@Transactional
@Service
public class RoleServiceImpl extends BaseServiceImpl<Role> implements
		IRoleService {

	@Autowired
	private RoleMapper roleMapper;

	@Override
	protected BaseMapper<Role> getBaseMapper() {
		return roleMapper;
	}

}
