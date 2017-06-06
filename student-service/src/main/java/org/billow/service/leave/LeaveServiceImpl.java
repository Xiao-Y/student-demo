package org.billow.service.leave;

import java.util.Date;

import javax.annotation.Resource;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.billow.api.leave.LeaveService;
import org.billow.dao.LeaveDao;
import org.billow.model.expand.LeaveDto;
import org.billow.model.expand.UserDto;
import org.billow.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeaveServiceImpl extends BaseServiceImpl<LeaveDto> implements LeaveService {

	private LeaveDao leaveDao;

	@Resource
	public void setLeaveDao(LeaveDao leaveDao) {
		this.leaveDao = leaveDao;
		super.setBaseDao(leaveDao);
	}

	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private TaskService taskService;

	@Override
	public ProcessInstance saveLeave(LeaveDto leave) throws Exception {
		UserDto userDto = leave.getUserDto();
		leave.setApplyTime(new Date());
		leave.setUserId(userDto.getUserId());
		int id = leaveDao.insert(leave);
		String processDefinitionKey = "QingJia";
		// 业务主键
		String businessKey = LeaveDto.class.getSimpleName() + "." + id;
		// 启动流程实例
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey, businessKey);
		String processInstanceId = processInstance.getProcessInstanceId();
		// 查询任务
		Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
		// 添加保存流程变量
		taskService.addComment(task.getId(), processInstanceId, "businessKey", businessKey);
		return processInstance;
	}

}
