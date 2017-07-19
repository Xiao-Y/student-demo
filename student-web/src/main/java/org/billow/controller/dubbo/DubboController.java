package org.billow.controller.dubbo;

import org.billow.dubbo.provider.api.DemoServer;
import org.billow.utils.constant.PagePathCst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/dubboController")
public class DubboController {

	@Autowired(required = false)
	private DemoServer demoServiceCon;

	@RequestMapping("/index")
	public String index() {
		return PagePathCst.BASEPATH_DUBBO + "index";
	}

	@ResponseBody
	@RequestMapping("/sayHello")
	public String sayHello() {
		return demoServiceCon.sayHello("bilow");
	}
}
