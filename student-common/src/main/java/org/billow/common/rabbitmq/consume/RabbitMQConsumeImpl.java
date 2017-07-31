package org.billow.common.rabbitmq.consume;

import org.apache.log4j.Logger;
import org.billow.model.custom.MessageObject;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import com.alibaba.fastjson.JSON;

public class RabbitMQConsumeImpl implements RabbitMQConsume {

	private static final Logger logger = Logger.getLogger(RabbitMQConsumeImpl.class);

	private RabbitTemplate rabbitTemplate;
	// 队列的名称
	private String queueName;
	// 超时
	private Long timeoutMillis;

	@Override
	public Message receiveToMessage() {
		Message message = null;
		if (timeoutMillis == null) {
			message = rabbitTemplate.receive(queueName);
		} else {
			message = rabbitTemplate.receive(queueName, timeoutMillis);
		}
		logger.info("读取RabbitMQ中的队列为：" + queueName + " ---> " + message);
		return message;
	}

	@Override
	public MessageObject receiveToMessageObject() {
		Message message = null;
		if (timeoutMillis == null) {
			message = rabbitTemplate.receive(queueName);
		} else {
			message = rabbitTemplate.receive(queueName, timeoutMillis);
		}
		if (message == null) {
			return null;
		}
		MessageObject msg = JSON.parseObject(new String(message.getBody()), MessageObject.class);
		logger.info("读取RabbitMQ中的队列为：" + queueName + " ---> " + msg);
		return msg;
	}

	@Override
	public Object receiveAndConvert() {
		Object message = null;
		if (timeoutMillis == null) {
			message = rabbitTemplate.receiveAndConvert(queueName);
		} else {
			message = rabbitTemplate.receiveAndConvert(queueName, timeoutMillis);
		}
		logger.info("读取RabbitMQ中的队列为：" + queueName + " ---> " + message);
		return message;
	}

	public RabbitTemplate getRabbitTemplate() {
		return rabbitTemplate;
	}

	public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}

	public void setTimeoutMillis(Long timeoutMillis) {
		this.timeoutMillis = timeoutMillis;
	}
}
