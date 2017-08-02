package org.billow.service.system;

import javax.annotation.Resource;

import org.billow.api.system.SystemLogService;
import org.billow.dao.SystemLogDao;
import org.billow.dao.base.BaseDao;
import org.billow.model.expand.SystemLogDto;
import org.billow.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class SystemLogServiceImpl extends BaseServiceImpl<SystemLogDto> implements SystemLogService {

	@Resource
	private SystemLogDao systemLogDao;
	
	@Resource
	@Override
	public void setBaseDao(BaseDao<SystemLogDto> baseDao) {
		super.baseDao = this.systemLogDao;
	}

	@Override
	public void persistLog(SystemLogDto log) {
		systemLogDao.insert(log);
	}
}
