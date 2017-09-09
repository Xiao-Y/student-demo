package org.billow.service.workflow;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.bpmn.diagram.ProcessDiagramGenerator;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentQuery;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ModelQuery;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.billow.api.workflow.WorkFlowService;
import org.billow.model.custom.DiagramDto;
import org.billow.utils.PageHelper;
import org.billow.utils.ToolsUtils;
import org.billow.utils.constant.ActivitiCst;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;

/**
 * 工作流程统一处理类
 * 
 * @author liuyongtao
 * 
 * @date 2017年6月7日 上午8:58:53
 */
@Service
public class WorkFlowServiceImpl implements WorkFlowService, Comparator<Comment> {

	private static final Logger logger = Logger.getLogger(WorkFlowServiceImpl.class);

	@Autowired(required = false)
	private HistoryService historyService;
	@Autowired(required = false)
	private RepositoryService repositoryService;
	@Autowired(required = false)
	private TaskService taskService;
	@Autowired(required = false)
	private RuntimeService runtimeService;
	@Autowired(required = false)
	private FormService formService;
	@Autowired(required = false)
	private IdentityService identityService;

	@Override
	public int compare(Comment o1, Comment o2) {
		return o2.getTime().compareTo(o1.getTime());
	}

	// @Override
	// public <T> List<T> findMyTaskList(List<T> list, String
	// processDefinitionKey, String assignee) throws Exception {
	// // 创建查询
	// HistoricProcessInstanceQuery historicProcessInstanceQuery =
	// historyService.createHistoricProcessInstanceQuery();
	// TaskQuery taskQuery =
	// taskService.createTaskQuery().processDefinitionKey(processDefinitionKey);//
	// .taskAssignee(assignee);
	// ProcessDefinitionQuery processDefinitionQuery =
	// repositoryService.createProcessDefinitionQuery();
	// for (int i = 0; i < list.size(); i++) {
	// T t = list.get(i);
	// Class<? extends Object> clazz = t.getClass();
	// // 拼接businessKey
	// Method getId = clazz.getMethod("getId");
	// Integer id = (Integer) getId.invoke(t);
	// String businessKey = clazz.getSimpleName() + "." + id;
	// // 查询历史流程实例（为获取流程实例Id）
	// HistoricProcessInstance historicProcessInstance =
	// historicProcessInstanceQuery.processInstanceBusinessKey(businessKey).singleResult();
	// // 设置历史流程到实体类中
	// Method setProcessInstance = clazz.getMethod("setHistoricProcessInstance",
	// HistoricProcessInstance.class);
	// setProcessInstance.invoke(t, historicProcessInstance);
	// if (historicProcessInstance != null) {
	// // 通过流程实例Id查询当前用户的任务
	// String processInstanceId = historicProcessInstance.getId();
	// // 设置历史流程Id到实体类中
	// Method setProcessInstanceId = clazz.getMethod("setProcessInstanceId",
	// String.class);
	// setProcessInstanceId.invoke(t, processInstanceId);
	// Task task =
	// taskQuery.processInstanceId(processInstanceId).singleResult();
	// // 设置任务到实体类中
	// Method setTask = clazz.getMethod("setTask", Task.class);
	// setTask.invoke(t, task);
	// // 查询流程定义
	// String processDefinitionId =
	// historicProcessInstance.getProcessDefinitionId();
	// ProcessDefinition processDefinition =
	// processDefinitionQuery.processDefinitionId(processDefinitionId).singleResult();
	// // 设置流程定义到实体类中
	// Method setProcessDefinition = clazz.getMethod("setProcessDefinition",
	// ProcessDefinition.class);
	// setProcessDefinition.invoke(t, processDefinition);
	// String taskName = "已结束";
	// if (task != null) {
	// // 1.获取当前流程的流程定义
	// ProcessDefinitionEntity pdf = (ProcessDefinitionEntity)
	// ((RepositoryServiceImpl) repositoryService)
	// .getDeployedProcessDefinition(processDefinitionId);
	// // 2.流程定义获得所有的节点
	// List<ActivityImpl> activities = pdf.getActivities();
	// // 3.根据任务获取当前流程执行ID，执行实例以及当前流程节点的ID
	// String executionId = task.getExecutionId();
	// // 3.1根据流程执行ID获取执行实例
	// ExecutionEntity execution = (ExecutionEntity)
	// runtimeService.createExecutionQuery().executionId(executionId).singleResult();
	// // 3.2从执行实例中获取当前流程节点的ID
	// String activitiId = execution.getActivityId();
	// // 4、然后循环activitiList
	// // 并判断出当前流程所处节点
	// for (ActivityImpl activityImpl : activities) {
	// if (activitiId.equals(activityImpl.getId())) {
	// // 当前任(输出某个节点的某种属性)
	// taskName = (String) activityImpl.getProperty("name");
	// break;
	// }
	// }
	// }
	// Method setTaskName = clazz.getMethod("setTaskName", String.class);
	// setTaskName.invoke(t, taskName);
	// }
	// }
	// return list;
	//
	// }
	//
	// @Override
	// public <T> T findMyTask(T t, String processDefinitionKey, String
	// assignee) throws Exception {
	// ProcessInstanceQuery processInstanceQuery =
	// runtimeService.createProcessInstanceQuery();
	// TaskQuery taskQuery =
	// taskService.createTaskQuery().processDefinitionKey(processDefinitionKey).taskAssignee(assignee);
	// ProcessDefinitionQuery processDefinitionQuery =
	// repositoryService.createProcessDefinitionQuery();
	//
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
	// String processDefinitionId = processInstance.getProcessDefinitionId();
	// ProcessDefinition processDefinition =
	// processDefinitionQuery.processDefinitionId(processDefinitionId).singleResult();
	// Method setProcessDefinition = clazz.getMethod("setProcessDefinition",
	// ProcessDefinition.class);
	// setProcessDefinition.invoke(t, processDefinition);
	// }
	// return t;
	//
	// }

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
	public void addComment(String taskId, String processInstanceId, String type, String message, String userName) {
		try {
			identityService.setAuthenticatedUserId(userName);
			taskService.addComment(taskId, processInstanceId, type, message);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			identityService.setAuthenticatedUserId(null);
		}
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
		Collections.sort(list, this);
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
			Authentication.setAuthenticatedUserId(assignee);// 添加批注人
			Method getCommentInfo = clazz.getMethod("getCommentInfo");
			String message = (String) getCommentInfo.invoke(t);
			if (ToolsUtils.isNotEmpty(message)) {
				taskService.addComment(taskId, processInstanceId, ActivitiCst.TYPE_LEAVE_COMMENT, message);
			}
			// 完成任务
			// Method getutcome = clazz.getMethod("getOutcome");
			// String outcome = (String) getutcome.invoke(t);
			Method getApplyPass = clazz.getMethod("getApplyPass");
			boolean applyPass = (boolean) getApplyPass.invoke(t);
			Map<String, Object> map = new HashMap<>();
			map.put("applyPass", applyPass);
			taskService.complete(taskId, map);
		}
	}

	@Override
	public <T> void complete(T t, String processDefinitionKey, String assignee, Map<String, Object> variables) throws Exception {
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
			Authentication.setAuthenticatedUserId(assignee);// 添加批注人
			Method getCommentInfo = clazz.getMethod("getCommentInfo");
			String message = (String) getCommentInfo.invoke(t);
			if (ToolsUtils.isNotEmpty(message)) {
				this.addComment(taskId, processInstanceId, ActivitiCst.TYPE_LEAVE_COMMENT, message);
			}
			// 完成任务
			taskService.complete(taskId, variables);
		}
	}

	@Override
	public List<String> getOutGoingTransNames(String taskId) throws Exception {
		List<String> transNames = new ArrayList<>();
		// 通过任务Id获取任务对象
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		if (task == null) {
			throw new Exception("没有获取到任务Id为" + taskId + "的任务！");
		}
		String processDefinitionId = task.getProcessDefinitionId();
		// 获取流程定义的实体类
		ProcessDefinitionEntity pde = (ProcessDefinitionEntity) repositoryService.getProcessDefinition(processDefinitionId);
		// 获取流程实例
		String processInstanceId = task.getProcessInstanceId();
		ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
		// 获取前活动的ID
		String activityId = processInstance.getActivityId();
		// 通过当前活动的Id获取对应的活动对象
		ActivityImpl activityImpl = pde.findActivity(activityId);
		// 通过活动对象找当前活动的所有出口
		List<PvmTransition> transitions = activityImpl.getOutgoingTransitions();
		// 提取所有出口的名称，封装成集合
		for (PvmTransition pt : transitions) {
			String name = (String) pt.getProperty("name");
			if (ToolsUtils.isNotEmpty(name)) {
				transNames.add(name);
			}
		}
		if (ToolsUtils.isEmpty(transNames)) {
			transNames.add("提交");// 默认
		}
		return transNames;
	}

	@Override
	public <T> List<T> findTaskNodeList(List<T> list) throws Exception {
		for (int i = 0; i < list.size(); i++) {
			findTaskNode(list.get(i));
		}
		return list;
	}

	public <T> void findTaskNode(T t) throws Exception {
		// 创建查询
		HistoricProcessInstanceQuery historicProcessInstanceQuery = historyService.createHistoricProcessInstanceQuery();
		ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
		TaskQuery taskQuery = taskService.createTaskQuery();
		Class<? extends Object> clazz = t.getClass();
		Method getId = clazz.getMethod("getId");
		Integer id = (Integer) getId.invoke(t);
		String businessKey = clazz.getSimpleName() + "." + id;
		// 查询历史流程实例（为获取流程实例Id）
		HistoricProcessInstance historicProcessInstance = historicProcessInstanceQuery.processInstanceBusinessKey(businessKey).singleResult();
		String processInstanceId = historicProcessInstance.getId();
		// 设置流程实例
		Method setProcessInstanceId = clazz.getMethod("setProcessInstanceId", String.class);
		setProcessInstanceId.invoke(t, processInstanceId);
		// 通过流程定论Id,查询流程定义
		String processDefinitionId = historicProcessInstance.getProcessDefinitionId();
		ProcessDefinition processDefinition = processDefinitionQuery.processDefinitionId(processDefinitionId).singleResult();
		// 设置流程定义到实体类中
		Method setProcessDefinition = clazz.getMethod("setProcessDefinition", ProcessDefinition.class);
		setProcessDefinition.invoke(t, processDefinition);
		// 通过流程实例Id查询出当前活动的任务
		Task task = taskQuery.processInstanceId(processInstanceId).active().singleResult();
		// 设置任务到实体类中
		Method setTask = clazz.getMethod("setTask", Task.class);
		setTask.invoke(t, task);
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

	@SuppressWarnings("unchecked")
	@Override
	public <T, C> PageInfo<T> findTodoTaskList(String userId, C s) throws Exception {
		List<T> results = new ArrayList<>();
		// 根据当前人的ID查询
		TaskQuery taskQuery = taskService.createTaskQuery().taskCandidateOrAssigned(userId);
		ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
		// 计算分布数据
		PageInfo<T> pageInfo = PageHelper.getPageInfo(taskQuery.count());
		// 分布查询
		List<Task> tasks = taskQuery.listPage(pageInfo.getFirstPage(), pageInfo.getPageSize());
		for (Task task : tasks) {
			// 通过流程实例Id查询出活动的流程实例
			String processInstanceId = task.getProcessInstanceId();
			ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).active()
					.singleResult();
			// 获取业务主键
			String businessKey = processInstance.getBusinessKey();
			if (ToolsUtils.isEmpty(businessKey)) {
				continue;
			}
			// 形式： LeaveDto.12
			String[] split = businessKey.split("\\.");
			Integer id = Integer.valueOf(split[1]);
			// 通过主键查询
			Class<? extends Object> serviceClazz = s.getClass();
			Method selectByPrimaryKey = serviceClazz.getMethod("selectByPrimaryKey", Integer.class);
			T t = (T) selectByPrimaryKey.invoke(s, id);

			// 设置任务到实体类中
			Class<? extends Object> clazz = t.getClass();
			Method setTask = clazz.getMethod("setTask", Task.class);
			setTask.invoke(t, task);

			// 设置流程实例
			Method setProcessInstanceId = clazz.getMethod("setProcessInstanceId", String.class);
			setProcessInstanceId.invoke(t, processInstanceId);

			String processDefinitionId = task.getProcessDefinitionId();
			ProcessDefinition processDefinition = processDefinitionQuery.processDefinitionId(processDefinitionId).singleResult();
			// 设置流程定义到实体类中
			Method setProcessDefinition = clazz.getMethod("setProcessDefinition", ProcessDefinition.class);
			setProcessDefinition.invoke(t, processDefinition);

			// 查询任务当前结点
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
			String taskName = "已结束";
			for (ActivityImpl activityImpl : activities) {
				if (activitiId.equals(activityImpl.getId())) {
					// 当前任(输出某个节点的某种属性)
					taskName = (String) activityImpl.getProperty("name");
					break;
				}
			}
			Method setTaskName = clazz.getMethod("setTaskName", String.class);
			setTaskName.invoke(t, taskName);
			results.add(t);
		}
		pageInfo.setList(results);
		return pageInfo;
	}

	@Override
	public void claim(String taskId, String userName) {
		taskService.claim(taskId, userName);
	}

	@Override
	public Object getRenderedStartForm(String processDefinitionKey) {
		ProcessDefinition processDefinition = this.getProcessDefinitionLatestVersion(processDefinitionKey);
		return formService.getRenderedStartForm(processDefinition.getId());
	}

	@Override
	public ProcessDefinition getProcessDefinitionLatestVersion(String processDefinitionKey) {
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionKey(processDefinitionKey)
				.latestVersion().singleResult();
		return processDefinition;
	}

	@Override
	public ProcessInstance submitStartFormData(String processDefinitionKey, String businessKey, Map<String, String> properties, String userName) {
		ProcessInstance processInstance = null;
		try {
			identityService.setAuthenticatedUserId(userName);
			ProcessDefinition processDefinition = this.getProcessDefinitionLatestVersion(processDefinitionKey);
			processInstance = formService.submitStartFormData(processDefinition.getId(), businessKey, properties);
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
			throw e;
		} finally {
			identityService.setAuthenticatedUserId(null);
		}
		return processInstance;
	}

	@Override
	public Object getRenderedTaskForm(String processInstanceId) {
		Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
		return formService.getRenderedTaskForm(task.getId());
	}

	@Override
	public void submitTaskFormData(String taskId, Map<String, String> properties, String userName) {
		try {
			identityService.setAuthenticatedUserId(userName);
			formService.submitTaskFormData(taskId, properties);
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
			throw e;
		} finally {
			identityService.setAuthenticatedUserId(null);
		}
	}

	@Override
	public PageInfo<Model> getModel() {
		ModelQuery createModelQuery = repositoryService.createModelQuery();
		long count = createModelQuery.count();
		PageInfo<Model> pageInfo = PageHelper.getPageInfo(count);
		List<Model> list = createModelQuery.orderByLastUpdateTime().desc().listPage(pageInfo.getFirstPage(), pageInfo.getPageSize());
		DeploymentQuery deploymentQuery = repositoryService.createDeploymentQuery();
		for (Model m : list) {
			List<Deployment> deploymentList = deploymentQuery.deploymentName(m.getName()).orderByDeploymenTime().desc().list();
			if (ToolsUtils.isNotEmpty(deploymentList)) {
				Deployment deployment = deploymentList.get(0);
				m.setDeploymentId(deployment.getId());
			}
		}
		pageInfo.setList(list);
		return pageInfo;
	}

	@Override
	public Model createModel(DiagramDto diagram) throws Exception {
		String key = diagram.getKey();
		String name = diagram.getName();
		String description = diagram.getDescription();

		ObjectMapper objectMapper = new ObjectMapper();

		ObjectNode editorNode = objectMapper.createObjectNode();
		editorNode.put("id", "canvas");
		editorNode.put("resourceId", "canvas");

		ObjectNode stencilSetNode = objectMapper.createObjectNode();
		stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
		editorNode.put("stencilset", stencilSetNode);

		Model modelData = repositoryService.newModel();

		ObjectNode modelObjectNode = objectMapper.createObjectNode();
		modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, name);
		modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);
		modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, StringUtils.defaultString(description));

		modelData.setMetaInfo(modelObjectNode.toString());
		modelData.setName(name);
		modelData.setKey(StringUtils.defaultString(key));
		repositoryService.saveModel(modelData);

		repositoryService.addModelEditorSource(modelData.getId(), editorNode.toString().getBytes("utf-8"));
		return modelData;
	}

	@Override
	public byte[] viewPic(String modelId) {
		return repositoryService.getModelEditorSourceExtra(modelId);
	}

	@Override
	public byte[] viewPicDepId(String deploymentId, String resourceType) throws IOException {
		String resourceName = "";
		List<String> names = repositoryService.getDeploymentResourceNames(deploymentId);
		if (ToolsUtils.isNotEmpty(names)) {
			for (String name : names) {
				if ("image".equals(resourceType) && name.indexOf(".png") > -1) {
					resourceName = name;
				} else if ("xml".equals(resourceType) && name.indexOf(".xml") > -1) {
					resourceName = name;
				}
			}
		}
		InputStream inputStream = repositoryService.getResourceAsStream(deploymentId, resourceName);
		return IOUtils.toByteArray(inputStream);
	}

	@Override
	public void deleteModel(String modelId) throws Exception {
		repositoryService.deleteModel(modelId);
	}

	@Override
	public PageInfo<ProcessDefinition> queryProcDefList() {
		PageHelper.startPage();
		ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery();
		long count = query.count();
		PageInfo<ProcessDefinition> pageInfo = PageHelper.getPageInfo(count);
		List<ProcessDefinition> listPage = query.orderByProcessDefinitionKey().desc().listPage(pageInfo.getFirstPage(), pageInfo.getPageSize());
		pageInfo.setList(listPage);
		return pageInfo;
	}

	@Override
	public PageInfo<Deployment> queryDeployList() {
		PageHelper.startPage();
		DeploymentQuery query = repositoryService.createDeploymentQuery();
		long count = query.count();
		PageInfo<Deployment> pageInfo = PageHelper.getPageInfo(count);
		List<Deployment> list = query.orderByDeploymenTime().desc().listPage(pageInfo.getFirstPage(), pageInfo.getPageSize());
		pageInfo.setList(list);
		return pageInfo;
	}
}
