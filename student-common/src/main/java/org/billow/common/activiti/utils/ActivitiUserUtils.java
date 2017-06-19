package org.billow.common.activiti.utils;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.identity.Group;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.billow.model.expand.RoleDto;
import org.billow.model.expand.UserDto;

public class ActivitiUserUtils {

	/**
	 * 系统user实体转换成activiti user实体
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @param userDto
	 *            系统user
	 * @return activiti user实体
	 * 
	 * @date 2017年6月15日 上午9:18:11
	 */
	public static UserEntity toActivitiUser(UserDto userDto) {
		UserEntity userEntity = new UserEntity();
		userEntity.setId(userDto.getUserId().toString());
		userEntity.setFirstName(userDto.getUserName());
		userEntity.setLastName("");
		userEntity.setPassword(userDto.getPassword());
		userEntity.setEmail(userDto.getPhoneNumber());
		userEntity.setRevision(1);
		return userEntity;
	}

	/**
	 * 系统RoleDto实体转换成activiti Group实体
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @param roleDto
	 *            系统RoleDto
	 * @return activiti Group实体
	 * 
	 * @date 2017年6月15日 上午9:18:11
	 */
	public static GroupEntity toActivitiGroup(RoleDto roleDto) {
		GroupEntity groupEntity = new GroupEntity();
		groupEntity.setRevision(1);
		groupEntity.setType("assignment");
		groupEntity.setId(roleDto.getId().toString());
		groupEntity.setName(roleDto.getRolename());
		return groupEntity;
	}

	/**
	 * 系统List&lt;RoleDto&gt;实体转换成activiti List&lt;Group&gt;实体
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @param roleDtos
	 *            系统List&lt;RoleDto&gt;
	 * @return List&lt;Group&gt;
	 * 
	 * @date 2017年6月15日 上午9:31:05
	 */
	public static List<Group> toActivitiGroups(List<RoleDto> roleDtos) {
		List<Group> groups = new ArrayList<Group>();
		for (RoleDto sysRole : roleDtos) {
			GroupEntity groupEntity = toActivitiGroup(sysRole);
			groups.add(groupEntity);
		}
		return groups;
	}
}
