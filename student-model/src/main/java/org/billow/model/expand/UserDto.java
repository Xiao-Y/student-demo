package org.billow.model.expand;

import org.billow.model.domain.UserBase;

public class UserDto extends UserBase {

	private static final long serialVersionUID = -4013903577078716039L;

	private boolean rememberMe;

	public boolean isRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}

}
