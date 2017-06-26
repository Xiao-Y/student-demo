package org.billow.common.activiti.custom.manager;

import java.util.List;

import org.activiti.engine.identity.Group;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.activiti.engine.impl.persistence.entity.UserEntityManager;
import org.billow.api.user.UserService;
import org.billow.common.activiti.utils.ActivitiUserUtils;
import org.billow.model.expand.RoleDto;
import org.billow.model.expand.UserDto;
import org.billow.utils.ToolsUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomUserEntityManager extends UserEntityManager {

	@Autowired
	private UserService userService;

	@Override
	public UserEntity findUserById(String userId) {
		UserEntity userEntity = new UserEntity();
		// 这是我们的dao方法查询回来的方法，是自己定义的user
		UserDto dto = new UserDto();
		dto.setUserId(Integer.valueOf(userId));
		UserDto cue = userService.selectByPrimaryKey(dto);
		// 将自定义的user转化为activiti的类
		userEntity = ActivitiUserUtils.toActivitiUser(cue);
		return userEntity;// 返回的是activiti的实体类
	}

	@Override
	public List<Group> findGroupsByUser(final String userId) {
		if (ToolsUtils.isEmpty(userId)) {
			return null;
		}
		UserDto user = userService.findRoleListByUserId(Integer.valueOf(userId));
		List<RoleDto> roleDtos = user.getRoleDtos();
		List<Group> groups = ActivitiUserUtils.toActivitiGroups(roleDtos);
		return groups;
	}
}
