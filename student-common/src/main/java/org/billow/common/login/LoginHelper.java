package org.billow.common.login;

import javax.servlet.http.HttpSession;

import org.billow.model.expand.UserDto;
import org.billow.utils.RequestUtils;
import org.billow.utils.ToolsUtils;

public class LoginHelper {

	public static UserDto getLoginUser(HttpSession session) {
		UserDto userDto = (UserDto) session.getAttribute("currentUser");
		if (ToolsUtils.isEmpty(userDto.getUserName())) {
			userDto = new UserDto();
			userDto.setUserName("employee");
			userDto.setUserId(1);
		}
		return userDto;
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
