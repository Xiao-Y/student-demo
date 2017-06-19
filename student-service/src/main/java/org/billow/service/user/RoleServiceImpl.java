package org.billow.service.user;

import javax.annotation.Resource;

import org.billow.api.user.RoleService;
import org.billow.dao.RoleDao;
import org.billow.model.expand.RoleDto;
import org.billow.service.base.BaseServiceImpl;

public class RoleServiceImpl extends BaseServiceImpl<RoleDto> implements RoleService {

	private RoleDao roleDao;

	@Resource
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
		super.setBaseDao(roleDao);
	}
}
