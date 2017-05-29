package org.billow.model.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class LeaveBase implements Serializable {

	private static final long serialVersionUID = 1256101489100186030L;

	private Integer id;
	// 用户id
	private Integer userId;
	// 请假始时间
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startTime;
	// 请假结束时间
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endTime;
	// 请假申请时间
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date applyTime;
	// 请假类型
	private String leaveType;
	// 原因
	private String reason;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 用户id
	 * 
	 * @return
	 * @author XiaoY
	 * @date: 2017年5月28日 下午5:16:52
	 */
	public Integer getUserId() {
		return userId;
	}

	/**
	 * 用户id
	 * 
	 * @param userId
	 * @author XiaoY
	 * @date: 2017年5月28日 下午5:16:55
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * 请假始时间
	 * 
	 * @return
	 * @author XiaoY
	 * @date: 2017年5月28日 下午5:17:03
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * 请假始时间
	 * 
	 * @param startTime
	 * @author XiaoY
	 * @date: 2017年5月28日 下午5:17:06
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * 请假结束时间
	 * 
	 * @return
	 * @author XiaoY
	 * @date: 2017年5月28日 下午5:17:17
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * 请假结束时间
	 * 
	 * @param endTime
	 * @author XiaoY
	 * @date: 2017年5月28日 下午5:17:20
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**
	 * 请假申请时间
	 * 
	 * @return
	 * @author XiaoY
	 * @date: 2017年5月28日 下午5:17:58
	 */
	public Date getApplyTime() {
		return applyTime;
	}

	/**
	 * 请假申请时间
	 * 
	 * @param applyTime
	 * @author XiaoY
	 * @date: 2017年5月28日 下午5:18:01
	 */
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	/**
	 * 请假类型
	 * 
	 * @return
	 * @author XiaoY
	 * @date: 2017年5月28日 下午5:17:34
	 */
	public String getLeaveType() {
		return leaveType;
	}

	/**
	 * 请假类型
	 * 
	 * @param reason
	 * @author XiaoY
	 * @date: 2017年5月28日 下午5:17:38
	 */
	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}

	/**
	 * 原因
	 * 
	 * @return
	 * @author XiaoY
	 * @date: 2017年5月28日 下午5:18:19
	 */
	public String getReason() {
		return reason;
	}

	/**
	 * 原因
	 * 
	 * @param reason
	 * @author XiaoY
	 * @date: 2017年5月28日 下午5:18:23
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}
}