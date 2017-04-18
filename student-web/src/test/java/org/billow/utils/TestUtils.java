package org.billow.utils;

import org.billow.utils.bean.BeanUtils;
import org.billow.utils.exception.ActiveMQException;

public class TestUtils {

	public static void main(String[] args) throws Exception {
		try {
			BeanUtils.getBean("111");
		} catch (Exception e) {
			throw new ActiveMQException();
		}
		System.out.println(22222);
	}
}
