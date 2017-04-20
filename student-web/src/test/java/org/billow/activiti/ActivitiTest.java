package org.billow.activiti;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.Model;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.task.Task;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.billow.utils.ToolsUtils;
import org.billow.utils.bean.BeanUtils;
import org.billow.utils.date.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

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
			ObjectNode objectNode = (ObjectNode) new ObjectMapper().readTree(source);
			BpmnModel bpmnModel = new BpmnJsonConverter().convertToBpmnModel(objectNode);
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
		String processKey = "QingJia";// act_re_procdef表中的key
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processKey);
		logger.info("流程实例ID:" + processInstance.getId());// 流程实例ID:30001/5001
		logger.info("流程定义ID:" + processInstance.getProcessDefinitionId());// 流程定义ID:QingJiaProcess:2:27504/QingJia:1:2504
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
		String taskAssignee = "李四";
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
		// 任务ID:5004
		// 任务Key:QingJiaShengQin
		// 任务名称:请假申请
		// 任务的创建时间2017-04-20 21:30:00
		// 任务的办理人:张三
		// 流程实例ID:500
		// 执行对象ID:5001
		// 流程定义ID:QingJia:1:2504
		// ///////////////////////////////////////
		// 任务ID:7502
		// 任务Key:BuMenJingLi
		// 任务名称:审核【部门经理】
		// 任务的创建时间2017-04-20 22:08:27
		// 任务的办理人:李四
		// 流程实例ID:5001
		// 执行对象ID:5001
		// 流程定义ID:QingJia:1:2504
		// ///////////////////////////////////////
		// 任务ID:10002
		// 任务Key:ZhongJingLi
		// 任务名称:审核【总经理】
		// 任务的创建时间2017-04-20 22:30:59
		// 任务的办理人:王五
		// 流程实例ID:5001
		// 执行对象ID:5001
		// 流程定义ID:QingJia:1:2504
	}

	/**
	 * 完成个人任务
	 * 
	 * @author XiaoY
	 * @date: 2017年4月20日 下午10:04:17
	 */
	@Test
	public void completeMyTask() {
		logger.info("=================================完成任务=================================");
		String taskId = "10002";
		taskService.complete(taskId);
		logger.info("=================================完成任务=================================");
	}

	/**
	 * 查询流程是否结束
	 * 
	 * @author XiaoY
	 * @date: 2017年4月20日 下午10:36:39
	 */
	@Test
	public void isProcessEnd() {
		String processInstanceId = "5001";
		ProcessInstance query = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId)
				.singleResult();
		if (query == null) {
			logger.info("=================================流程是否结束:true=================================");
		} else {
			logger.info("=================================流程是否结束:false================================");
		}
	}

	/**
	 * 查询历史
	 * 
	 * @author XiaoY
	 * @date: 2017年4月20日 下午10:46:00
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void findHistoryProcessInstance() {
		logger.info("=================================开始流程历史查询=================================");
		String processInstanceId = "5001";
		List<HistoricProcessInstance> list = historyService.createHistoricProcessInstanceQuery()
				.processInstanceId(processInstanceId).list();
		for (HistoricProcessInstance instance : list) {
			logger.info("流程定义ID:" + instance.getProcessDefinitionId());
			logger.info("流程定义Key:" + instance.getProcessDefinitionKey());
			logger.info("流程定义名称:" + instance.getProcessDefinitionName());
			logger.info("流程定义版本:" + instance.getProcessDefinitionVersion());
			logger.info("流程定义开始时间:" + new DateTime(instance.getStartTime(), DateTime.YEAR_TO_SECOND));
			logger.info("流程定义结束时间:" + new DateTime(instance.getEndTime(), DateTime.YEAR_TO_SECOND));
			logger.info("流程定义结束ID:" + instance.getStartActivityId());
			logger.info("流程定义结束ID:" + instance.getEndActivityId());
			logger.info("###################################################");
		}
		logger.info("=================================结束流程历史查询=================================");
		// 流程定义ID:QingJia:1:2504
		// 流程定义Key:QingJia
		// 流程定义名称:请假流程
		// 流程定义版本:1
		// 流程定义开始时间:2017-04-20 21:30:00
		// 流程定义结束时间:2017-04-20 22:33:29
		// 流程定义结束ID:start
		// 流程定义结束ID:end
	}

	/**
	 * 查看流程图片
	 * 
	 * @author XiaoY
	 * @date: 2017年4月20日 下午10:16:04
	 */
	@Test
	public void viewPic() {
		logger.info("=================================生成流程图片=================================");
		String deploymentId = "2501";
		// 定义图片资源名称
		String resourceName = "";
		List<String> list = repositoryService.getDeploymentResourceNames(deploymentId);
		if (ToolsUtils.isNotEmpty(list)) {
			for (String s : list) {
				if (s.indexOf(".png") >= 0) {
					resourceName = s;
				}
			}
		}
		InputStream input = repositoryService.getResourceAsStream(deploymentId, resourceName);
		File out = new File("D:/" + resourceName);
		try {
			FileUtils.copyInputStreamToFile(input, out);
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.info("=================================生成完毕=================================");
	}
}
