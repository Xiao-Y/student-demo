package org.billow.service.approval;

import java.util.List;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.billow.api.approval.ApprovalLeaveService;
import org.billow.model.expand.LeaveDto;
import org.billow.utils.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;

@Service
public class ApprovalLeaveServiceImpl implements ApprovalLeaveService {

	@Autowired
	private TaskService taskService;

	@Autowired
	private RuntimeService runtimeService;

	@Override
	public PageInfo<Task> findApprovalLeave(LeaveDto leaveDto) {
		String processDefinitionKey = "QingJia:4:35004";
		String assignee = "employee";
		TaskQuery taskQuery = taskService.createTaskQuery().processDefinitionId(processDefinitionKey)
				.taskAssignee(assignee);
		long count = taskQuery.count();
		PageInfo<Task> pageInfo = PageHelper.getPageInfo(count);
		List<Task> listPage = taskQuery.listPage(pageInfo.getFirstPage(), pageInfo.getPageSize());
		pageInfo.setList(listPage);
		return pageInfo;
	}

}
