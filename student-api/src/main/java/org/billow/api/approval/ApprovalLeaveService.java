package org.billow.api.approval;

import org.billow.model.expand.LeaveDto;

import com.github.pagehelper.PageInfo;

/**
 * 请假审批
 * 
 * @author XiaoY
 * @date: 2017年5月28日 下午10:19:28
 */
public interface ApprovalLeaveService {

	/**
	 * 查询个人任务（要审批的请假）
	 * 
	 * @param leaveDto
	 * @return
	 * @author XiaoY
	 * @date: 2017年5月28日 下午10:21:45
	 */
	PageInfo<LeaveDto> findApprovalLeave(LeaveDto leaveDto);

	/**
	 * 保存请假审批
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @param leave
	 * 
	 * @date 2017年6月8日 下午12:20:44
	 */
	void saveLeaveApplyApp(LeaveDto leave) throws Exception;

}
