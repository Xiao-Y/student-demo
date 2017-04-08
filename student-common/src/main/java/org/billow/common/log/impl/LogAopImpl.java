package org.billow.common.log.impl;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.billow.api.system.SystemLogService;
import org.billow.common.annotation.SystemControllerLog;
import org.billow.common.log.LogAop;
import org.billow.model.domain.SystemLog;
import org.billow.utils.LoginHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

@Component
public class LogAopImpl implements LogAop {

	@Autowired
	private SystemLogService systemLogService;

	@Override
	public void logArgSave(JoinPoint joinPoint) {
		SystemLog log = new SystemLog();
		this.saveLog(joinPoint, log);
	}

	/**
	 * 保存日志
	 * 
	 * @param joinPoint
	 * @param log
	 * @throws Exception
	 */
	private void saveLog(JoinPoint joinPoint, SystemLog log) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			map = this.getControllerMethodDescription(joinPoint);
			// map为空说明当前请求方法上没有日志注解
			if (map == null || map.isEmpty()) {
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 获取类全名
		String className = joinPoint.getTarget().getClass().getName();
		// 获取方法名
		String methodName = joinPoint.getSignature().getName();
		// 获取操作内容
		String opContent = this.optionContent(joinPoint);

		System.out.println(map.get("module") + "--->" + map.get("function") + "--->" + map.get("operation"));

		log.setContent(opContent);
		log.setRunClass(className + "." + methodName);
		log.setModule(map.get("module"));
		log.setFunction(map.get("function"));
		log.setOperation(map.get("operation"));
		log.setNote(map.get("note"));
		log.setCreateTime(new Date());
		log.setUserId(LoginHelper.getLoginUserId());

		systemLogService.persistLog(log);
	}

	/**
	 * 使用Java反射来获取被拦截方法(insert、update)的参数值， 将参数值拼接为操作内容
	 */
	private String optionContent(JoinPoint joinPoint) {
		// 此方法返回的是一个数组，数组中包括request以及ActionCofig等类对象
		Object[] args = joinPoint.getArgs();
		// 判断参数
		if (args == null) {// 没有参数
			return null;
		}
		StringBuffer rs = new StringBuffer();
		String className = null;
		// 遍历参数对象
		int i = 1;
		for (int index = 0; index < args.length; index++) {
			Object info = args[index];
			// 获取对象类型
			className = info.getClass().getName();
			className = className.substring(className.lastIndexOf(".") + 1);
			if ("Request".equals(className) || "Response".equals(className) || "RequestFacade".equals(className)
					|| "BindingAwareModelMap".equals(className)) {
				continue;
			}
			rs.append("[参数" + i + ",类型:" + className + ",值:");
			rs.append(JSON.toJSONString(info));
			rs.append("]");
			i++;
		}
		return rs.toString();
	}

	/**
	 * 获取注解中对方法的描述信息 用于Controller层注解
	 * 
	 * @param joinPoint
	 *            切点
	 * @return 方法描述
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	private Map<String, String> getControllerMethodDescription(JoinPoint joinPoint) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		// 获取请求访求名
		String methodName = joinPoint.getSignature().getName();
		// 获取请求的处理类
		String targetName = joinPoint.getTarget().getClass().getName();
		// 获取请求访求的参数
		Object[] arguments = joinPoint.getArgs();
		Class targetClass = Class.forName(targetName);
		// 获取处理类的所有方法
		Method[] methods = targetClass.getMethods();
		for (Method method : methods) {
			// 判断请求方法与方法数组中的方法是否相同
			if (method.getName().equals(methodName)) {
				// 获取方法数组中方法的参数
				Class[] clazzs = method.getParameterTypes();
				if (clazzs.length == arguments.length) {
					// 判断方法上是否存在自定义注解
					if (method.isAnnotationPresent(SystemControllerLog.class)) {
						SystemControllerLog systemControllerLog = method.getAnnotation(SystemControllerLog.class);
						map.put("module", systemControllerLog.module());
						map.put("function", systemControllerLog.function());
						map.put("operation", systemControllerLog.operation());
						map.put("note", systemControllerLog.note());
						return map;
					}
				}
			}
		}
		return map;
	}
}
