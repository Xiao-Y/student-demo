package org.billow.api;

import java.util.List;

import org.billow.api.base.BaseService;
import org.billow.model.domain.User;

public interface UserService extends BaseService<User> {

	public void deleteTest();

	public List<User> findUserList(User user);

	public int findUserCount(User user);
}
