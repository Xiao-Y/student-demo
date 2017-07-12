package org.billow.common.mq.sender.topic;

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
public class TopicSender {
	private static final Logger logger = Logger.getLogger(TopicSender.class);

	// @Resource
	private JmsTemplate jmsTopicTemplate;
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

		try {
			jmsTopicTemplate = BeanUtils.getBean("jmsTopicTemplate");
		} catch (Exception e) {
			logger.error(e);
			throw new ActiveMQException();
		}
		logger.info("向队列：" + destination.toString() + "\r\n发送了消息：" + msg);
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
		try {
			defaultTopicDestination = BeanUtils.getBean("defaultTopicDestination");
			jmsTopicTemplate = BeanUtils.getBean("jmsTopicTemplate");
		} catch (Exception e) {
			logger.error(e);
			throw new ActiveMQException();
		}
		logger.info("向默认队列：" + defaultTopicDestination.toString() + "\r\n发送了消息：" + msg);
		jmsTopicTemplate.send(defaultTopicDestination, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(msg);
			}
		});
	}
}
