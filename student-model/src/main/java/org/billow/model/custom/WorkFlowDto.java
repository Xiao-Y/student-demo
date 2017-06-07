package org.billow.model.custom;

import java.io.Serializable;
import java.util.Map;

import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

/**
 * 需要操作流程图的要继承
 * 
 * @author liuyongtao
 * 
 * @date 2017年6月7日 上午9:11:03
 */
public class WorkFlowDto implements Serializable {

	private static final long serialVersionUID = -8464617402822701998L;

	// 流程实例id
	private String processInstanceId;
	// 流程任务
	private Task task;

	private Map<String, Object> variables;

	// 运行中的流程实例
	private ProcessInstance processInstance;

	// 历史的流程实例
	private HistoricProcessInstance historicProcessInstance;

	// 流程定义
	private ProcessDefinition processDefinition;

	/**
	 * 流程实例id
	 * 
	 * @return
	 * @author XiaoY
	 * @date: 2017年5月28日 下午5:16:41
	 */
	public String getProcessInstanceId() {
		return processInstanceId;
	}

	/**
	 * 流程实例id
	 * 
	 * @param processInstanceId
	 * @author XiaoY
	 * @date: 2017年5月28日 下午5:16:44
	 */
	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public Map<String, Object> getVariables() {
		return variables;
	}

	public void setVariables(Map<String, Object> variables) {
		this.variables = variables;
	}

	public ProcessInstance getProcessInstance() {
		return processInstance;
	}

	public void setProcessInstance(ProcessInstance processInstance) {
		this.processInstance = processInstance;
	}

	public HistoricProcessInstance getHistoricProcessInstance() {
		return historicProcessInstance;
	}

	public void setHistoricProcessInstance(HistoricProcessInstance historicProcessInstance) {
		this.historicProcessInstance = historicProcessInstance;
	}

	public ProcessDefinition getProcessDefinition() {
		return processDefinition;
	}

	public void setProcessDefinition(ProcessDefinition processDefinition) {
		this.processDefinition = processDefinition;
	}
}
