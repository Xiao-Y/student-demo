package org.billow.service.user;

import java.util.List;

import javax.annotation.Resource;

import org.billow.api.user.UserService;
import org.billow.dao.UserDao;
import org.billow.model.expand.UserDto;
import org.billow.service.base.BaseServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseServiceImpl<UserDto> implements UserService {

	private UserDao userDao;

	@Resource
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
		super.setBaseDao(userDao);
	}

	@Override
	public void deleteTest() {
		this.deleteByPrimaryKey(15);
		UserDto record = new UserDto();
		record.setUserId(52);
		record.setUserName("XXXX");
		record.setPhoneNumber("22222");
		this.insert(record);
		throw new RuntimeException();
	}

	@Override
	@Cacheable(keyGenerator = "keyGenerator", value = "findUserList")
	public List<UserDto> findUserList(UserDto user) {
		return userDao.findUserList(user);
	}

	@Override
	@Cacheable(keyGenerator = "keyGenerator", value = "findUserList")
	public int findUserCount(UserDto user) {
		return userDao.findUserCount(user);
	}

	@Override
	public UserDto findUserById(int id) {
		return super.selectByPrimaryKey(id);
	}

}
