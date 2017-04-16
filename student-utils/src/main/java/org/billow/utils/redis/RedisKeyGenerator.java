package org.billow.utils.redis;

import java.lang.reflect.Method;

import org.billow.utils.ToolsUtils;
import org.springframework.cache.interceptor.KeyGenerator;

public class RedisKeyGenerator implements KeyGenerator {

	@Override
	public Object generate(Object o, Method method, Object... objects) {
		StringBuilder sb = new StringBuilder();
		sb.append(o.getClass().getName());
		sb.append(method.getName());
		if (ToolsUtils.isNotEmpty(objects)) {
			for (Object obj : objects) {
				sb.append(obj.toString());
			}
		}
		return sb.toString();
	}

}
