package org.billow.common.activiti.custom.manager;

import java.util.List;

import org.apache.log4j.Logger;
import org.activiti.engine.identity.Group;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.activiti.engine.impl.persistence.entity.UserEntityManager;
import org.billow.common.activiti.utils.ActivitiUserUtils;
import org.billow.dao.UserDao;
import org.billow.model.expand.RoleDto;
import org.billow.model.expand.UserDto;
import org.billow.utils.ToolsUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomUserEntityManager extends UserEntityManager {

	private static final Logger logger = Logger.getLogger(CustomUserEntityManager.class);

	@Autowired
	private UserDao userDao;

	@Override
	public UserEntity findUserById(String userId) {
		UserEntity userEntity = new UserEntity();
		// 这是我们的dao方法查询回来的方法，是自己定义的user
		UserDto cue = userDao.selectByPrimaryKey(Integer.valueOf(userId));
		// 将自定义的user转化为activiti的类
		userEntity = ActivitiUserUtils.toActivitiUser(cue);
		return userEntity;// 返回的是activiti的实体类
	}

	@Override
	public List<Group> findGroupsByUser(final String userId) {
		if (ToolsUtils.isEmpty(userId)) {
			return null;
		}
		List<RoleDto> groupIds = userDao.findRoleListByUserId(userId);
		List<Group> gs = null;
		gs = ActivitiUserUtils.toActivitiGroups(groupIds);
		return gs;

	}
}
