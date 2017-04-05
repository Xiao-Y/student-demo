package org.billow.api;

import org.billow.api.base.BaseService;
import org.billow.model.domain.SystemLog;

public interface SystemLogService extends BaseService<SystemLog> {

	void persistLog(SystemLog log);
}
