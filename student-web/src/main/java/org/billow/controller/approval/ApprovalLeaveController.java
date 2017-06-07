package org.billow.controller.approval;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.bpmn.diagram.ProcessDiagramGenerator;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.apache.log4j.Logger;
import org.billow.api.approval.ApprovalLeaveService;
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
	private HistoryService historyService;
	@Autowired
	private RepositoryService repositoryService;

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
		av.setViewName(PagePathCst.BASEPATH_APPROVAL + "taskList");
		return av;
	}

	/**
	 * 获取流程图像，已执行节点和流程线高亮显示
	 */
	@RequestMapping("/getActivitiProccessImage/{pProcessInstanceId}")
	public void getActivitiProccessImage(@PathVariable String pProcessInstanceId, HttpServletResponse response) throws Exception {
		logger.info("[开始]-获取流程图图像");
		// 设置页面不缓存
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		try {
			// 获取历史流程实例
			HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery()
					.processInstanceId(pProcessInstanceId).singleResult();

			if (historicProcessInstance == null) {
				throw new RuntimeException("获取流程实例ID[" + pProcessInstanceId + "]对应的历史流程实例失败！");
			} else {
				// 获取流程定义
				ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
						.getDeployedProcessDefinition(historicProcessInstance.getProcessDefinitionId());

				// 获取流程历史中已执行节点，并按照节点在流程中执行先后顺序排序
				List<HistoricActivityInstance> historicActivityInstanceList = historyService.createHistoricActivityInstanceQuery()
						.processInstanceId(pProcessInstanceId).orderByHistoricActivityInstanceId().asc().list();

				// 已执行的节点ID集合
				List<String> executedActivityIdList = new ArrayList<String>();
				int index = 1;
				logger.info("获取已经执行的节点ID");
				for (HistoricActivityInstance activityInstance : historicActivityInstanceList) {
					executedActivityIdList.add(activityInstance.getActivityId());
					logger.info("第[" + index + "]个已执行节点=" + activityInstance.getActivityId() + " : " + activityInstance.getActivityName());
					index++;
				}

				// 获取流程图图像字符流
				InputStream imageStream = ProcessDiagramGenerator.generateDiagram(processDefinition, "png", executedActivityIdList);

				response.setContentType("image/png");
				OutputStream os = response.getOutputStream();
				int bytesRead = 0;
				byte[] buffer = new byte[8192];
				while ((bytesRead = imageStream.read(buffer, 0, 8192)) != -1) {
					os.write(buffer, 0, bytesRead);
				}
				os.close();
				imageStream.close();
			}
			logger.info("[完成]-获取流程图图像");
		} catch (Exception e) {
			logger.error("【异常】-获取流程图失败！" + e.getMessage());
			throw new RuntimeException("获取流程图失败！" + e.getMessage());
		}
	}
}
