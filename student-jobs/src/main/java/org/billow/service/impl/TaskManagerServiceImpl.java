package org.billow.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.billow.api.system.ScheduleJobService;
import org.billow.jobs.manager.QuartzManager;
import org.billow.model.expand.ScheduleJobDto;
import org.billow.service.TaskManagerService;
import org.billow.utils.ToolsUtils;
import org.billow.utils.bean.BeanUtils;
import org.quartz.CronScheduleBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskManagerServiceImpl implements TaskManagerService {

	/**
	 * 任务状态,0-禁用 1-启用 2-删除
	 */
	private static final String JOB_STATUS_RESUME = "1";
	/**
	 * 任务状态,0-禁用 1-启用 2-删除
	 */
	private static final String JOB_STATUS_PAUSE = "0";

	/**
	 * 任务是否有状态,0-无，1-有
	 */
	private static final String IS_CONCURRENT_NO = "0";
	/**
	 * 任务是否有状态,0-无，1-有
	 */
	private static final String IS_CONCURRENT_YSE_ = "1";

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

	@Override
	public Map<String, String> saveAutoTask(ScheduleJobDto scheduleJobDto) throws Exception {
		boolean exceptionFlag = false;
		Map<String, String> map = new HashMap<>();
		String isConcurrent = scheduleJobDto.getIsConcurrent();
		Integer jobId = scheduleJobDto.getJobId();
		String jobStatus = scheduleJobDto.getJobStatus();
		exceptionFlag = this.checkAutoTask(scheduleJobDto, map);

		if (ToolsUtils.isEmpty(isConcurrent)) {
			isConcurrent = IS_CONCURRENT_NO;
		}
		if (ToolsUtils.isEmpty(jobStatus)) {
			jobStatus = JOB_STATUS_PAUSE;
		}
		if (null == jobId) {// 表示添加

		} else {// 表示更新

		}
		return map;
	}

	/**
	 * 校验自动任务添加、修改时参数的设置
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @param scheduleJobDto
	 * @param map
	 *            错误信息
	 * @return
	 * 
	 * @date 2017年5月14日 下午12:11:55
	 */
	private boolean checkAutoTask(ScheduleJobDto scheduleJobDto, Map<String, String> map) {
		String jobStatus = scheduleJobDto.getJobStatus();
		String cronExpression = scheduleJobDto.getCronExpression();
		String springId = scheduleJobDto.getSpringId();
		String beanClass = scheduleJobDto.getBeanClass();
		String methodName = scheduleJobDto.getMethodName();
		boolean exceptionFlag = false;
		try {
			CronScheduleBuilder.cronSchedule(cronExpression);
		} catch (Exception e) {
			exceptionFlag = true;
			map.put("1", "cron表达式错误，请查证！");
		}
		boolean beanFlag = false;
		if (ToolsUtils.isEmpty(springId) && ToolsUtils.isEmpty(beanClass)) {
			map.put("2", "springId或beanClass不能同时为空！");
			exceptionFlag = true;
			beanFlag = true;
		}
		// springId和beanClass不同时为空并且为执行状态的时候才检查
		if (beanFlag && JOB_STATUS_RESUME == jobStatus) {
			Class<?> clazz = null;
			// bean相关检查
			if (ToolsUtils.isNotEmpty(springId)) {
				try {
					BeanUtils.getBean(springId);
				} catch (Exception e) {
					map.put("2", "springId错误，未获取相关Bean！");
					exceptionFlag = true;
				}
			} else {
				try {
					clazz = Class.forName(beanClass);
					clazz.newInstance();
				} catch (Exception e) {
					map.put("2", "beanClass错误，未获取相关类！");
					exceptionFlag = true;
				}
			}
			// 对执行方法检查
			if (ToolsUtils.isNotEmpty(methodName)) {
				try {
					clazz.getDeclaredMethod(methodName);
				} catch (NoSuchMethodException | SecurityException e) {
					map.put("4", "方法：" + methodName + "，未获取！");
					exceptionFlag = true;
				}
			} else {
				map.put("3", "执行方法不能为空！");
				exceptionFlag = true;
			}
		}
		return exceptionFlag;
	}
}
