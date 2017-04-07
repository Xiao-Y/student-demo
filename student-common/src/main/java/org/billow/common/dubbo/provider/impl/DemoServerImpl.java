package org.billow.common.dubbo.provider.impl;

import java.util.Date;

import org.billow.common.dubbo.provider.DemoServer;


public class DemoServerImpl implements DemoServer {

	@Override
	public String sayHello(String str) {
		str = "Hello " + str + "2:" + new Date();
		System.out.println("server:" + str);
		return str;
	}
}
