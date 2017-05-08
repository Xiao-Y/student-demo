package org.billow.jobs.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.billow.model.expand.ScheduleJobDto;
import org.billow.utils.ToolsUtils;
import org.billow.utils.bean.BeanUtils;

/**
 * 自动任务工具类
 * 
 * @author liuyongtao
 * 
 * @date 2017年5月8日 上午10:24:57
 */
public class TaskUtils {

	public final static Logger log = Logger.getLogger(TaskUtils.class);

	/**
	 * 通过反射调用scheduleJob中定义的方法
	 * 
	 * @param scheduleJob
	 */
	public static void invokMethod(ScheduleJobDto scheduleJob) {
		Object object = null;
		Class<?> clazz = null;
		Method method = null;
		// springId不为空先按springId查找bean
		if (ToolsUtils.isNotEmpty(scheduleJob.getSpringId())) {
			try {
				object = BeanUtils.getBean(scheduleJob.getSpringId());
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (ToolsUtils.isNotEmpty(scheduleJob.getBeanClass())) {
			try {
				clazz = Class.forName(scheduleJob.getBeanClass());
				object = clazz.newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (object == null) {
			log.error("任务名称 = [" + scheduleJob.getJobName() + "] 未启动成功，请检查是否配置正确！！！");
			return;
		}

		clazz = object.getClass();
		try {
			// 获取自动任务要执行的方法
			method = clazz.getDeclaredMethod(scheduleJob.getMethodName());
		} catch (NoSuchMethodException e) {
			log.error("任务名称 = [" + scheduleJob.getJobName() + "] 未启动成功，方法名设置错误！！！");
		} catch (SecurityException e) {
			e.printStackTrace();
		}

		if (method != null) {
			try {
				method.invoke(object);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		} else {
			log.error("任务名称 = [" + scheduleJob.getJobName() + "] 方法名设置错误！！！");
		}
	}
}
