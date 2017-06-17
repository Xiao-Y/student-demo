package org.billow.api.user;

import java.util.List;

import org.billow.api.base.BaseService;
import org.billow.model.expand.UserDto;

public interface UserService extends BaseService<UserDto> {

	public void deleteTest();

	public List<UserDto> findUserList(UserDto user);

	public int findUserCount(UserDto user);

	public UserDto findUserById(Integer id);

	/**
	 * 微信查询
	 * 
	 * @param openId
	 * @return
	 * @author XiaoY
	 * @date: 2017年6月17日 上午7:54:00
	 */
	public UserDto getUserByOpenId(String openId);
}
