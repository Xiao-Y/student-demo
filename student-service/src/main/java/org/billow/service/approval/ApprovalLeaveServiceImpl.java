package org.billow.service.approval;

import org.activiti.engine.repository.ProcessDefinition;
import org.billow.api.approval.ApprovalLeaveService;
import org.billow.api.workflow.WorkFlowService;
import org.billow.dao.LeaveDao;
import org.billow.model.expand.LeaveDto;
import org.billow.model.expand.UserDto;
import org.billow.utils.constant.ActivitiCst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;

@Service
public class ApprovalLeaveServiceImpl implements ApprovalLeaveService {

	@Autowired
	private LeaveDao leaveDao;
	@Autowired
	private WorkFlowService workFlowService;

	@Override
	public PageInfo<LeaveDto> findApprovalLeave(LeaveDto leaveDto) throws Exception {
		UserDto userDto = leaveDto.getUserDto();
		String userId = userDto.getUserName();
		PageInfo<LeaveDto> pageInfo = workFlowService.findTodoTaskList(userId, leaveDao);
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
		ProcessDefinition processDefinition = workFlowService.getProcessDefinition(processDefinitionId);
		return processDefinition;
	}

	@Override
	public void saveLeaveApplyApp(LeaveDto leave) throws Exception {
		if (leave.getApplyPass()) {
			leave.setStatus("3");
		} else {
			leave.setStatus("7");
		}
		leaveDao.updateByPrimaryKeySelective(leave);
		String processDefinitionKey = ActivitiCst.PROCESSDEFINITION_KEY_LEAVE;
		UserDto userDto = leave.getUserDto();
		String assignee = userDto.getUserName();
		workFlowService.complete(leave, processDefinitionKey, assignee);
	}

	@Override
	public void leaveClaim(LeaveDto leaveDto, String taskId) throws Exception {
		// 任务签收人
		String userName = leaveDto.getUserDto().getUserName();
		workFlowService.claim(taskId, userName);
		leaveDao.updateByPrimaryKeySelective(leaveDto);
	}
}
