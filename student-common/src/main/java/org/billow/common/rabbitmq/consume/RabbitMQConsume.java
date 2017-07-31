package org.billow.common.rabbitmq.consume;

import org.billow.model.custom.MessageObject;
import org.springframework.amqp.core.Message;

public interface RabbitMQConsume {

	/**
	 * 读取字符串消息(可以是json)
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @return MessageObject
	 * 
	 * @date 2017年7月31日 下午4:30:29
	 */
	public MessageObject receiveToMessageObject();

	/**
	 * 读取普通字符串消息
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @return Message
	 * 
	 * @date 2017年7月31日 下午4:30:29
	 */
	public Message receiveToMessage();

	/**
	 * 读取对象
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @return
	 * 
	 * @date 2017年7月31日 下午4:39:49
	 */
	public Object receiveAndConvert();

}
