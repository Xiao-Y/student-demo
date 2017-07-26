package org.billow.common.activemq.consume.listener.topic;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class TopicMessageListener2 implements MessageListener {

	private static final Logger logger = Logger.getLogger(TopicMessageListener2.class);
	
	@Override
	public void onMessage(Message message) {
		try {
			Destination destination = message.getJMSDestination();
			logger.info("\r\n读取2：" + destination.toString() + "\r\n发送的消息：" + ((TextMessage) message).getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
