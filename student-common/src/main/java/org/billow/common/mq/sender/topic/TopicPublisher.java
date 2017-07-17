package org.billow.common.mq.sender.topic;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.log4j.Logger;
import org.billow.utils.exception.ActiveMQException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

@Component
public class TopicPublisher {
	private static final Logger logger = Logger.getLogger(TopicPublisher.class);

	@Autowired(required = false)
	@Qualifier("jmsTopicTemplate")
	private JmsTemplate jmsTopicTemplate;

	@Autowired(required = false)
	@Qualifier("defaultTopicDestination")
	private Destination defaultTopicDestination;

	/**
	 * 向指定主题中发送消息
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @param destination
	 * @param msg
	 * 
	 * @date 2017年7月11日 上午11:17:56
	 */
	public void sendMessage(Destination destination, final String msg) {
		if (jmsTopicTemplate == null) {
			throw new ActiveMQException();
		}
		if (destination == null) {
			throw new ActiveMQException("Destination为空！");
		}
		logger.info("\r\n向主题：" + destination.toString() + "\r\n发送了消息：" + msg);
		jmsTopicTemplate.send(destination, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(msg);
			}
		});
	}

	/**
	 * 向默认的主题中发送消息
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @param msg
	 * 
	 * @date 2017年7月11日 上午11:17:30
	 */
	public void sendMessage(final String msg) {
		if (defaultTopicDestination == null || jmsTopicTemplate == null) {
			throw new ActiveMQException();
		}
		logger.info("\r\n向默认主题：" + defaultTopicDestination.toString() + "\r\n发送了消息：" + msg);
		jmsTopicTemplate.send(defaultTopicDestination, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(msg);
			}
		});
	}
}
