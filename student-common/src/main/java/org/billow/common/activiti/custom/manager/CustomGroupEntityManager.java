package org.billow.common.activiti.custom.manager;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.identity.Group;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.UserEntityManager;
import org.billow.api.user.UserService;
import org.billow.model.expand.RoleDto;
import org.billow.model.expand.UserDto;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomGroupEntityManager extends UserEntityManager {

	@Autowired
	private UserService userService;// 用于查询实际业务中用户表、角色等表

	@Override
	public List<Group> findGroupsByUser(final String userId) {
		if (userId == null) {
			return null;
		}
		UserDto user = userService.findRoleListByUserId(Integer.valueOf(userId));
		List<RoleDto> roleDtos = user.getRoleDtos();
		List<Group> gs = new ArrayList<>();
		for (RoleDto bGroup : roleDtos) {
			GroupEntity g = new GroupEntity();
			g.setRevision(1);
			g.setType("assignment");
			String roleId = String.valueOf(bGroup.getId());
			// String activitRole = bindGroupWithRole.get(roleId);//此处只是根据RoleId获取RoleCode， 因实际表中无RoleCode字段，暂且如此实际，此行可注释掉
			// g.setId(/* activitRole != null ? activitRole : */roleId);
			g.setId(roleId);
			g.setName(bGroup.getRolename());
			gs.add(g);
		}
		return gs;
	}
}