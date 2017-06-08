package org.billow.service.leave;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.billow.api.leave.LeaveService;
import org.billow.api.workflow.WorkFlowService;
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
	private WorkFlowService workFlowService;

	@Override
	public ProcessInstance saveLeave(LeaveDto leave) throws Exception {
		UserDto userDto = leave.getUserDto();
		leave.setApplyTime(new Date());
		leave.setUserId(userDto.getUserId());
		leaveDao.insert(leave);
		// 业务主键
		String businessKey = LeaveDto.class.getSimpleName() + "." + leave.getId();
		String processDefinitionKey = "QingJia";
		// 启动流程实例
		ProcessInstance processInstance = workFlowService.startProcessInstanceByKey(processDefinitionKey, businessKey);
		String processInstanceId = processInstance.getProcessInstanceId();
		// 查询任务
		Task task = workFlowService.findTaskByProcessInstanceId(processInstanceId);
		// 保存批注信息
		workFlowService.addComment(task.getId(), processInstanceId, "businessKey", businessKey);
		return processInstance;
	}

	@Override
	public LeaveDto findLeaveDto(LeaveDto leave) throws Exception {
		LeaveDto leaveDto = leaveDao.selectByPrimaryKey(leave.getId());
		if (leaveDto != null) {
			List<Comment> comments = workFlowService.findCommentByProcessInstanceId(leave.getProcessInstanceId(), leave.getType());
			leaveDto.setComments(comments);
		}
		return leaveDto;
	}

}
