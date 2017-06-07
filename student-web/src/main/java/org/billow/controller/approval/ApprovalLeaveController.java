package org.billow.controller.approval;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.billow.api.approval.ApprovalLeaveService;
import org.billow.api.workflow.WorkFlowService;
import org.billow.common.login.LoginHelper;
import org.billow.model.expand.LeaveDto;
import org.billow.model.expand.UserDto;
import org.billow.utils.constant.PagePathCst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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

	private static final Logger logger = Logger.getLogger(ApprovalLeaveController.class);

	@Autowired
	private ApprovalLeaveService approvalLeaveService;

	@Autowired
	private WorkFlowService workflowService;

	/**
	 * 查询个人任务（要审批的请假）
	 * 
	 * @return
	 * @author XiaoY
	 * @date: 2017年5月28日 下午10:10:05
	 */
	@RequestMapping("/findApprovalLeave")
	public ModelAndView findApprovalLeave(HttpSession session, LeaveDto leaveDto) {
		UserDto userDto = LoginHelper.getLoginUser(session);
		leaveDto.setUserDto(userDto);
		PageInfo<LeaveDto> list = approvalLeaveService.findApprovalLeave(leaveDto);
		ModelAndView av = new ModelAndView();
		av.addObject("paegs", list);
		av.setViewName(PagePathCst.BASEPATH_APPROVAL + "leaveTaskList");
		return av;
	}

	/**
	 * 获取流程图像，已执行节点和流程线高亮显示
	 */
	@RequestMapping("/getActivitiProccessImage/{pProcessInstanceId}")
	public void getActivitiProccessImage(@PathVariable String pProcessInstanceId, HttpServletResponse response) throws Exception {
		workflowService.getActivitiProccessImage(pProcessInstanceId, response);
	}
}
