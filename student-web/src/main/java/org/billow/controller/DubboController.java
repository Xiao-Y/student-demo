package org.billow.controller;

import org.apache.log4j.Logger;
import org.billow.utils.bean.BeanUtils;
import org.billow.utils.exception.DubboException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.student.dubbo.provider.api.DemoServer;

@Controller
@RequestMapping("/dubboController")
public class DubboController {
	
	private static final Logger logger = Logger.getLogger(DubboController.class);

//	@Resource(name = "demoServiceCon")
	private DemoServer demoServer;
	
	@ResponseBody
	@RequestMapping("/testDubbo")
	public void testDubbo() {
		try {
			demoServer = BeanUtils.getBean("demoServiceCon");
		} catch (Exception e) {
			logger.error(e);
			throw new DubboException();
		}
		
		String sayHello = demoServer.sayHello("TTTT");
		System.out.println(sayHello);
	}

}
