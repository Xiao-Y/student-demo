package org.billow.service.dubbo.provider.impl;

import java.util.Date;

import org.billow.service.dubbo.provider.service.DemoServer;

public class DemoServerImpl implements DemoServer {

	@Override
	public String sayHello(String str) {
		str = "Hello " + str + "2:" + new Date();
		System.out.println("server:" + str);
		return str;
	}
}
