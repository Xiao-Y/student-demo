package org.billow.service.approval;

import java.util.List;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.billow.api.approval.ApprovalLeaveService;
import org.billow.dao.LeaveDao;
import org.billow.model.expand.LeaveDto;
import org.billow.model.expand.UserDto;
import org.billow.utils.PageHelper;
import org.billow.utils.ToolsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;

@Service
public class ApprovalLeaveServiceImpl implements ApprovalLeaveService {

	@Autowired
	private TaskService taskService;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private HistoryService historyService;
	@Autowired
	private LeaveDao leaveDao;

	@Override
	public PageInfo<LeaveDto> findApprovalLeave(LeaveDto leaveDto) {
		PageHelper.startPage();
		List<LeaveDto> leavList = leaveDao.selectAll(leaveDto);
		if (ToolsUtils.isNotEmpty(leavList)) {
			String processDefinitionKey = "QingJia";
			UserDto userDto = leaveDto.getUserDto();
			ProcessInstanceQuery processInstanceQuery = runtimeService.createProcessInstanceQuery();
			TaskQuery taskQuery = taskService.createTaskQuery().processDefinitionKey(processDefinitionKey)
					.taskAssignee(userDto.getUserName());
			ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
			for (LeaveDto leave : leavList) {
				// 查询流程实例
				String businessKey = LeaveDto.class.getSimpleName() + "." + leave.getId();
				ProcessInstance processInstance = processInstanceQuery.processInstanceBusinessKey(businessKey)
						.singleResult();
				leave.setProcessInstance(processInstance);
				if (processInstance != null) {
					// 查询任务
					String processInstanceId = processInstance.getProcessInstanceId();
					Task task = taskQuery.processInstanceId(processInstanceId).singleResult();
					leave.setTask(task);
					// 查询流程定义
					String processDefinitionId = processInstance.getProcessDefinitionId();
					ProcessDefinition processDefinition = processDefinitionQuery.processDefinitionId(
							processDefinitionId).singleResult();
					leave.setProcessDefinition(processDefinition);
				}
			}
		}
		PageInfo<LeaveDto> pageInfo = new PageInfo<>(leavList);
		return pageInfo;
	}

	/**
	 * 查询流程定义对象
	 * 
	 * @param processDefinitionId
	 *            流程定义ID
	 * @return
	 */
	protected ProcessDefinition getProcessDefinition(String processDefinitionId) {
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
				.processDefinitionId(processDefinitionId).singleResult();
		return processDefinition;
	}
}
