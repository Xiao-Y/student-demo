package org.billow.jobs.quartzJobFactory;

import org.apache.log4j.Logger;
import org.billow.jobs.util.TaskUtils;
import org.billow.model.expand.ScheduleJobDto;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 若一个方法一次执行不完下次轮转时则等待改方法执行完后才执行下一次操作(相当于单线程的)
 * 
 * @author liuyongtao
 * 
 * @date 2017年5月7日 下午5:22:03
 */
@DisallowConcurrentExecution
public class QuartzJobFactoryDisallowConcurrentExecution implements Job {

	public final Logger log = Logger.getLogger(this.getClass());

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		ScheduleJobDto scheduleJob = (ScheduleJobDto) context.getMergedJobDataMap().get("scheduleJob");
		TaskUtils.invokMethod(scheduleJob);
	}
}
