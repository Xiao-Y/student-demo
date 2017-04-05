package org.billow.service.impl;

import javax.annotation.Resource;

import org.billow.api.SystemLogService;
import org.billow.dao.SystemLogMapper;
import org.billow.model.domain.SystemLog;
import org.billow.service.impl.base.BaseServiceImpl;
import org.springframework.stereotype.Service;



@Service
public class SystemLogServiceImpl extends BaseServiceImpl<SystemLog> implements SystemLogService {

	private SystemLogMapper systemLogMapper;

	@Resource
	public void setSystemLogMapper(SystemLogMapper systemLogMapper) {
		this.systemLogMapper = systemLogMapper;
		super.setBaseMapper(systemLogMapper);
	}

	@Override
	public void persistLog(SystemLog log) {
		systemLogMapper.insert(log);
	}
}
