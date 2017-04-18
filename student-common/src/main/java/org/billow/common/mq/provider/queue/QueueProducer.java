package org.billow.common.mq.provider.queue;

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
public class QueueProducer {

	private static final Logger logger = Logger.getLogger(QueueProducer.class);

	// @Resource(name = "jmsQueueTemplate")
	private JmsTemplate jmsQueueTemplate;

	public void sendMessage(Destination destination, final String msg) {
		try {
			jmsQueueTemplate = BeanUtils.getBean("jmsQueueTemplate");
		} catch (Exception e) {
			logger.error(e);
			throw new ActiveMQException();
		}
		logger.error("向队列" + destination.toString() + "发送了消息------------" + msg);
		jmsQueueTemplate.send(destination, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(msg);
			}
		});
	}
}
