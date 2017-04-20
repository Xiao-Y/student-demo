package org.billow.activiti;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.List;

import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.Model;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.billow.utils.bean.BeanUtils;
import org.billow.utils.date.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ActivitiTest {

	private static final Logger logger = Logger.getLogger(ActivitiTest.class);

	private ClassPathXmlApplicationContext ctx = null;
	private RuntimeService runtimeService;
	private RepositoryService repositoryService;
	private TaskService taskService;
	private HistoryService historyService;

	// company start
	// String modelId = "15007";
	// company end
	// home start
	String modelId = "1";

	// home end

	@Before
	public void init() {
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		try {
			runtimeService = BeanUtils.getBean("runtimeService");
			repositoryService = BeanUtils.getBean("repositoryService");
			taskService = BeanUtils.getBean("taskService");
			historyService = BeanUtils.getBean("historyService");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据Model部署流程
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @date 2017年4月19日 下午5:46:59
	 */
	@Test
	public void createDeploymentTest() {
		logger.info("=================================开始发布=================================");
		Model model = repositoryService.getModel(modelId);
		logger.info("Model:" + model.getId() + "," + model.getName() + "," + model.getMetaInfo());

		// 流程xml文件的名称
		String processName = model.getName() + ".bpmn20.xml";

		byte[] source = repositoryService.getModelEditorSource(model.getId());
		try {
			JsonNode jsonNode = new ObjectMapper().readTree(source);
			BpmnModel bpmnModel = new BpmnJsonConverter().convertToBpmnModel(jsonNode);
			byte[] bpmnBytes = new BpmnXMLConverter().convertToXML(bpmnModel);

			DeploymentBuilder createDeployment = repositoryService.createDeployment();
			createDeployment.name(model.getName());
			createDeployment.addString(processName, new String(bpmnBytes, "UTF-8"));
			createDeployment.deploy();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.info("=================================开始完成=================================");
	}

	/**
	 * 启动流程
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @date 2017年4月19日 下午6:12:11
	 */
	@Test
	public void startProcessInstance() {
		logger.info("=================================启动流程实例=================================");
		// 使用流程定义的key启动流程实例，key对应hellworld.bpmn文件中id的属性值，使用key值启动，默认是按照最新版本的流程定义启动
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("QingJiaProcess");
		logger.info("流程实例ID:" + processInstance.getId());// 流程实例ID:30001
		logger.info("流程定义ID:" + processInstance.getProcessDefinitionId());// 流程定义ID:QingJiaProcess:2:27504
		logger.info("=================================流程实例完成=================================");
	}

	/**
	 * 查询任务
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @date 2017年4月19日 下午6:30:18
	 */
	@Test
	public void findTask() {
		logger.info("=================================开始查询=================================");
		List<Task> list = taskService.createTaskQuery().list();
		for (Task task : list) {
			logger.info("任务ID:" + task.getId());
			logger.info("任务Key:" + task.getTaskDefinitionKey());
			logger.info("任务名称:" + task.getName());
			logger.info("任务的创建时间" + new DateTime(task.getCreateTime(), DateTime.YEAR_TO_SECOND));
			logger.info("任务的办理人:" + task.getAssignee());
			logger.info("流程实例ID:" + task.getProcessInstanceId());
			logger.info("执行对象ID:" + task.getExecutionId());
			logger.info("流程定义ID:" + task.getProcessDefinitionId());
			logger.info("###################################################");
		}
		logger.info("=================================结束查询=================================");
	}

	/**
	 * 查询个人任务
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @date 2017年4月19日 下午6:35:47
	 */
	@Test
	public void findMyTask() {
		logger.info("=================================开始查询=================================");
		String taskAssignee = "张三";
		List<Task> list = taskService.createTaskQuery().taskAssignee(taskAssignee).list();
		for (Task task : list) {
			logger.info("任务ID:" + task.getId());
			logger.info("任务Key:" + task.getTaskDefinitionKey());
			logger.info("任务名称:" + task.getName());
			logger.info("任务的创建时间" + new DateTime(task.getCreateTime(), DateTime.YEAR_TO_SECOND));
			logger.info("任务的办理人:" + task.getAssignee());
			logger.info("流程实例ID:" + task.getProcessInstanceId());
			logger.info("执行对象ID:" + task.getExecutionId());
			logger.info("流程定义ID:" + task.getProcessDefinitionId());
			logger.info("###################################################");
		}
		logger.info("=================================结束查询=================================");
	}
}
