package org.billow.service.system;

import java.util.List;

import javax.annotation.Resource;

import org.billow.api.system.ScheduleJobService;
import org.billow.dao.ScheduleJobDao;
import org.billow.dao.base.BaseDao;
import org.billow.model.expand.ScheduleJobDto;
import org.billow.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ScheduleJobServiceImpl extends BaseServiceImpl<ScheduleJobDto> implements ScheduleJobService {

	@Resource
	private ScheduleJobDao scheduleJobDao;

	@Resource
	@Override
	public void setBaseDao(BaseDao<ScheduleJobDto> baseDao) {
		super.baseDao = this.scheduleJobDao;
	}

	@Override
	public List<ScheduleJobDto> selectAll(ScheduleJobDto scheduleJobDto) {
		return scheduleJobDao.selectAll(scheduleJobDto);
	}

	@Override
	public void updateJobStatus(ScheduleJobDto dto) {
		scheduleJobDao.updateByPrimaryKeySelective(dto);
	}
}
