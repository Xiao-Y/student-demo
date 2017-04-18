package org.billow.common.mq.provider.topic;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.log4j.Logger;
import org.billow.utils.bean.BeanUtils;
import org.billow.utils.exception.ActiveMQException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

@Component
public class TopicProducer {
	private static final Logger logger = Logger.getLogger(TopicProducer.class);

	// @Resource
	private JmsTemplate jmsTopicTemplate;

	public void sendMessage(Destination destination, final String msg) {

		try {
			jmsTopicTemplate = BeanUtils.getBean("jmsTopicTemplate");
		} catch (Exception e) {
			logger.error(e);
			throw new ActiveMQException();
		}
		System.out.println("向主题" + destination.toString() + "发送了消息------------" + msg);
		jmsTopicTemplate.send(destination, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(msg);
			}
		});
	}
}
