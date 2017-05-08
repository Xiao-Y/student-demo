package org.billow.api.system;

import java.util.List;

import org.billow.api.base.BaseService;
import org.billow.model.expand.ScheduleJobDto;

public interface ScheduleJobService extends BaseService<ScheduleJobDto> {

	List<ScheduleJobDto> selectAll(ScheduleJobDto scheduleJobDto);

}
