package org.billow.properties;

import org.billow.model.UserBase;
import org.junit.Before;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	private ClassPathXmlApplicationContext ctx = null;

	private UserBase userBase;

	@Before
	public void init() {
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		userBase = (UserBase) ctx.getBean("userBase");
	}

	@org.junit.Test
	public void test() {
		System.out.println(userBase.getUserName());
		System.out.println(userBase.getAge());
		System.out.println(userBase.getPassword());
	}
}
