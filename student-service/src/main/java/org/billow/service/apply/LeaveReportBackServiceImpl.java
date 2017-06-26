package org.billow.service.apply;

import org.apache.log4j.Logger;

import java.util.Date;

import org.activiti.engine.delegate.DelegateTask;
import org.billow.api.apply.LeaveReportBackService;
import org.billow.dao.LeaveDao;
import org.billow.model.expand.LeaveDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LeaveReportBackServiceImpl implements LeaveReportBackService {

	private static final Logger logger = Logger.getLogger(LeaveReportBackServiceImpl.class);

	private static final long serialVersionUID = 760138116203734293L;

	@Autowired
	private LeaveDao leaveDao;

	@Override
	public void notify(DelegateTask delegateTask) {
		Integer id = (Integer) delegateTask.getVariable("id");
		try {
			LeaveDto dto = new LeaveDto();
			dto.setId(id);
			LeaveDto leaveDto = leaveDao.selectByPrimaryKey(dto);
			String status = (String) delegateTask.getVariable("status");
			Date startTime = (Date) delegateTask.getVariable("startTime");
			Date endTime = (Date) delegateTask.getVariable("endTime");
			leaveDto.setStatus(status);
			leaveDto.setStartTime(startTime);
			leaveDto.setEndTime(endTime);
			leaveDao.updateByPrimaryKeySelective(leaveDto);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("销假流程出现了异常：" + e);
		}
		logger.info("===========================销假流程结束============================");
	}
}
