package org.billow.controller;

import java.util.List;

import org.billow.api.user.UserService;
import org.billow.model.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/TestRedisController")
public class TestRedisController {

	// private Logger logger = Logger.getLogger(TestRedisController.class);

	@Autowired
	private UserService userService;

	@RequestMapping("/findUserList2")
	@ResponseBody
	public List<User> findUserList2() {
		List<User> users = userService.findUserList(null);
		return users;
	}

	@ResponseBody
	@RequestMapping(value = "/findUserCount")
	public int findUserCount() {
		int i = userService.findUserCount(null);
		return i;
	}

	@ResponseBody
	@RequestMapping(value = "/findUserById")
	public User findUserById() {
		User i = userService.selectByPrimaryKey(3);
		return i;
	}
}
