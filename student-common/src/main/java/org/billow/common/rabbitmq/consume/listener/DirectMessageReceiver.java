package org.billow.common.rabbitmq.consume.listener;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.stereotype.Component;

@Component
public class DirectMessageReceiver implements MessageListener {

	private static final Logger logger = Logger.getLogger(DirectMessageReceiver.class);

	@Override
	public void onMessage(Message message) {
		MessageProperties messageProperties = message.getMessageProperties();
		logger.info("\r\n读取1：" + messageProperties.getConsumerQueue() + messageProperties.getReceivedExchange() + "\r\n发送的消息："
				+ new String(message.getBody()));
	}
}
