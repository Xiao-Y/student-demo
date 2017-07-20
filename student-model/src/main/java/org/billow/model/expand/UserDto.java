package org.billow.model.expand;

import java.util.ArrayList;
import java.util.List;

import org.billow.model.domain.UserBase;

public class UserDto extends UserBase {

	private static final long serialVersionUID = -4013903577078716039L;

	private boolean rememberMe;

	private List<UserRoleDto> userRoleDtos = new ArrayList<>();
	private List<RoleDto> roleDtos = new ArrayList<>();

	public boolean isRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}

	public List<UserRoleDto> getUserRoleDtos() {
		return userRoleDtos;
	}

	public void setUserRoleDtos(List<UserRoleDto> userRoleDtos) {
		this.userRoleDtos = userRoleDtos;
	}

	public List<RoleDto> getRoleDtos() {
		return roleDtos;
	}

	public void setRoleDtos(List<RoleDto> roleDtos) {
		this.roleDtos = roleDtos;
	}

	@Override
	public String toString() {
		return super.toString();
	}
}
