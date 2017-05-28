package org.billow.controller.apply;

import org.apache.log4j.Logger;
import org.billow.api.leave.LeaveService;
import org.billow.model.custom.JsonResult;
import org.billow.model.custom.LeaveDto;
import org.billow.utils.constant.MessageTipsCst;
import org.billow.utils.constant.PagePathCst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 请假申请
 * 
 * @author XiaoY
 * @date: 2017年5月28日 下午3:46:50
 */
@Controller
@RequestMapping("/applyLeave")
public class LeaveController {

	private static final Logger logger = Logger.getLogger(LeaveController.class);

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
	public JsonResult saveLeave(LeaveDto leave) {
		JsonResult json = new JsonResult();
		logger.info("leave ===>>" + leave);
		try {
			leaveService.saveLeave(leave);
			json.setType(MessageTipsCst.TYPE_SUCCES);
		} catch (Exception e) {
			json.setType(MessageTipsCst.TYPE_ERROR);
			e.printStackTrace();
			logger.error(e);
		}
		return json;
	}
}
