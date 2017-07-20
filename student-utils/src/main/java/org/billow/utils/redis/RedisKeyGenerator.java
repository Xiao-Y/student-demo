package org.billow.utils.redis;

import org.apache.log4j.Logger;

import java.lang.reflect.Method;

import org.billow.utils.ToolsUtils;
import org.springframework.cache.interceptor.KeyGenerator;

public class RedisKeyGenerator implements KeyGenerator {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(RedisKeyGenerator.class);

	/**
	 * @param o
	 *            类名
	 * @param method
	 *            方法名
	 * @param objects
	 *            方法参数
	 */
	@Override
	public Object generate(Object o, Method method, Object... objects) {
		StringBuilder sb = new StringBuilder();
		sb.append(o.getClass().getName());
		sb.append(".");
		sb.append(method.getName());
		if (ToolsUtils.isNotEmpty(objects)) {
			for (Object obj : objects) {
				sb.append(",");
				sb.append(obj.toString());
			}
		}
		logger.debug("生成的Key:" + sb);
		return sb.toString();
	}

}
