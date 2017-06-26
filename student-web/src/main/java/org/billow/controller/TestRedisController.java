package org.billow.controller;

import java.util.List;

import org.billow.api.user.UserService;
import org.billow.model.domain.UserBase;
import org.billow.model.expand.UserDto;
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
	public List<UserDto> findUserList2() {
		List<UserDto> users = userService.findUserList(null);
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
	public UserBase findUserById() {
		UserDto dto = new UserDto();
		dto.setUserId(3);
		UserBase i = userService.selectByPrimaryKey(dto);
		return i;
	}
}
