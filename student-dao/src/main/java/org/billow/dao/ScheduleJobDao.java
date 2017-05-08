package org.billow.dao;

import java.util.List;

import org.billow.dao.base.BaseDao;
import org.billow.model.expand.ScheduleJobDto;

public interface ScheduleJobDao extends BaseDao<ScheduleJobDto> {

	List<ScheduleJobDto> selectAll(ScheduleJobDto scheduleJobDto);
}