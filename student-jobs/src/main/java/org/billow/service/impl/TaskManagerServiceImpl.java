package org.billow.service.impl;

import org.billow.api.system.ScheduleJobService;
import org.billow.jobs.manager.QuartzManager;
import org.billow.model.expand.ScheduleJobDto;
import org.billow.service.TaskManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskManagerServiceImpl implements TaskManagerService {

	/**
	 * 启用自动任务
	 */
	private static final String JOB_STATUS_RESUME = "0";
	/**
	 * 禁用自动任务
	 */
	private static final String JOB_STATUS_PAUSE = "1";

	@Autowired
	private QuartzManager quartzManager;

	@Autowired
	private ScheduleJobService scheduleJobService;

	@Override
	public void updateJobStatus(ScheduleJobDto dto) throws Exception {
		ScheduleJobDto scheduleJobDto = scheduleJobService.selectByPrimaryKey(dto.getJobId());
		if (JOB_STATUS_RESUME.equals(dto.getJobStatus())) {
			quartzManager.resumeJob(scheduleJobDto);
		} else if (JOB_STATUS_PAUSE.equals(dto.getJobStatus())) {
			quartzManager.pauseJob(scheduleJobDto);
		}
		scheduleJobService.updateByPrimaryKeySelective(dto);
	}

	@Override
	public void deleteAutoTask(int jobId) throws Exception {
		ScheduleJobDto scheduleJobDto = scheduleJobService.selectByPrimaryKey(jobId);
		quartzManager.deleteJob(scheduleJobDto);
		scheduleJobService.deleteByPrimaryKey(jobId);
	}
}
