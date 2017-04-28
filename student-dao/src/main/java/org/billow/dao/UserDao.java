package org.billow.dao;

import java.util.List;

import org.billow.dao.base.BaseDao;
import org.billow.model.domain.User;


public interface UserDao extends BaseDao<User> {

	List<User> findUserList(User user);

	int findUserCount(User user);
}