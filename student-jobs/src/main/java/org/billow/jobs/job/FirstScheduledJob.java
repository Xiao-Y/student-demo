package org.billow.jobs.job;

import org.billow.jobs.bean.AnotherBean;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class FirstScheduledJob extends QuartzJobBean {

	private AnotherBean anotherBean;

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		System.out.println("I am FirstScheduledJob...................");
		JobDataMap dataMap = context.getMergedJobDataMap();
		AnotherBean anotherBeanWish = (AnotherBean) dataMap.get("anotherBean");
		String wish = (String) dataMap.get("wish");
		System.out.println("wish:" + wish);
		System.out.println("anotherBeanWish:" + anotherBeanWish.getClass().getName());
		this.anotherBean.printAnotherMessage();
	}

	public void setAnotherBean(AnotherBean anotherBean) {
		this.anotherBean = anotherBean;
	}
}
