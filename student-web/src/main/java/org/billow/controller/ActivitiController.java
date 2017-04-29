package org.billow.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * 工作流测试
 * 
 * @author liuyongtao
 * 
 * @date 2017年4月17日 下午3:41:18
 */
@Controller("/test")
@RequestMapping("/activitiController")
public class ActivitiController {

	private static final Logger logger = Logger.getLogger(ActivitiController.class);

	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private HistoryService historyService;

	@RequestMapping("/index")
	public String index() {
		return "act/activitiIndex";
	}

	@RequestMapping("/createModel")
	public String createModel() {
		return "act/createModel";
	}

	@RequestMapping("/findModelList")
	public String findModelList() {
		return "act/findModelList";
	}

	/**
	 * 创建Model
	 * 
	 * @param name
	 *            model的名称
	 * @param key
	 *            model的key
	 * @param description
	 *            简介
	 * @param request
	 * @param response
	 * @return
	 * @author XiaoY
	 * @date: 2017年4月22日 上午11:16:15
	 */
	@RequestMapping("/saveModel")
	public String saveModel(@RequestParam("name") String name, @RequestParam("key") String key,
			@RequestParam("description") String description, RedirectAttributes redirectAttributes) {
		redirectAttributes.addAttribute("name", name);
		redirectAttributes.addAttribute("key", key);
		redirectAttributes.addAttribute("description", description);
		return "redirect:/activitiController/diagram";
	}

