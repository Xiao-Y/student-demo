package org.billow.dao;

import java.util.List;

import org.billow.dao.base.BaseDao;
import org.billow.model.expand.UserDto;


public interface UserDao extends BaseDao<UserDto> {

	List<UserDto> findUserList(UserDto user);

	int findUserCount(UserDto user);
}