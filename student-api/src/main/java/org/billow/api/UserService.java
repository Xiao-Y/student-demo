package org.billow.api;

import java.util.List;

import com.billow.base.service.BaseService;
import com.billow.business.model.User;

public interface UserService extends BaseService<User> {

	public void deleteTest();

	public List<User> findUserList(User user);

	public int findUserCount(User user);
}
