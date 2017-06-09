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
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.bpmn.diagram.ProcessDiagramGenerator;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.log4j.Logger;
import org.billow.api.workflow.WorkFlowService;
import org.billow.utils.ToolsUtils;
import org.billow.utils.constant.ActivitiCommentCst;
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
	public <T> List<T> findMyTaskList(List<T> list, String processDefinitionKey, String assignee) throws Exception {
		// ProcessInstanceQuery processInstanceQuery =
		// runtimeService.createProcessInstanceQuery();
		// TaskQuery taskQuery =
		// taskService.createTaskQuery().processDefinitionKey(processDefinitionKey).taskAssignee(assignee);
		// ProcessDefinitionQuery processDefinitionQuery =
		// repositoryService.createProcessDefinitionQuery();
		// for (int i = 0; i < list.size(); i++) {
		// T t = list.get(i);
		// Class<? extends Object> clazz = t.getClass();
		// Method getId = clazz.getMethod("getId");
		// Integer id = (Integer) getId.invoke(t);
		// String businessKey = clazz.getSimpleName() + "." + id;
		// // 查询流程实例
		// ProcessInstance processInstance =
		// processInstanceQuery.processInstanceBusinessKey(businessKey).singleResult();
		// Method setProcessInstance = clazz.getMethod("setProcessInstance",
		// ProcessInstance.class);
		// setProcessInstance.invoke(t, processInstance);
		// if (processInstance != null) {
		// // 查询任务
		// String processInstanceId = processInstance.getProcessInstanceId();
		// Task task =
		// taskQuery.processInstanceId(processInstanceId).singleResult();
		// Method setTask = clazz.getMethod("setTask", Task.class);
		// setTask.invoke(t, task);
		// // 查询流程定义
		// String processDefinitionId =
		// processInstance.getProcessDefinitionId();
		// ProcessDefinition processDefinition =
		// processDefinitionQuery.processDefinitionId(processDefinitionId).singleResult();
		// Method setProcessDefinition = clazz.getMethod("setProcessDefinition",
		// ProcessDefinition.class);
		// setProcessDefinition.invoke(t, processDefinition);
		// }
		// }

		// 创建查询
		HistoricProcessInstanceQuery historicProcessInstanceQuery = historyService.createHistoricProcessInstanceQuery();
		TaskQuery taskQuery = taskService.createTaskQuery().processDefinitionKey(processDefinitionKey);//.taskAssignee(assignee);
		ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();

		for (int i = 0; i < list.size(); i++) {
			T t = list.get(i);
			Class<? extends Object> clazz = t.getClass();
			// 拼接businessKey
			Method getId = clazz.getMethod("getId");
			Integer id = (Integer) getId.invoke(t);
			String businessKey = clazz.getSimpleName() + "." + id;
			// 查询历史流程实例（为获取流程实例Id）
			HistoricProcessInstance historicProcessInstance = historicProcessInstanceQuery.processInstanceBusinessKey(businessKey).singleResult();
			// 设置历史流程到实体类中
			Method setProcessInstance = clazz.getMethod("setHistoricProcessInstance", HistoricProcessInstance.class);
			setProcessInstance.invoke(t, historicProcessInstance);
			if (historicProcessInstance != null) {
				// 通过流程实例Id查询当前用户的任务
				String processInstanceId = historicProcessInstance.getId();
				Task task = taskQuery.processInstanceId(processInstanceId).singleResult();
				// 设置任务到实体类中
				Method setTask = clazz.getMethod("setTask", Task.class);
				setTask.invoke(t, task);
				// 查询流程定义
				String processDefinitionId = historicProcessInstance.getProcessDefinitionId();
				ProcessDefinition processDefinition = processDefinitionQuery.processDefinitionId(processDefinitionId).singleResult();
				// 设置流程定义到实体类中
				Method setProcessDefinition = clazz.getMethod("setProcessDefinition", ProcessDefinition.class);
				setProcessDefinition.invoke(t, processDefinition);
				String taskName = "已结束";
				if (task != null) {
					// 1.获取当前流程的流程定义
					ProcessDefinitionEntity pdf = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
							.getDeployedProcessDefinition(processDefinitionId);
					// 2.流程定义获得所有的节点
					List<ActivityImpl> activities = pdf.getActivities();
					// 3.根据任务获取当前流程执行ID，执行实例以及当前流程节点的ID
					String executionId = task.getExecutionId();
					// 3.1根据流程执行ID获取执行实例
					ExecutionEntity execution = (ExecutionEntity) runtimeService.createExecutionQuery().executionId(executionId).singleResult();
					// 3.2从执行实例中获取当前流程节点的ID
					String activitiId = execution.getActivityId();
					// 4、然后循环activitiList
					// 并判断出当前流程所处节点
					for (ActivityImpl activityImpl : activities) {
						if (activitiId.equals(activityImpl.getId())) {
							// 当前任(输出某个节点的某种属性)
							taskName = (String) activityImpl.getProperty("name");
							break;
						}
					}
				}
				Method setTaskName = clazz.getMethod("setTaskName", String.class);
				setTaskName.invoke(t, taskName);
			}
		}
		return list;

	}

	@Override
	public <T> T findMyTask(T t, String processDefinitionKey, String assignee) throws Exception {
		ProcessInstanceQuery processInstanceQuery = runtimeService.createProcessInstanceQuery();
		TaskQuery taskQuery = taskService.createTaskQuery().processDefinitionKey(processDefinitionKey).taskAssignee(assignee);
		ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();

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
		return t;

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

	@Override
	public ProcessInstance startProcessInstanceByKey(String processDefinitionKey, String businessKey) {
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey, businessKey);
		return processInstance;
	}

	@Override
	public Task findTaskByProcessInstanceId(String processInstanceId) {
		Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
		return task;
	}

	@Override
	public void addComment(String taskId, String processInstanceId, String type, String message) {
		taskService.addComment(taskId, processInstanceId, type, message);
	}

	@Override
	public List<Comment> findCommentByProcessInstanceId(String processInstanceId, String type) {
		List<Comment> list = new ArrayList<>();
		// 使用流程实例ID，查询历史任务，获取历史任务对应的每个任务ID
		List<HistoricTaskInstance> htiList = historyService.createHistoricTaskInstanceQuery()// 历史任务表查询
				.processInstanceId(processInstanceId)// 使用流程实例ID查询
				.list();
		if (ToolsUtils.isNotEmpty(htiList)) {
			for (HistoricTaskInstance ht : htiList) {
				// 任务ID
				String htaskId = ht.getId();
				// 获取批注信息
				List<Comment> taskComments = taskService.getTaskComments(htaskId, type);// 对用历史完成后的任务ID
				list.addAll(taskComments);
			}
		}
		return list;
	}

	@Override
	public <T> void complete(T t, String processDefinitionKey, String assignee) throws Exception {
		ProcessInstanceQuery processInstanceQuery = runtimeService.createProcessInstanceQuery();
		TaskQuery taskQuery = taskService.createTaskQuery().processDefinitionKey(processDefinitionKey).taskAssignee(assignee);

		Class<? extends Object> clazz = t.getClass();
		Method getId = clazz.getMethod("getId");
		Integer id = (Integer) getId.invoke(t);
		String businessKey = clazz.getSimpleName() + "." + id;
		// 查询流程实例
		ProcessInstance processInstance = processInstanceQuery.processInstanceBusinessKey(businessKey).singleResult();
		if (processInstance != null) {
			// 查询任务
			String processInstanceId = processInstance.getProcessInstanceId();
			Task task = taskQuery.processInstanceId(processInstanceId).singleResult();
			String taskId = task.getId();
			// 添加批注信息
			Method getCommentInfo = clazz.getMethod("getCommentInfo");
			String message = (String) getCommentInfo.invoke(t);
			taskService.addComment(taskId, processInstanceId, ActivitiCommentCst.TYPE_LEAVE_COMMENT, message);
			// 完成任务
			taskService.complete(taskId);
		}
	}
}
