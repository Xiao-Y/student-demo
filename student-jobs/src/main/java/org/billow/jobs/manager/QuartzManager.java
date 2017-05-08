package org.billow.jobs.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.billow.common.constant.QuartzCst;
import org.billow.jobs.quartzJobFactory.QuartzJobFactory;
import org.billow.jobs.quartzJobFactory.QuartzJobFactoryDisallowConcurrentExecution;
import org.billow.model.expand.ScheduleJobDto;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

/**
 * 定时任务管理器
 * 
 * @author XiaoY
 * @date: 2017年5月6日 下午10:49:14
 */
@Component
public class QuartzManager {

	public final static Logger log = Logger.getLogger(QuartzManager.class);

	@Autowired
	private SchedulerFactoryBean schedulerFactoryBean;

	/**
	 * 获取所有计划中的任务列表
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @return
	 * @throws SchedulerException
	 * 
	 * @date 2017年5月7日 下午5:17:27
	 */
	public List<ScheduleJobDto> getAllJob() throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		GroupMatcher<JobKey> matcher = GroupMatcher.anyJobGroup();
		Set<JobKey> jobKeys = scheduler.getJobKeys(matcher);
		List<ScheduleJobDto> jobList = new ArrayList<ScheduleJobDto>();
		for (JobKey jobKey : jobKeys) {
			List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
			for (Trigger trigger : triggers) {
				ScheduleJobDto job = new ScheduleJobDto();
				job.setJobName(jobKey.getName());
				job.setJobGroup(jobKey.getGroup());
				job.setDescription("触发器:" + trigger.getKey());
				Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
				job.setJobStatus(triggerState.name());
				if (trigger instanceof CronTrigger) {
					CronTrigger cronTrigger = (CronTrigger) trigger;
					String cronExpression = cronTrigger.getCronExpression();
					job.setCronExpression(cronExpression);
				}
				jobList.add(job);
			}
		}
		return jobList;
	}

	/**
	 * 所有正在运行的job
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @return
	 * @throws SchedulerException
	 * 
	 * @date 2017年5月7日 下午5:17:48
	 */
	public List<ScheduleJobDto> getRunningJob() throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
		List<ScheduleJobDto> jobList = new ArrayList<ScheduleJobDto>(executingJobs.size());
		for (JobExecutionContext executingJob : executingJobs) {
			ScheduleJobDto job = new ScheduleJobDto();
			JobDetail jobDetail = executingJob.getJobDetail();
			JobKey jobKey = jobDetail.getKey();
			Trigger trigger = executingJob.getTrigger();
			job.setJobName(jobKey.getName());
			job.setJobGroup(jobKey.getGroup());
			job.setDescription("触发器:" + trigger.getKey());
			Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
			job.setJobStatus(triggerState.name());
			if (trigger instanceof CronTrigger) {
				CronTrigger cronTrigger = (CronTrigger) trigger;
				String cronExpression = cronTrigger.getCronExpression();
				job.setCronExpression(cronExpression);
			}
			jobList.add(job);
		}
		return jobList;
	}

	/**
	 * 暂停一个job
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @param scheduleJob
	 * @throws SchedulerException
	 * 
	 * @date 2017年5月7日 下午5:17:56
	 */
	public void pauseJob(ScheduleJobDto scheduleJob) throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
		scheduler.pauseJob(jobKey);
	}

	/**
	 * 恢复一个job
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @param scheduleJob
	 * @throws SchedulerException
	 * 
	 * @date 2017年5月7日 下午5:18:05
	 */
	public void resumeJob(ScheduleJobDto scheduleJob) throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
		scheduler.resumeJob(jobKey);
	}

	/**
	 * 删除一个job
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @param scheduleJob
	 * @throws SchedulerException
	 * 
	 * @date 2017年5月7日 下午5:18:12
	 */
	public void deleteJob(ScheduleJobDto scheduleJob) throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
		scheduler.deleteJob(jobKey);

	}

	/**
	 * 立即执行job
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @param scheduleJob
	 * @throws SchedulerException
	 * 
	 * @date 2017年5月7日 下午5:18:21
	 */
	public void runAJobNow(ScheduleJobDto scheduleJob) throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobKey jobKey = JobKey.jobKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
		scheduler.triggerJob(jobKey);
	}

	/**
	 * 更新job时间表达式
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @param scheduleJob
	 * @throws SchedulerException
	 * 
	 * @date 2017年5月7日 下午5:18:28
	 */
	public void updateJobCron(ScheduleJobDto scheduleJob) throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		TriggerKey triggerKey = TriggerKey.triggerKey(scheduleJob.getJobName(), scheduleJob.getJobGroup());
		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
		CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression());
		trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
		scheduler.rescheduleJob(triggerKey, trigger);
	}

	/**
	 * 添加任务，任务状态要为ScheduleJob.STATUS_RUNNING
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @param job
	 * @throws SchedulerException
	 * 
	 * @date 2017年5月7日 下午5:18:37
	 */
	public void addJob(ScheduleJobDto job) throws SchedulerException {
		// 0禁用 1启用 2删除
		if (job == null || !QuartzCst.STATUS_RUNNING.equals(job.getJobStatus())) {
			return;
		}
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());
		CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
		// 不存在，创建一个
		if (null == trigger) {
			Class<? extends Job> clazz = null;
			if (QuartzCst.CONCURRENT_NOT.equals(job.getIsConcurrent())) {
				clazz = QuartzJobFactory.class;
			} else {
				clazz = QuartzJobFactoryDisallowConcurrentExecution.class;
			}
			// 指定Job在Scheduler中所属组及名称
			JobDetail jobDetail = JobBuilder.newJob(clazz).withIdentity(job.getJobName(), job.getJobGroup()).build();
			jobDetail.getJobDataMap().put("scheduleJob", job);
			// 设置调度的时间规则
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
			// 创建一个SimpleTrigger实例，指定该Trigger在Scheduler中所属组及名称
			trigger = TriggerBuilder.newTrigger().withIdentity(job.getJobName(), job.getJobGroup()).withSchedule(scheduleBuilder).build();
			// 注册并进行调度
			scheduler.scheduleJob(jobDetail, trigger);
		} else {
			// Trigger已存在，那么更新相应的定时设置
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
			// 按新的cronExpression表达式重新构建trigger
			trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
			// 按新的trigger重新设置job执行
			scheduler.rescheduleJob(triggerKey, trigger);
		}
	}
}
