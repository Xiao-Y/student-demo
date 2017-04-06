package org.billow.service.mq.provider.topic;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

@Component
public class TopicProducer {

	@Resource
	private JmsTemplate jmsTopicTemplate;

	public void sendMessage(Destination destination, final String msg) {
		System.out.println("向主题" + destination.toString() + "发送了消息------------" + msg);
		jmsTopicTemplate.send(destination, new MessageCreator() {

			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(msg);
			}
		});
	}
}
