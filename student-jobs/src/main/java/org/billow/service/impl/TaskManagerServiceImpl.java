package org.billow.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	public List<String> saveAutoTask(ScheduleJobDto scheduleJobDto) throws Exception {
		List<String> list = new ArrayList<>();
		String isConcurrent = scheduleJobDto.getIsConcurrent();
		Integer jobId = scheduleJobDto.getJobId();
		String jobStatus = scheduleJobDto.getJobStatus();
		// 启用状态时检验合法性
		if (JOB_STATUS_RESUME.equals(jobStatus)) {
			boolean exceptionFlag = this.checkAutoTask(scheduleJobDto, list);
			if (exceptionFlag) {
				return list;
			}
		}
		if (ToolsUtils.isEmpty(isConcurrent)) {
			scheduleJobDto.setIsConcurrent(IS_CONCURRENT_NO);
		}
		if (ToolsUtils.isEmpty(jobStatus)) {
			jobStatus = JOB_STATUS_PAUSE;
			scheduleJobDto.setJobStatus(JOB_STATUS_PAUSE);
		}
		if (null == jobId) {// 表示添加
			scheduleJobDto.setUpdateTime(new Date());
			scheduleJobDto.setCreateTime(new Date());
			scheduleJobService.insert(scheduleJobDto);
		} else {// 表示更新
			ScheduleJobDto jobDto = scheduleJobService.selectByPrimaryKey(scheduleJobDto.getJobId());
			scheduleJobDto.setCreateTime(jobDto.getCreateTime());
			scheduleJobService.updateByPrimaryKey(scheduleJobDto);
		}
		// 启用状态时添加运行定时任务
		if (JOB_STATUS_RESUME.equals(jobStatus)) {
			quartzManager.addJob(scheduleJobDto);
		}
		return list;
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
	private boolean checkAutoTask(ScheduleJobDto scheduleJobDto, List<String> list) {
		String jobStatus = scheduleJobDto.getJobStatus();
		String cronExpression = scheduleJobDto.getCronExpression();
		String springId = scheduleJobDto.getSpringId();
		String beanClass = scheduleJobDto.getBeanClass();
		String methodName = scheduleJobDto.getMethodName();
		// 异常标识
		boolean exceptionFlag = false;
		try {
			CronScheduleBuilder.cronSchedule(cronExpression);
		} catch (Exception e) {
			exceptionFlag = true;
			list.add("cron表达式错误，请查证！");
		}

		// bean是否为空的标识
		boolean beanEmptyFlag = false;
		if (ToolsUtils.isEmpty(springId) && ToolsUtils.isEmpty(beanClass)) {
			list.add("springId或beanClass不能同时为空！");
			exceptionFlag = true;
			beanEmptyFlag = true;
		}
		// springId和beanClass不同时为空并且为执行状态的时候才检查
		if (!beanEmptyFlag && JOB_STATUS_RESUME.equals(jobStatus)) {
			// bean能否获取标识
			boolean beanFlag = true;
			Class<?> clazz = null;
			// bean相关检查
			if (ToolsUtils.isNotEmpty(springId)) {
				try {
					BeanUtils.getBean(springId);
				} catch (Exception e) {
					list.add("springId错误，未获取相关Bean！");
					exceptionFlag = true;
					beanFlag = false;
				}
			} else {
				try {
					clazz = Class.forName(beanClass);
					clazz.newInstance();
				} catch (Exception e) {
					list.add("beanClass错误，未获取相关类！");
					exceptionFlag = true;
					beanFlag = false;
				}
			}
			// 对执行方法检查（bean可以获取）
			if (beanFlag) {
				if (ToolsUtils.isNotEmpty(methodName)) {
					try {
						clazz.getDeclaredMethod(methodName);
					} catch (NoSuchMethodException | SecurityException e) {
						list.add("方法：" + methodName + "，未获取！");
						exceptionFlag = true;
					}
				} else {
					list.add("执行方法不能为空！");
					exceptionFlag = true;
				}
			}
		}
		return exceptionFlag;
	}
}
