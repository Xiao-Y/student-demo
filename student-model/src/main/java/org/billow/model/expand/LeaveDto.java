package org.billow.model.expand;

import java.util.Map;

import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.billow.model.domain.LeaveBase;

/**
 * 请假实体对象
 * 
 * @author XiaoY
 * @date: 2017年5月28日 下午3:49:01
 */
public class LeaveDto extends LeaveBase {

	private static final long serialVersionUID = 3221605134094266678L;
	// 流程实例id
	private String processInstanceId;
	private UserDto userDto;
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

	public UserDto getUserDto() {
		return userDto;
	}

	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
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