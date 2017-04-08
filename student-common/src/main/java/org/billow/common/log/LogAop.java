package org.billow.common.log;

import org.aspectj.lang.JoinPoint;

public interface LogAop {
	/**
	 * 有参的保存日志
	 * 
	 * @param point
	 */
	public void logArgSave(JoinPoint point);
}
