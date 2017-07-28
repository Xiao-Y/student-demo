package org.billow.common.rabbitmq.sender;

public interface RabbitMQProducer {

	/**
	 * 发送消息
	 * 
	 * @param msgContent
	 *            发送内容
	 */
	public void send(Object msgContent, String businessKey, String businessKeyDesc);
}
