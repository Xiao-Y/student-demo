package org.billow.service.apply;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.IdentityService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.billow.api.apply.ApplyLeaveService;
import org.billow.api.workflow.WorkFlowService;
import org.billow.dao.LeaveDao;
import org.billow.dao.base.BaseDao;
import org.billow.model.expand.LeaveDto;
import org.billow.model.expand.UserDto;
import org.billow.service.base.BaseServiceImpl;
import org.billow.utils.PageHelper;
import org.billow.utils.ToolsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;

@Service
public class ApplyLeaveServiceImpl extends BaseServiceImpl<LeaveDto> implements ApplyLeaveService {

	@Resource
	private LeaveDao leaveDao;

	@Resource
	@Override
	public void setBaseDao(BaseDao<LeaveDto> baseDao) {
		super.baseDao = this.leaveDao;
	}

	@Autowired
	private WorkFlowService workFlowService;
	@Autowired(required = false)
	private IdentityService identityService;

	@Override
	public ProcessInstance saveLeave(LeaveDto leave) throws Exception {
		UserDto userDto = leave.getUserDto();
		leave.setApplyTime(new Date());
		leave.setUserId(userDto.getUserId());
		ProcessInstance processInstance = null;
		try {
			leaveDao.insert(leave);
			// 用来设置启动流程的人员ID，引擎会自动把用户ID保存到activiti:initiator中
			identityService.setAuthenticatedUserId(userDto.getUserName());
			// 业务主键
			String businessKey = LeaveDto.class.getSimpleName() + "." + leave.getId();
			String processDefinitionKey = leave.getProcessDefinitionKey();
			// 启动流程实例
			processInstance = workFlowService.startProcessInstanceByKey(processDefinitionKey, businessKey);
			String processInstanceId = processInstance.getProcessInstanceId();
			leave.setProcessInstanceId(processInstanceId);
			leaveDao.updateByPrimaryKey(leave);
			// 查询任务
			Task task = workFlowService.findTaskByProcessInstanceId(processInstanceId);
			// 保存批注信息(保存业务主键)
			workFlowService.addComment(task.getId(), processInstanceId, "businessKey", businessKey);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			identityService.setAuthenticatedUserId(null);
		}
		return processInstance;
	}

	@Override
	public LeaveDto findLeaveDto(LeaveDto leave) throws Exception {
		LeaveDto leaveDto = leaveDao.selectByPrimaryKey(leave);
		if (leaveDto != null) {
			List<Comment> comments = workFlowService.findCommentByProcessInstanceId(leave.getProcessInstanceId(), leave.getType());
			leaveDto.setComments(comments);
		}
		return leaveDto;
	}

	@Override
	public PageInfo<LeaveDto> findLeaveList(LeaveDto leave) {
		PageHelper.startPage();
		List<LeaveDto> leavList = leaveDao.selectAll(leave);
		if (ToolsUtils.isNotEmpty(leavList)) {
			try {
				leavList = workFlowService.findTaskNodeList(leavList);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		PageInfo<LeaveDto> pages = new PageInfo<>(leavList);
		return pages;
	}

	@Override
	public void updateLeave(LeaveDto leave) throws Exception {
		leave.setApplyTime(new Date());
		leaveDao.updateByPrimaryKeySelective(leave);
		String processDefinitionKey = leave.getProcessDefinitionKey();
		UserDto userDto = leave.getUserDto();
		String assignee = userDto.getUserName();
		workFlowService.complete(leave, processDefinitionKey, assignee);
	}

	@Override
	public LeaveDto selectByPrimaryKey(Integer id) {
		return leaveDao.selectByPrimaryKey(id);
	}

	@Override
	public ProcessInstance saveLeaveFormKey(LeaveDto leave) {
		UserDto userDto = leave.getUserDto();
		leave.setApplyTime(new Date());
		leave.setUserId(userDto.getUserId());
		ProcessInstance processInstance = null;
		try {
			leaveDao.insert(leave);
			// 用来设置启动流程的人员ID，引擎会自动把用户ID保存到activiti:initiator中
			identityService.setAuthenticatedUserId(userDto.getUserName());
			// 业务主键
			String businessKey = LeaveDto.class.getSimpleName() + "." + leave.getId();
			String processDefinitionKey = leave.getProcessDefinitionKey();
			Map<String, String> properties = leave.getProperties();
			// 启动流程实例
			processInstance = workFlowService.submitStartFormData(processDefinitionKey, businessKey, properties, userDto.getUserName());
			String processInstanceId = processInstance.getProcessInstanceId();
			// 查询任务
			Task task = workFlowService.findTaskByProcessInstanceId(processInstanceId);
			// 保存批注信息
			workFlowService.addComment(task.getId(), processInstanceId, "businessKey", businessKey);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			identityService.setAuthenticatedUserId(null);
		}
		return processInstance;
	}

}
