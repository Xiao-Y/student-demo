package org.billow.jobs;

import java.util.ArrayList;
import java.util.List;

import org.billow.jobs.manager.QuartzManager;
import org.billow.model.expand.ScheduleJobDto;
import org.billow.utils.bean.BeanUtils;
import org.junit.Before;
import org.quartz.Scheduler;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

public class JobsTest {

	private ClassPathXmlApplicationContext ctx = null;
	private SchedulerFactoryBean schedulerFactoryBean;

	@Before
	public void init() {

		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		try {
			schedulerFactoryBean = BeanUtils.getBean("schedulerFactoryBean");
			Scheduler scheduler = schedulerFactoryBean.getScheduler();
			// 这里从数据库中获取任务信息数据
			List<ScheduleJobDto> jobList = new ArrayList<>();// scheduleJobMapper.getAll();
			QuartzManager qm = new QuartzManager();
			for (ScheduleJobDto job : jobList) {
				qm.addJob(job);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
