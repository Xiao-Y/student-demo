package org.billow.controller.act;

import java.util.List;

import org.activiti.engine.task.Comment;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletResponse;

import org.billow.api.workflow.WorkFlowService;
import org.billow.utils.constant.PagePathCst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/workFlowController")
public class WorkFlowController {

	private static final Logger logger = Logger.getLogger(WorkFlowController.class);

	@Autowired
	private WorkFlowService workFlowService;

	/**
	 * 打开流程图显示页面
	 **/
	@RequestMapping("/openActivitiProccessImagePage/{commentType}/{pProcessInstanceId}")
	public ModelAndView openActivitiProccessImagePage(@PathVariable String commentType,
			@PathVariable String pProcessInstanceId) throws Exception {
		logger.info("[开始]-打开流程图显示页面");
		// 查询批注信息
		List<Comment> comments = workFlowService.findCommentByProcessInstanceId(pProcessInstanceId, commentType);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("processInstanceId", pProcessInstanceId);
		modelAndView.addObject("comments", comments);
		modelAndView.setViewName(PagePathCst.BASEPATH_WORKFLOW + "activitiProccessImagePage");
		logger.info("[完成]-打开流程图显示页面");
		return modelAndView;
	}

	/**
	 * 获取流程图像，已执行节点和流程线高亮显示
	 */
	@RequestMapping("/getActivitiProccessImage/{pProcessInstanceId}")
	public void getActivitiProccessImage(@PathVariable String pProcessInstanceId,
			HttpServletResponse response) throws Exception {
		workFlowService.getActivitiProccessImage(pProcessInstanceId, response);
	}

}
