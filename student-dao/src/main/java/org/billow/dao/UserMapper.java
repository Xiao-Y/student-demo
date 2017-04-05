package org.billow.dao;

import java.util.List;

import org.billow.dao.base.BaseMapper;
import org.billow.model.domain.User;


public interface UserMapper extends BaseMapper<User> {

	List<User> findUserList(User user);

	int findUserCount(User user);
}