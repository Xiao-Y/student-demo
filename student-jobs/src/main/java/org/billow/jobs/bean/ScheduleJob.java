package org.billow.jobs.bean;

import java.util.Date;

/**
 * 任务对应实体类
 * 
 * @author XiaoY
 * @date: 2017年5月6日 下午10:45:17
 */
public class ScheduleJob {
	public static final String STATUS_RUNNING = "1";
	public static final String STATUS_NOT_RUNNING = "0";
	public static final String CONCURRENT_IS = "1";
	public static final String CONCURRENT_NOT = "0";
	private Long jobId;

	private Date createTime;

	private Date updateTime;
	/**
	 * 任务名称
	 */
	private String jobName;
	/**
	 * 任务分组
	 */
	private String jobGroup;
	/**
	 * 任务状态 是否启动任务
	 */
	private String jobStatus;
	/**
	 * cron表达式
	 */
	private String cronExpression;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 任务执行时调用哪个类的方法 包名+类名
	 */
	private String beanClass;
	/**
	 * 任务是否有状态
	 */
	private String isConcurrent;
	/**
	 * spring bean
	 */
	private String springId;
	/**
	 * 任务调用的方法名
	 */
	private String methodName;

	/**
	 * 任务名称
	 * 
	 * @return
	 * @author XiaoY
	 * @date: 2017年5月6日 下午10:43:41
	 */
	public String getJobName() {
		return jobName;
	}

	/**
	 * 任务名称
	 * 
	 * @param jobName
	 * @author XiaoY
	 * @date: 2017年5月6日 下午10:43:45
	 */
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	/**
	 * 任务分组
	 * 
	 * @return
	 * @author XiaoY
	 * @date: 2017年5月6日 下午10:43:54
	 */
	public String getJobGroup() {
		return jobGroup;
	}

	/**
	 * 任务分组
	 * 
	 * @param jobGroup
	 * @author XiaoY
	 * @date: 2017年5月6日 下午10:43:58
	 */
	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}

	/**
	 * 任务状态 0禁用 1启用 2删除
	 * 
	 * @return
	 * @author XiaoY
	 * @date: 2017年5月6日 下午10:44:10
	 */
	public String getJobStatus() {
		return jobStatus;
	}

	/**
	 * 任务状态 0禁用 1启用 2删除
	 * 
	 * @param jobStatus
	 * @author XiaoY
	 * @date: 2017年5月6日 下午10:44:14
	 */
	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}

	/**
	 * 任务运行时间表达式
	 * 
	 * @return
	 * @author XiaoY
	 * @date: 2017年5月6日 下午10:44:29
	 */
	public String getCronExpression() {
		return cronExpression;
	}

	/**
	 * 任务运行时间表达式
	 * 
	 * @param cronExpression
	 * @author XiaoY
	 * @date: 2017年5月6日 下午10:44:33
	 */
	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	/**
	 * 任务描述
	 * 
	 * @return
	 * @author XiaoY
	 * @date: 2017年5月6日 下午10:56:01
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 任务描述
	 * 
	 * @param description
	 * @author XiaoY
	 * @date: 2017年5月6日 下午10:56:05
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getBeanClass() {
		return beanClass;
	}

	public void setBeanClass(String beanClass) {
		this.beanClass = beanClass;
	}

	public String getIsConcurrent() {
		return isConcurrent;
	}

	public void setIsConcurrent(String isConcurrent) {
		this.isConcurrent = isConcurrent;
	}

	public String getSpringId() {
		return springId;
	}

	public void setSpringId(String springId) {
		this.springId = springId;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	/**
	 * 任务id
	 * 
	 * @param jobId
	 * @author XiaoY
	 * @date: 2017年5月6日 下午11:06:23
	 */
	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	/**
	 * 任务id
	 * 
	 * @return
	 * @author XiaoY
	 * @date: 2017年5月6日 下午11:06:47
	 */
	public Long getJobId() {
		return jobId;
	}
}
