package org.billow.jobs.quartzJobFactory;

import org.apache.log4j.Logger;
import org.billow.jobs.util.TaskUtils;
import org.billow.model.expand.ScheduleJobDto;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 计划任务执行处 无状态(相当于多线程的)
 * 
 * @author XiaoY
 * @date: 2017年5月6日 下午10:45:29
 */
public class QuartzJobFactory implements Job {

	public final Logger log = Logger.getLogger(this.getClass());

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		ScheduleJobDto scheduleJob = (ScheduleJobDto) context.getMergedJobDataMap().get("scheduleJob");
		TaskUtils.invokMethod(scheduleJob);
	}
}
