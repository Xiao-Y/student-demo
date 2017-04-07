package org.bilow.common.mq.provider.queue;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

@Component
public class QueueProducer {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(QueueProducer.class);
	private final org.slf4j.Logger logger2 = LoggerFactory.getLogger(QueueProducer.class);

	@Resource(name = "jmsQueueTemplate")
	private JmsTemplate jmsQueueTemplate;

	public void sendMessage(Destination destination, final String msg) {
		logger.error("向队列" + destination.toString() + "发送了消息------------" + msg);
		logger2.error("向队列" + destination.toString() + "发送了消息------------" + msg);
		jmsQueueTemplate.send(destination, new MessageCreator() {

			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(msg);
			}
		});
	}
}
