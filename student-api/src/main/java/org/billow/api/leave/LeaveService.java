package org.billow.api.leave;

import org.activiti.engine.runtime.ProcessInstance;
import org.billow.api.base.BaseService;
import org.billow.model.expand.LeaveDto;

/**
 * 申请管理
 * 
 * @author XiaoY
 * @date: 2017年5月28日 下午6:58:31
 */
public interface LeaveService extends BaseService<LeaveDto> {

	/**
	 * 保存请假申请，启动流程实例
	 * 
	 * @param leave
	 * @throws Exception
	 * @author XiaoY
	 * @date: 2017年5月28日 下午7:00:46
	 */
	ProcessInstance saveLeave(LeaveDto leave) throws Exception;
}
