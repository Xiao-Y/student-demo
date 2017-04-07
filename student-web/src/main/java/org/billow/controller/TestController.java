package org.billow.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.billow.api.user.UserService;
import org.billow.common.annotation.SystemControllerLog;
import org.billow.common.dubbo.provider.DemoServer;
import org.billow.model.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.github.pagehelper.PageHelper;

@Controller
@RequestMapping("/test")
public class TestController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TestController.class);

	@Autowired
	private UserService userService;

	@Resource(name = "demoServiceCon")
	private DemoServer demoServer;

	@RequestMapping("/index")
	public String test(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = new User();
		user.setAge(23);
		user.setPassword("111");
		user.setPhoneNumber("1231313");
		user.setUserName("Xiao-Y");
		user.setUserId(1);
		session.setAttribute("temp", user);
		logger.error("8888888888");
		return "index";
	}

	@SystemControllerLog(function = "测试查询列表", module = "测试", operation = "查询", note = "非异步")
	@RequestMapping("/testList")
	public List<User> testList(Model model, User user, HttpServletRequest request) {
		// Integer pageSize = RequestUtils.getPageSize(request);
		// Integer targetPage = RequestUtils.getTargetPage(request);

		PageHelper.startPage(2, 10);
		List<User> users = userService.findUserList(user);
		// int count = userService.findUserCount(user);
		return users;
	}

	@RequestMapping("/testJqueryUi")
	public String testJqueryUi(Model model, User user, HttpServletRequest request) {
		return "testJqueryUi";
	}

	@RequestMapping("/testSession")
	public String testSession() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		return session.getId();
	}

	@RequestMapping("/indexSender")
	public String indexSender(HttpServletRequest request) {
		return "indexSender";
	}

	@ResponseBody
	@RequestMapping("/testDubbo")
	public void testDubbo() {
		String sayHello = demoServer.sayHello("TTTT");
		System.out.println(sayHello);
	}
}
