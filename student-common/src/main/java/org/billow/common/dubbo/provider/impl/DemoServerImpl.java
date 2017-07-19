package org.billow.common.dubbo.provider.impl;

import org.apache.log4j.Logger;

import java.util.Date;

import org.billow.dubbo.provider.api.DemoServer;
import org.billow.utils.date.DateTime;

public class DemoServerImpl implements DemoServer {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(DemoServerImpl.class);

	@Override
	public String sayHello(String str) {
		str = "Hello " + str + " " + new DateTime(new Date(), DateTime.YEAR_TO_MILLISECOND);
		logger.info("\r\nserver---> " + str);
		return str;
	}
}
