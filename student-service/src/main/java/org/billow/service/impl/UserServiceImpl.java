package org.billow.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.billow.api.UserService;
import org.billow.dao.UserMapper;
import org.billow.model.domain.User;
import org.billow.service.impl.base.BaseServiceImpl;
import org.bilow.common.annotation.SystemControllerLog;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

	private UserMapper userMapper;

	@Resource
	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
		super.setBaseMapper(userMapper);
	}

	@Override
	public void deleteTest() {
		this.deleteByPrimaryKey(15);
		User record = new User();
		record.setUserId(52);
		record.setUserName("XXXX");
		record.setPhoneNumber("22222");
		this.insert(record);
		throw new RuntimeException();
	}

	@SystemControllerLog(function = "测试查询列表", module = "测试", operation = "查询", note = "非异步")
	@Override
	public List<User> findUserList(User user) {
		return userMapper.findUserList(user);
	}

	@Override
	public int findUserCount(User user) {
		return userMapper.findUserCount(user);
	}

}
