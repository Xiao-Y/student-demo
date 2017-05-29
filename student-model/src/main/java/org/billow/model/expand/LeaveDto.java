package org.billow.model.expand;

import org.billow.model.domain.LeaveBase;

/**
 * 请假实体对象
 * 
 * @author XiaoY
 * @date: 2017年5月28日 下午3:49:01
 */
public class LeaveDto extends LeaveBase {

	private static final long serialVersionUID = 3221605134094266678L;

	// 流程实例id
	private String processInstanceId;

	/**
	 * 流程实例id
	 * 
	 * @return
	 * @author XiaoY
	 * @date: 2017年5月28日 下午5:16:41
	 */
	public String getProcessInstanceId() {
		return processInstanceId;
	}

	/**
	 * 流程实例id
	 * 
	 * @param processInstanceId
	 * @author XiaoY
	 * @date: 2017年5月28日 下午5:16:44
	 */
	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

}