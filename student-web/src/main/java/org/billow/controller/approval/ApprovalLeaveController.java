package org.billow.controller.approval;

import javax.servlet.http.HttpSession;

import org.activiti.engine.task.Task;
import org.billow.api.approval.ApprovalLeaveService;
import org.billow.model.expand.LeaveDto;
import org.billow.model.expand.UserDto;
import org.billow.utils.LoginHelper;
import org.billow.utils.RequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

/**
 * 请假审批
 * 
 * @author XiaoY
 * @date: 2017年5月28日 下午10:08:07
 */
@Controller
@RequestMapping("/approvalLeave")
public class ApprovalLeaveController {

	@Autowired
	private ApprovalLeaveService approvalLeaveService;

	/**
	 * 查询个人任务（要审批的请假）
	 * 
	 * @return
	 * @author XiaoY
	 * @date: 2017年5月28日 下午10:10:05
	 */
	@ResponseBody
	@RequestMapping("/findApprovalLeave")
	public PageInfo<Task> findApprovalLeave(HttpSession session, LeaveDto leaveDto) {
		UserDto userDto = LoginHelper.getLoginUser(session);
		leaveDto.setUserDto(userDto);
		PageInfo<Task> list = approvalLeaveService.findApprovalLeave(leaveDto);
		return list;
	}
}
