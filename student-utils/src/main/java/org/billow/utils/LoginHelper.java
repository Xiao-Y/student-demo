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
			if (user.getUserName().equals("employee")) {
				userId = 1;
			} else if (user.getUserName().equals("admin")) {
				userId = 2;
			} else if (user.getUserName().equals("sa")) {
				userId = 3;
			} else if (user.getUserName().equals("Manager")) {
				userId = 4;
			} else if (user.getUserName().equals("General manager")) {
				userId = 5;
			} else if (user.getUserName().equals("Board Chairman")) {
				userId = 6;
			}
			// userId = user.getUserId();
		}
		return userId;
	}
}
