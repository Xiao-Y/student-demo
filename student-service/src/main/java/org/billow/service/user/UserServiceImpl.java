package org.billow.service.user;

import java.util.List;

import javax.annotation.Resource;

import org.billow.api.user.UserService;
import org.billow.dao.UserMapper;
import org.billow.model.domain.User;
import org.billow.service.base.BaseServiceImpl;
import org.springframework.cache.annotation.Cacheable;
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

	@Override
	@Cacheable(keyGenerator = "keyGenerator", value = "findUserList")
	public List<User> findUserList(User user) {
		return userMapper.findUserList(user);
	}

	@Override
	@Cacheable(keyGenerator = "keyGenerator", value = "findUserList")
	public int findUserCount(User user) {
		return userMapper.findUserCount(user);
	}

	@Override
	public User findUserById(int id) {
		return super.selectByPrimaryKey(id);
	}

}
