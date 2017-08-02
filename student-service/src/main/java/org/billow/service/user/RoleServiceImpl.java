package org.billow.service.user;

import javax.annotation.Resource;

import org.billow.api.user.RoleService;
import org.billow.dao.RoleDao;
import org.billow.dao.base.BaseDao;
import org.billow.model.expand.RoleDto;
import org.billow.service.base.BaseServiceImpl;

public class RoleServiceImpl extends BaseServiceImpl<RoleDto> implements RoleService {

	@Resource
	private RoleDao roleDao;
	
	@Resource
	@Override
	public void setBaseDao(BaseDao<RoleDto> baseDao) {
		super.baseDao = this.roleDao;
	}
}
