package org.billow.common.mq.consume.listener.queue;

import org.apache.log4j.Logger;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.stereotype.Component;

@Component
public class QueueMessageListener implements MessageListener {
	private static final Logger logger = Logger.getLogger(QueueMessageListener.class);

	@Override
	public void onMessage(Message message) {
		try {
			Destination destination = message.getJMSDestination();
			logger.info("\r\n读取：" + destination.toString() + "\r\n发送的消息：" + ((TextMessage) message).getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
