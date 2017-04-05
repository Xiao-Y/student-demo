package org.billow.dao;

import java.util.List;

import com.billow.base.mapper.BaseMapper;
import com.billow.business.model.User;

public interface UserMapper extends BaseMapper<User> {

	List<User> findUserList(User user);

	int findUserCount(User user);
}