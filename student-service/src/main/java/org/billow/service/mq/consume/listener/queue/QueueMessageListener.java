package org.billow.service.mq.consume.listener.queue;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.springframework.stereotype.Component;

@Component
public class QueueMessageListener implements MessageListener {

	@Override
	public void onMessage(Message message) {
		try {
			System.out.println("QueueMessageListener--->接收到消息:" + ((TextMessage) message).getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

}
