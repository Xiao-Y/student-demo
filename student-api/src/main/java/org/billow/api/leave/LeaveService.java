package org.billow.api.leave;

import org.billow.model.custom.LeaveDto;

/**
 * 申请管理
 * 
 * @author XiaoY
 * @date: 2017年5月28日 下午6:58:31
 */
public interface LeaveService {

	/**
	 * 保存请假申请，启动流程实例
	 * 
	 * @param leave
	 * @throws Exception
	 * @author XiaoY
	 * @date: 2017年5月28日 下午7:00:46
	 */
	void saveLeave(LeaveDto leave) throws Exception;

}