	/**
	 * 进入流程设计器
	 * 
	 * @param name
	 *            model的名称
	 * @param key
	 *            model的key
	 * @param description
	 *            简介
	 * @param request
	 * @param response
	 * @author XiaoY
	 * @date: 2017年4月22日 上午11:16:49
	 */
	@RequestMapping("/diagram")
	public void create(@ModelAttribute("name") String name, @ModelAttribute("key") String key,
			@ModelAttribute("description") String description, HttpServletRequest request, HttpServletResponse response) {
		try {
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
			description = StringUtils.defaultString(description);
			modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, description);
			modelData.setMetaInfo(modelObjectNode.toString());
			modelData.setName(name);
			modelData.setKey(StringUtils.defaultString(key));
			repositoryService.saveModel(modelData);
			repositoryService.addModelEditorSource(modelData.getId(), editorNode.toString().getBytes("utf-8"));
			response.sendRedirect(request.getContextPath() + "/process-editor/modeler.html?modelId="
					+ modelData.getId());
		} catch (Exception e) {
			logger.error("创建模型失败：", e);
			e.printStackTrace();
		}
	}

	/**
	 * 根据Model部署流程
	 * 
	 * @param modelId
	 * @param redirectAttributes
	 * @return
	 * @author XiaoY
	 * @date: 2017年4月22日 上午11:17:26
	 */
	@RequestMapping(value = "/deploy/{modelId}")
	@ResponseBody
	public String deploy(@PathVariable("modelId") String modelId, RedirectAttributes redirectAttributes) {
		try {
			Model modelData = repositoryService.getModel(modelId);
			ObjectNode modelNode = (ObjectNode) new ObjectMapper().readTree(repositoryService
					.getModelEditorSource(modelData.getId()));
			BpmnModel model = new BpmnJsonConverter().convertToBpmnModel(modelNode);
			byte[] bpmnBytes = new BpmnXMLConverter().convertToXML(model);
			String processName = modelData.getName() + ".bpmn20.xml";
			repositoryService.createDeployment().name(modelData.getName())
					.addString(processName, new String(bpmnBytes, "UTF-8")).deploy();
		} catch (Exception e) {
			logger.error("根据模型部署流程失败：modelId=" + modelId, e);
		}
		return "deployesuccess";
	}

	/**
	 * 启动流程实例
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @param processDefinitionKey
	 * @param redirectAttributes
	 * @return
	 * 
	 * @date 2017年4月17日 下午4:20:44
	 */
	@RequestMapping(value = "/instance/{process}")
	public String startProcessInstance(@PathVariable("process") String processDefinitionKey,
			RedirectAttributes redirectAttributes) {
		ProcessInstance pi = runtimeService// 于正在执行的流程实例和执行对象相关的Service
				.startProcessInstanceByKey(processDefinitionKey);// 使用流程定义的key启动流程实例，key对应hellworld.bpmn文件中id的属性值，使用key值启动，默认是按照最新版本的流程定义启动
		System.out.println("流程实例ID:" + pi.getId());// 流程实例ID 101
		System.out.println("流程定义ID:" + pi.getProcessDefinitionId()); // 流程定义ID
		return "instancesuccess"; // HelloWorld:1:4
	}

	/**
	 * 查询个人任务
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @param assignee
	 * @param redirectAttributes
	 * 
	 * @date 2017年4月17日 下午4:30:41
	 */
	@RequestMapping(value = "/findTask/{assignee}")
	public void findMyPersonTask(@PathVariable("assignee") String assignee, RedirectAttributes redirectAttributes) {
		// String assignee = "张三";
		List<Task> list = taskService// 与正在执行的认为管理相关的Service
				.createTaskQuery()// 创建任务查询对象
				// .taskAssignee(assignee)// 指定个人认为查询，指定办理人
				.list();
		if (list != null && list.size() > 0) {
			for (Task task : list) {
				System.out.println("任务ID:" + task.getId());
				System.out.println("任务名称:" + task.getName());
				System.out.println("任务的创建时间" + task);
				System.out.println("任务的办理人:" + task.getAssignee());
				System.out.println("流程实例ID:" + task.getProcessInstanceId());
				System.out.println("执行对象ID:" + task.getExecutionId());
				System.out.println("流程定义ID:" + task.getProcessDefinitionId());
				System.out.println("#################################");
			}
		}
	}

	/**
	 * 完成任务
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @date 2017年4月17日 下午4:30:28
	 */
	@RequestMapping(value = "/complete/{taskId}")
	public String completeMyPersonTask(@PathVariable("taskId") String taskId, RedirectAttributes redirectAttributes) {
		taskService.complete(taskId);// 与正在执行的认为管理相关的Service
		System.out.println("完成任务:任务ID:" + taskId);
		return "completesuccess";
	}

	/**
	 * 导出model的xml文件
	 */
	@RequestMapping(value = "/export/{modelId}")
	public void export(@PathVariable("modelId") String modelId, HttpServletResponse response) {
		try {
			Model modelData = repositoryService.getModel(modelId);
			BpmnJsonConverter jsonConverter = new BpmnJsonConverter();
			JsonNode editorNode = new ObjectMapper()
					.readTree(repositoryService.getModelEditorSource(modelData.getId()));
			BpmnModel bpmnModel = jsonConverter.convertToBpmnModel(editorNode);
			BpmnXMLConverter xmlConverter = new BpmnXMLConverter();
			byte[] bpmnBytes = xmlConverter.convertToXML(bpmnModel);

			ByteArrayInputStream in = new ByteArrayInputStream(bpmnBytes);
			IOUtils.copy(in, response.getOutputStream());
			String filename = bpmnModel.getMainProcess().getId() + ".bpmn20.xml";
			response.setHeader("Content-Disposition", "attachment; filename=" + filename);
			response.flushBuffer();
		} catch (Exception e) {
			logger.error("导出model的xml文件失败：modelId=" + modelId, e);
		}
	}

	/**
	 * 
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @param modelId
	 * @return
	 * 
	 * @date 2017年4月17日 下午4:54:58
	 */
	@RequestMapping(value = "/delete/{modelId}")
	@ResponseBody
	public String delete(@PathVariable("modelId") String modelId) {
		repositoryService.deleteModel(modelId);
		return "deletesuccess";
	}

	/**
	 * 查询所有的流程定义
	 */
	@RequestMapping(value = "/findProcessDefinition")
	public void findProcessDefinition() {
		List<ProcessDefinition> list = repositoryService// 与流程定义和部署对象先相关的service
				.createProcessDefinitionQuery()// 创建一个流程定义的查询
				/** 指定查询条件，where条件 */
				// .deploymentId(deploymentId) //使用部署对象ID查询
				// .processDefinitionId(processDefinitionId)//使用流程定义ID查询
				// .processDefinitionNameLike(processDefinitionNameLike)//使用流程定义的名称模糊查询
				.orderByProcessDefinitionVersion().asc()// 排序
				// .orderByProcessDefinitionVersion().desc()
				/* 返回的结果集 */
				.list();// 返回一个集合列表，封装流程定义
		// .singleResult();//返回惟一结果集
		// .count();//返回结果集数量
		// .listPage(firstResult, maxResults);//分页查询

		if (list != null && list.size() > 0) {
			for (ProcessDefinition pd : list) {
				System.out.println("流程定义ID:" + pd.getId());// 流程定义的key+版本+随机生成数
				System.out.println("流程定义的名称:" + pd.getName());// 对应helloworld.bpmn文件中的name属性值
				System.out.println("流程定义的key:" + pd.getKey());// 对应helloworld.bpmn文件中的id属性值
				System.out.println("流程定义的版本:" + pd.getVersion());// 当流程定义的key值相同的相同下，版本升级，默认1
				System.out.println("资源名称bpmn文件:" + pd.getResourceName());
				System.out.println("资源名称png文件:" + pd.getDiagramResourceName());
				System.out.println("部署对象ID：" + pd.getDeploymentId());
				System.out.println("#########################################################");
			}
		}
	}

	/**
	 * 附加功能，查询最新版本的流程定义
	 */
	@RequestMapping(value = "/findLastVersionProcessDefinition")
	public void findLastVersionProcessDefinition() {
		List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery()
				.orderByProcessDefinitionVersion().asc() // 使用流程定义的版本升序排列
				.list();
		/**
		 * Map<String,ProcessDefinition> map集合的key：流程定义的key map集合的value：流程定义的对象 map集合的特点：当map集合key值相同的情况下，后一次的值将替换前一次的值
		 */
		Map<String, ProcessDefinition> map = new HashMap<String, ProcessDefinition>();
		if (list != null && list.size() > 0) {
			for (ProcessDefinition pd : list) {
				map.put(pd.getKey(), pd);
			}
		}

		List<ProcessDefinition> pdList = new ArrayList<ProcessDefinition>(map.values());
		if (pdList != null && pdList.size() > 0) {
			for (ProcessDefinition pd : pdList) {
				System.out.println("流程定义ID:" + pd.getId());// 流程定义的key+版本+随机生成数
				System.out.println("流程定义的名称:" + pd.getName());// 对应helloworld.bpmn文件中的name属性值
				System.out.println("流程定义的key:" + pd.getKey());// 对应helloworld.bpmn文件中的id属性值
				System.out.println("流程定义的版本:" + pd.getVersion());// 当流程定义的key值相同的相同下，版本升级，默认1
				System.out.println("资源名称bpmn文件:" + pd.getResourceName());
				System.out.println("资源名称png文件:" + pd.getDiagramResourceName());
				System.out.println("部署对象ID：" + pd.getDeploymentId());
				System.out.println("#########################################################");
			}
		}
	}

	/**
	 * 查看流程图
	 */
	@RequestMapping(value = "/viewPic/{deploymentId}")
	public void viewPic(@PathVariable("deploymentId") String deploymentId) throws IOException {
		// 获取图片资源名称
		List<String> list = repositoryService.getDeploymentResourceNames(deploymentId);
		// 定义图片资源名称
		String resourceName = "";
		if (list != null && list.size() > 0) {
			for (String name : list) {
				if (name.indexOf(".png") >= 0) {
					resourceName = name;
				}
			}
		}
		// 获取图片的输入流
		InputStream in = repositoryService.getResourceAsStream(deploymentId, resourceName);
		File file = new File("D:/" + resourceName);
		// 将输入流的图片写到D盘下
		FileUtils.copyInputStreamToFile(in, file);
	}

	/**
	 * 删除流程定义(删除key相同的所有不同版本的流程定义)
	 */
	@RequestMapping(value = "/delteProcessDefinitionByKey")
	public void delteProcessDefinitionByKey(@PathVariable("process") String processDefinitionKey,
			RedirectAttributes redirectAttributes) {
		// 先使用流程定义的key查询流程定义，查询出所有的版本
		List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery()
				.processDefinitionKey(processDefinitionKey)// 使用流程定义的key查询
				.list();
		// 遍历，获取每个流程定义的部署ID
		if (list != null && list.size() > 0) {
			for (ProcessDefinition pd : list) {
				// 获取部署ID
				String deploymentId = pd.getDeploymentId();
				// /*
				// * 不带级联的删除， 只能删除没有启动的流程，如果流程启动，就会抛出异常
				// */
				// processEngine.getRepositoryService().deleteDeployment(deploymentId);

				/**
				 * 级联删除 不管流程是否启动，都可以删除
				 */
				repositoryService.deleteDeployment(deploymentId, true);

			}

		}
	}

	/**
	 * 查询历史流程实例
	 */
	@RequestMapping(value = "/findHistoryProcessInstance/{processInstanceId}")
	public void findHistoryProcessInstance(@PathVariable("processInstanceId") String processInstanceId) {
		HistoricProcessInstance hpi = historyService.createHistoricProcessInstanceQuery()
				.processInstanceId(processInstanceId).singleResult();
		System.out.println(hpi.getId() + "    " + hpi.getProcessDefinitionId() + "   " + hpi.getStartTime() + "   "
				+ hpi.getDurationInMillis());
	}

	/**
	 * 查询流程状态（判断流程正在执行，还是结束）
	 */
	@RequestMapping(value = "/isProcessEnd/{processInstanceId}")
	public void isProcessEnd(@PathVariable("processInstanceId") String processInstanceId) {
		ProcessInstance pi = runtimeService// 表示正在执行的流程实例和执行对象
				.createProcessInstanceQuery()// 创建流程实例查询
				.processInstanceId(processInstanceId)// 使用流程实例ID查询
				.singleResult();
		if (pi == null) {
			System.out.println("流程已经结束");
		} else {
			System.out.println("流程没有结束");
		}
	}

	/**
	 * 设置流程变量
	 */
	@RequestMapping(value = "/setVariables/{taskId}")
	public void setVariables(@PathVariable("taskId") String taskId) {
		// 1.设置流程变量，使用基本数据类型
		taskService.setVariable(taskId, "请假天数", 7);// 与任务ID邦德
		taskService.setVariable(taskId, "请假日期", new Date());
		taskService.setVariableLocal(taskId, "请假原因", "回去探亲，一起吃个饭123");
		System.out.println("设置流程变量成功！");
	}

	/**
	 * 获取流程变量
	 */
	@RequestMapping(value = "/getVariables/{taskId}")
	public void getVariables(@PathVariable("taskId") String taskId) {
		// 1.获取流程变量，使用基本数据类型
		Integer days = (Integer) taskService.getVariable(taskId, "请假天数");
		Date date = (Date) taskService.getVariable(taskId, "请假日期");
		String reason = (String) taskService.getVariable(taskId, "请假原因");
		System.out.println("请假天数：" + days);
		System.out.println("请假日期：" + date);
		System.out.println("请假原因：" + reason);
	}

	/**
	 * 查询流程变量的历史表
	 */
	@RequestMapping(value = "/findHistoryProcessVariables")
	public void findHistoryProcessVariables() {
		List<HistoricVariableInstance> list = historyService.createHistoricVariableInstanceQuery()// 创建一个历史的流程变量查询对象
				.variableName("请假原因").list();
		if (list != null && list.size() > 0) {
			for (HistoricVariableInstance hvi : list) {
				System.out.println(hvi.getId() + "     " + hvi.getProcessInstanceId() + "   " + hvi.getVariableName()
						+ "   " + hvi.getVariableTypeName() + "    " + hvi.getValue());
				System.out.println("########################################");
			}
		}
	}
}
