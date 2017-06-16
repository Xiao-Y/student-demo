package org.billow.model.expand;

import org.billow.model.domain.UserBase;

public class UserDto extends UserBase {

	private static final long serialVersionUID = -4013903577078716039L;

	private boolean rememberMe;

	/**
	 * 微信账号唯一标识
	 */
	private String openID;
	
	public boolean isRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}

	public String getOpenID() {
		return openID;
	}

	public void setOpenID(String openID) {
		this.openID = openID;
	}

}
