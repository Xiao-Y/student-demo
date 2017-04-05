package org.billow.api;

import com.billow.base.service.BaseService;
import com.billow.business.model.SystemLog;

public interface SystemLogService extends BaseService<SystemLog> {

	void persistLog(SystemLog log);
}
