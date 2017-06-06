package org.billow.dao;

import java.util.List;

import org.billow.dao.base.BaseDao;
import org.billow.model.expand.LeaveDto;

public interface LeaveDao extends BaseDao<LeaveDto> {

	List<LeaveDto> selectAll(LeaveDto leaveDto);

}