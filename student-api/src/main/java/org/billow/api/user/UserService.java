package org.billow.api.user;

import java.util.List;

import org.billow.api.base.BaseService;
import org.billow.model.expand.UserDto;

public interface UserService extends BaseService<UserDto> {

	public void deleteTest();

	public List<UserDto> findUserList(UserDto user);

	public int findUserCount(UserDto user);

	public UserDto findUserById(Integer id);

	public UserDto getUserByOpenId(String string);
}
