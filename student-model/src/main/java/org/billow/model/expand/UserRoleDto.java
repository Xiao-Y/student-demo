package org.billow.model.expand;

import org.billow.model.domain.UserRoleBase;

public class UserRoleDto extends UserRoleBase {

	private static final long serialVersionUID = -8355180950808620833L;
	
	private RoleDto roleDto;

	public RoleDto getRoleDto() {
		return roleDto;
	}

	public void setRoleDto(RoleDto roleDto) {
		this.roleDto = roleDto;
	}
	
}