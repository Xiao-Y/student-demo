package org.billow.utils.redis;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.billow.utils.ToolsUtils;
import org.springframework.cache.interceptor.KeyGenerator;

public class RedisKeyGenerator implements KeyGenerator {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(RedisKeyGenerator.class);
	private static List<String> cacheMethodList = new ArrayList<>();
	static{
		cacheMethodList.add("insert");
		cacheMethodList.add("insertSelective");
		cacheMethodList.add("selectByPrimaryKey");
		cacheMethodList.add("deleteByPrimaryKey");
		cacheMethodList.add("updateByPrimaryKey");
		cacheMethodList.add("updateByPrimaryKeySelective");
	}

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
