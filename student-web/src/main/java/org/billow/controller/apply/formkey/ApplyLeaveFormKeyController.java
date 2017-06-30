package org.billow.controller.apply.formkey;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.runtime.ProcessInstance;
import org.apache.log4j.Logger;
import org.billow.api.apply.ApplyLeaveService;
import org.billow.api.workflow.WorkFlowService;
import org.billow.common.login.LoginHelper;
import org.billow.model.custom.JsonResult;
import org.billow.model.expand.LeaveDto;
import org.billow.model.expand.UserDto;
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
 * 请假申请
 * 
 * @author XiaoY
 * @date: 2017年5月28日 下午3:46:50
 */
@Controller
@RequestMapping("/formkey/applyLeave")
public class ApplyLeaveFormKeyController {

	private static final Logger logger = Logger.getLogger(ApplyLeaveFormKeyController.class);

	@Autowired
	private ApplyLeaveService applyLeaveService;

	@Autowired
	private WorkFlowService workFlowService;

	/**
	 * 请假申请
	 * 
	 * @param leave
	 * @return
	 * @author XiaoY
	 * @date: 2017年5月28日 下午3:58:24
	 */
	@RequestMapping("/editLeave")
	public ModelAndView editLeave(LeaveDto leave) {
		ModelAndView av = new ModelAndView();
		String viewName = PagePathCst.BASEPATH_APPLY + "form-key/leaveApply";
		String processDefinitionKey = ActivitiCst.PROCESSDEFINITION_KEY_LEAVE_FORMKEY;
		leave.setProcessDefinitionKey(processDefinitionKey);
		av.addObject("leaveDto", leave);
		if (leave.getId() != null) {
			leave = applyLeaveService.selectByPrimaryKey(leave);
			leave.setProcessDefinitionKey(processDefinitionKey);
			if (leave != null && "7".equals(leave.getStatus())) {// 被驳回的
				viewName = PagePathCst.BASEPATH_APPLY + "form-key/leaveApplyRe";
				av.addObject("leaveDto", leave);
			}
		}
		av.setViewName(viewName);
		return av;
	}

	@ResponseBody
	@RequestMapping("/getStart/{processDefinitionKey}")
	public Object getStart(@PathVariable String processDefinitionKey, HttpServletRequest request, HttpServletResponse response) {
		// 根据流程定义KEY读取外置表单
		Object startForm = workFlowService.getRenderedStartForm(processDefinitionKey);
		return startForm;
	}

	/**
	 * 提交请假申请,启动流程实例
	 * 
	 * @param leave
	 * @return
	 * @author XiaoY
	 * @date: 2017年5月28日 下午3:58:24
	 */
	@ResponseBody
	@RequestMapping("/saveLeave")
	public JsonResult saveLeave(HttpSession session, LeaveDto leave) {
		UserDto userDto = LoginHelper.getLoginUser(session);
		leave.setUserDto(userDto);
		leave.setUserName(userDto.getUserName());
		leave.setType(LeaveDto.TYPE_FORMKEY);
		String message = "";
		String type = "";
		try {
			leave.setStatus("1");
			ProcessInstance processInstance = applyLeaveService.saveLeave(leave);
			message = "流程已启动，流程ID：" + processInstance.getId();
			type = MessageTipsCst.TYPE_SUCCES;
		} catch (ActivitiException e) {
			if (e.getMessage().indexOf("no processes deployed with key") != -1) {
				logger.warn("没有部署流程!", e);
				message = "没有部署流程，请在[流程管理]->[流程部署]页面点击<部署流程>";
				type = MessageTipsCst.TYPE_ERROR;
			} else {
				message = "系统内部错误！";
				type = MessageTipsCst.TYPE_ERROR;
				logger.error("启动请假流程失败：", e);
			}
		} catch (Exception e) {
			message = "系统内部错误！";
			type = MessageTipsCst.TYPE_ERROR;
			logger.error("启动请假流程失败：", e);
		}
		JsonResult json = new JsonResult();
		json.setMessage(message);
		json.setType(type);
		json.setRoot("/formkey/applyLeave/findLeaveList");
		return json;
	}

	/**
	 * 查询个人请假列表
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @param session
	 * @param leave
	 * @return
	 * 
	 * @date 2017年6月9日 上午8:22:18
	 */
	@RequestMapping("/findLeaveList")
	public ModelAndView findLeaveList(HttpSession session, LeaveDto leave) {
		UserDto userDto = LoginHelper.getLoginUser(session);
		leave.setUserDto(userDto);
		leave.setUserName(userDto.getUserName());
		leave.setType(LeaveDto.TYPE_FORMKEY);
		PageInfo<LeaveDto> pages = applyLeaveService.findLeaveList(leave);
		ModelAndView av = new ModelAndView();
		av.addObject("page", pages);
		av.setViewName(PagePathCst.BASEPATH_APPLY + "form-key/leaveApplyList");
		return av;
	}

	/**
	 * 被驳回后修改
	 * 
	 * @param session
	 * @param leave
	 * @return
	 * @author XiaoY
	 * @date: 2017年6月14日 下午9:17:28
	 */
	@ResponseBody
	@RequestMapping("/updateLeave")
	public JsonResult updateLeave(HttpSession session, LeaveDto leave) {
		UserDto userDto = LoginHelper.getLoginUser(session);
		leave.setUserDto(userDto);
		leave.setUserName(userDto.getUserName());
		String message = "";
		String type = "";
		try {
			leave.setStatus("1");
			applyLeaveService.updateLeave(leave);
			message = MessageTipsCst.UPDATE_SUCCESS;
			type = MessageTipsCst.TYPE_SUCCES;
		} catch (Exception e) {
			message = "系统内部错误！";
			type = MessageTipsCst.TYPE_ERROR;
			logger.error("启动请假流程失败：", e);
		}
		JsonResult json = new JsonResult();
		json.setMessage(message);
		json.setType(type);
		json.setRoot("/approvalLeave/findApprovalLeave");
		return json;
	}
}
