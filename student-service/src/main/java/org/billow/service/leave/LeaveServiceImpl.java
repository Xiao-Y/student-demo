package org.billow.service.leave;

import java.util.Date;

import javax.annotation.Resource;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.billow.api.leave.LeaveService;
import org.billow.dao.LeaveDao;
import org.billow.model.expand.LeaveDto;
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

	@Override
	public ProcessInstance saveLeave(LeaveDto leave) throws Exception {
		leave.setApplyTime(new Date());
		int id = leaveDao.insert(leave);
		String processDefinitionKey = "QingJia";
		String businessKey = id + "";
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinitionKey, businessKey);
		return processInstance;
	}

}
