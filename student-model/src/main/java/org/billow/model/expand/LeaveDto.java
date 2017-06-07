package org.billow.model.expand;

import org.billow.model.domain.LeaveBase;

/**
 * 请假实体对象
 * 
 * @author XiaoY
 * @date: 2017年5月28日 下午3:49:01
 */
public class LeaveDto extends LeaveBase {

	private static final long serialVersionUID = 3221605134094266678L;
	private UserDto userDto;
	// 请类型，view,edit
	public String actionType;
	private String type;
	// 批注内容
	private String commentInfo;

	public UserDto getUserDto() {
		return userDto;
	}

	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCommentInfo() {
		return commentInfo;
	}

	public void setCommentInfo(String commentInfo) {
		this.commentInfo = commentInfo;
	}

}