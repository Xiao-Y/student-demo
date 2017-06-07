package org.billow.service.workflow;

import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.bpmn.diagram.ProcessDiagramGenerator;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.log4j.Logger;
import org.billow.api.workflow.WorkFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 工作流程统一处理类
 * 
 * @author liuyongtao
 * 
 * @date 2017年6月7日 上午8:58:53
 */
@Service
public class WorkFlowServiceImpl implements WorkFlowService {

	private static final Logger logger = Logger.getLogger(WorkFlowServiceImpl.class);

	@Autowired
	private HistoryService historyService;
	@Autowired
	private RepositoryService repositoryService;

	@Autowired
	private TaskService taskService;
	@Autowired
	private RuntimeService runtimeService;

	@Override
	public <T> List<T> findMyTask(List<T> list, String processDefinitionKey, String assignee) throws Exception {
		ProcessInstanceQuery processInstanceQuery = runtimeService.createProcessInstanceQuery();
		TaskQuery taskQuery = taskService.createTaskQuery().processDefinitionKey(processDefinitionKey).taskAssignee(assignee);
		ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
		for (int i = 0; i < list.size(); i++) {
			T t = list.get(i);
			Class<? extends Object> clazz = t.getClass();
			Method getId = clazz.getMethod("getId");
			Integer id = (Integer) getId.invoke(t);
			String businessKey = clazz.getSimpleName() + "." + id;
			// 查询流程实例
			ProcessInstance processInstance = processInstanceQuery.processInstanceBusinessKey(businessKey).singleResult();
			Method setProcessInstance = clazz.getMethod("setProcessInstance", ProcessInstance.class);
			setProcessInstance.invoke(t, processInstance);
			if (processInstance != null) {
				// 查询任务
				String processInstanceId = processInstance.getProcessInstanceId();
				Task task = taskQuery.processInstanceId(processInstanceId).singleResult();
				Method setTask = clazz.getMethod("setTask", Task.class);
				setTask.invoke(t, task);
				// 查询流程定义
				String processDefinitionId = processInstance.getProcessDefinitionId();
				ProcessDefinition processDefinition = processDefinitionQuery.processDefinitionId(processDefinitionId).singleResult();
				Method setProcessDefinition = clazz.getMethod("setProcessDefinition", ProcessDefinition.class);
				setProcessDefinition.invoke(t, processDefinition);
			}
		}
		return list;

	}

	@Override
	public void getActivitiProccessImage(String pProcessInstanceId, HttpServletResponse response) throws Exception {
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

	@Override
	public ProcessDefinition getProcessDefinition(String processDefinitionId) {
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId)
				.singleResult();
		return processDefinition;
	}
}
