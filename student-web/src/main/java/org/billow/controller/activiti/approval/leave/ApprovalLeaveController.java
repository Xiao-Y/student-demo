package org.billow.controller.activiti.approval.leave;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.billow.api.apply.ApplyLeaveService;
import org.billow.api.approval.ApprovalLeaveService;
import org.billow.api.workflow.WorkFlowService;
import org.billow.common.login.LoginHelper;
import org.billow.model.custom.JsonResult;
import org.billow.model.expand.LeaveDto;
import org.billow.model.expand.UserDto;
import org.billow.utils.ToolsUtils;
import org.billow.utils.constant.ActivitiCst;
import org.billow.utils.constant.MessageTipsCst;
import org.billow.utils.constant.PagePathCst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
	@Autowired
	private ApplyLeaveService applyLeaveService;

	/**
	 * 查询个人任务列表（要审批的请假）
	 * 
	 * @return
	 * @author XiaoY
	 * @date: 2017年5月28日 下午10:10:05
	 */
	@RequestMapping("/findApprovalLeave")
	public ModelAndView findApprovalLeave(HttpSession session, LeaveDto leaveDto) {
		UserDto userDto = LoginHelper.getLoginUser(session);
		leaveDto.setUserDto(userDto);
		PageInfo<LeaveDto> list = null;
		try {
			list = approvalLeaveService.findApprovalLeave(leaveDto);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询个人任务列表出错！");
		}
		ModelAndView av = new ModelAndView();
		av.addObject("page", list);
		av.setViewName(PagePathCst.BASEPATH_ACTIVITI_APPROVAL + "leave/leaveTaskList");
		return av;
	}

	/**
	 * 进入请假审批
	 * 
	 * @param leave
	 * @return
	 * @author XiaoY
	 * @date: 2017年5月28日 下午3:58:24
	 */
	@RequestMapping("/leaveApplyApp")
	public ModelAndView leaveApplyApp(HttpSession session, LeaveDto leave) {
		ModelAndView av = new ModelAndView();
		UserDto userDto = LoginHelper.getLoginUser(session);
		String reportBack = leave.getFlag();
		try {
			leave.setUserDto(userDto);
			leave.setType(ActivitiCst.TYPE_LEAVE_COMMENT);
			LeaveDto leaveDto = applyLeaveService.findLeaveDto(leave);
			List<String> transNames = workflowService.getOutGoingTransNames(leave.getTaskId());
			av.addObject("leaveDto", leaveDto);
			av.addObject("transNames", transNames);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 进入销假页面
		if (ToolsUtils.isNotEmpty(reportBack) && "reportBack".equals(reportBack)) {
			av.setViewName(PagePathCst.BASEPATH_ACTIVITI_APPROVAL + "leave/leaveApplyReportBack");
		} else {
			av.setViewName(PagePathCst.BASEPATH_ACTIVITI_APPROVAL + "leave/leaveApplyApp");
		}
		return av;
	}

	/**
	 * 请假审批
	 * 
	 * @param leave
	 * @return
	 * @author XiaoY
	 * @date: 2017年5月28日 下午3:58:24
	 */
	@ResponseBody
	@RequestMapping("/saveLeaveApplyApp")
	public JsonResult saveLeaveApplyApp(HttpSession session, LeaveDto leave) {
		String message;
		String type;
		UserDto userDto = LoginHelper.getLoginUser(session);
		try {
			leave.setUserDto(userDto);
			approvalLeaveService.saveLeaveApplyApp(leave);
			type = MessageTipsCst.TYPE_SUCCES;
			message = MessageTipsCst.SUBMIT_SUCCESS;
		} catch (Exception e) {
			type = MessageTipsCst.TYPE_ERROR;
			message = MessageTipsCst.SUBMIT_FAILURE;
			e.printStackTrace();
			logger.error(e);
		}
		JsonResult json = new JsonResult();
		json.setMessage(message);
		json.setType(type);
		json.setRoot("/approvalLeave/findApprovalLeave");
		return json;
	}

	/**
	 * 任务签收
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @param leaveId
	 *            请假表主键
	 * @param taskId
	 *            任务Id
	 * @return
	 * 
	 * @date 2017年6月13日 下午2:40:28
	 */
	@ResponseBody
	@RequestMapping("/leaveClaim/{leaveId}/{taskId}")
	public JsonResult leaveClaim(@PathVariable("leaveId") Integer leaveId, @PathVariable("taskId") String taskId,
			HttpSession session) {
		String message;
		String type;
		UserDto userDto = LoginHelper.getLoginUser(session);
		LeaveDto leaveDto = new LeaveDto();
		leaveDto.setId(leaveId);
		leaveDto.setUserDto(userDto);
		leaveDto.setStatus("3");
		try {
			approvalLeaveService.leaveClaim(leaveDto, taskId);
			type = MessageTipsCst.TYPE_SUCCES;
			message = MessageTipsCst.CLAIM_SUCCESS;
		} catch (Exception e) {
			type = MessageTipsCst.TYPE_ERROR;
			message = MessageTipsCst.CLAIM_FAILURE;
			e.printStackTrace();
			logger.error(e);
		}
		JsonResult json = new JsonResult();
		json.setMessage(message);
		json.setType(type);
		json.setRoot("/approvalLeave/findApprovalLeave");
		return json;
	}
}
