package org.billow.dao;

import java.util.List;

import org.billow.dao.base.BaseDao;
import org.billow.model.expand.RoleDto;
import org.billow.model.expand.UserDto;

public interface UserDao extends BaseDao<UserDto> {

	List<UserDto> findUserList(UserDto user);

	int findUserCount(UserDto user);

	UserDto getUserByOpenId(String openId);

	/**
	 * 通过用户id查询出所有的角色
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @param userId
	 *            用户id
	 * @return 角色
	 * 
	 * @date 2017年6月19日 下午3:48:57
	 */
	List<RoleDto> findRoleListByUserId(String userId);
}