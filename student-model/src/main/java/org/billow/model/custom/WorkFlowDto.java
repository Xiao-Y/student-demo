package org.billow.model.custom;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
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
	// 流程变量
	private Map<String, Object> variables;
	// 运行中的流程实例
	private ProcessInstance processInstance;
	// 历史的流程实例
	private HistoricProcessInstance historicProcessInstance;
	// 流程定义
	private ProcessDefinition processDefinition;
	// 批注信息
	private List<Comment> comments;
	// 批注内容
	private String commentInfo;
	// 任务名称
	private String taskName;
	// 批注信息表中type的值
	private String commentType;
	// 任务Id
	private String taskId;
	// 流程图中连线的变量
	private String outcome;
	// true-同意,false-驳回
	private boolean applyPass;
	// 流程部署key
	private String processDefinitionKey;

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

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public String getCommentInfo() {
		return commentInfo;
	}

	public void setCommentInfo(String commentInfo) {
		this.commentInfo = commentInfo;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getCommentType() {
		return commentType;
	}

	public void setCommentType(String commentType) {
		this.commentType = commentType;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getOutcome() {
		return outcome;
	}

	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}

	public boolean getApplyPass() {
		return applyPass;
	}

	public void setApplyPass(boolean applyPass) {
		this.applyPass = applyPass;
	}

	public String getProcessDefinitionKey() {
		return processDefinitionKey;
	}

	public void setProcessDefinitionKey(String processDefinitionKey) {
		this.processDefinitionKey = processDefinitionKey;
	}

}
