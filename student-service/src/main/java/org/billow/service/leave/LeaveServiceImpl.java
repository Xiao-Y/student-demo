package org.billow.service.leave;

import java.util.Date;

import org.billow.api.leave.LeaveService;
import org.billow.model.custom.LeaveDto;
import org.springframework.stereotype.Service;

@Service
public class LeaveServiceImpl implements LeaveService {

	@Override
	public void saveLeave(LeaveDto leave) throws Exception {
		leave.setApplyTime(new Date());
	}

}
