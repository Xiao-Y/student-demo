package org.billow.service.approval;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
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
	private LeaveDao leaveDao;

	@Override
	public PageInfo<LeaveDto> findApprovalLeave(LeaveDto leaveDto) {
		List<LeaveDto> results = new ArrayList<LeaveDto>();
		UserDto userDto = leaveDto.getUserDto();
		String processDefinitionKey = "QingJia";
		String assignee = userDto.getUserName();
		TaskQuery taskQuery = taskService.createTaskQuery().processDefinitionKey(processDefinitionKey)
				.taskAssignee(assignee);
		long count = taskQuery.count();
		PageInfo<LeaveDto> pageInfo = PageHelper.getPageInfo(count);
		List<Task> tasks = taskQuery.listPage(pageInfo.getFirstPage(), pageInfo.getPageSize());
		ProcessInstanceQuery processInstanceQuery = runtimeService.createProcessInstanceQuery();
		for (Task task : tasks) {
			String processInstanceId = task.getProcessInstanceId();
			ProcessInstance processInstance = processInstanceQuery.processInstanceId(processInstanceId).singleResult();
			String businessKey = processInstance.getBusinessKey();
			if (ToolsUtils.isEmpty(businessKey)) {
				continue;
			}
			LeaveDto dto = leaveDao.selectByPrimaryKey(Integer.valueOf(businessKey));
			dto.setTask(task);
			dto.setProcessInstance(processInstance);
			String processDefinitionId = task.getProcessDefinitionId();
			dto.setProcessDefinition(getProcessDefinition(processDefinitionId));
			results.add(dto);
		}
		pageInfo.setList(results);
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
