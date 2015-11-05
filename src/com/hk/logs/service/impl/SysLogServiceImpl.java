package com.hk.logs.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hk.auth.domain.User;
import com.hk.common.mapper.BaseMapper;
import com.hk.common.service.impl.BaseServiceImpl;
import com.hk.logs.domain.SysLog;
import com.hk.logs.mapper.SysLogMapper;
import com.hk.logs.service.ISysLogService;

@Transactional
@Service
public class SysLogServiceImpl extends BaseServiceImpl<SysLog> implements
		ISysLogService {

	@Autowired
	private SysLogMapper logMapper;

	@Override
	protected BaseMapper<SysLog> getBaseMapper() {
		return logMapper;
	}

	@Override
	public void insertLog(User user, String ip, String modelName,
			String methodName, String logInfo) {
		
		SysLog log = new SysLog();
		
		log.setLogInfo(logInfo);
		log.setModelName(modelName);
		log.setMethodName(methodName);
		log.setIp(ip);
		log.setLogTime(new Date());
		
		if(user != null) {
			log.setUserId(user.getId());
			log.setUsername(user.getUsername());
		}
		
		logMapper.save(log);
	}

	@Override
	public void updateUserIdsNull(String userIds) {
		logMapper.setUsersNullByUserIds(userIds);
	}

}
