package org.billow.utils;

import javax.servlet.http.HttpSession;

import org.billow.model.expand.UserDto;

public class LoginHelper {

	public static UserDto getLoginUser(HttpSession session) {
		Object attribute = session.getAttribute("currentUser");
		return attribute == null ? null : (UserDto) attribute;
	}

	public static Integer getLoginUserId() {
		HttpSession session = RequestUtils.getRequest().getSession();
		UserDto user = getLoginUser(session);
		Integer userId = null;
		if (user != null) {
			userId = user.getUserId();
		}
		return userId;
	}
}
