package org.billow.controller.apply;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.runtime.ProcessInstance;
import org.apache.log4j.Logger;
import org.billow.api.leave.LeaveService;
import org.billow.common.login.LoginHelper;
import org.billow.model.custom.JsonResult;
import org.billow.model.expand.LeaveDto;
import org.billow.model.expand.UserDto;
import org.billow.utils.constant.MessageTipsCst;
import org.billow.utils.constant.PagePathCst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
@RequestMapping("/applyLeave")
public class ApplyLeaveController {

	private static final Logger logger = Logger.getLogger(ApplyLeaveController.class);

	@Autowired
	private LeaveService leaveService;

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
		av.setViewName(PagePathCst.BASEPATH_APPLY + "leaveApply");
		return av;
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
		String message = "";
		String type = "";
		try {
			leave.setStatus("1");
			ProcessInstance processInstance = leaveService.saveLeave(leave);
			message = "流程已启动，流程ID：" + processInstance.getId();
			type = MessageTipsCst.TYPE_SUCCES;
		} catch (ActivitiException e) {
			if (e.getMessage().indexOf("no processes deployed with key") != -1) {
				logger.warn("没有部署流程!", e);
				message = "没有部署流程，请在[工作流]->[流程管理]页面点击<重新部署流程>";
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
		return json;
	}

	public ModelAndView findLeaveList(HttpSession session, LeaveDto leave) {
		UserDto userDto = LoginHelper.getLoginUser(session);
		leave.setUserDto(userDto);
		PageInfo<LeaveDto> pages = leaveService.findLeaveList(leave);
		ModelAndView av = new ModelAndView();
		av.addObject("pages", pages);
		return av;
	}
}
