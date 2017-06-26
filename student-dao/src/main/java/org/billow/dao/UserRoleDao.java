package org.billow.dao;

import java.util.List;

import org.billow.model.domain.UserRoleBase;
import org.billow.model.expand.UserRoleDto;

public interface UserRoleDao {
	
	int insert(UserRoleBase record);

	int insertSelective(UserRoleBase record);

	/**
	 * 通过用户id查询出用户角色对象
	 * 
	 * @param userId
	 *            用户id
	 * @return 用户角色对象
	 * @author XiaoY
	 * @date: 2017年6月19日 下午9:19:17
	 */
	List<UserRoleDto> findUserRoleByUserId(Integer userId);
}