package org.billow.utils.exception;

/**
 * activeMQ异常信息
 * 
 * @author liuyongtao
 * 
 * @date 2017年4月18日 下午4:39:56
 */
public class ActiveMQException extends RuntimeException {

	private static final long serialVersionUID = -3362712190200087623L;

	public ActiveMQException() {
		super("ActiveMQ没有启动...");
	}

	public ActiveMQException(String st) {
		super(st);
	}
}
