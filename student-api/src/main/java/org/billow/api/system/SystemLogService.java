package org.billow.api.system;

import org.billow.api.base.BaseService;
import org.billow.model.expand.SystemLogDto;

public interface SystemLogService extends BaseService<SystemLogDto> {

	void persistLog(SystemLogDto log);
}
