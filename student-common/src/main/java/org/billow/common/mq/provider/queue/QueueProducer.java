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
	private Destination defaultQueueDestination;

	/**
	 * 向指定队列中发送消息
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @param destination
	 *            指定队列
	 * @param msg
	 * 
	 * @date 2017年7月10日 下午5:46:46
	 */
	public void sendMessage(Destination destination, final String msg) {
		try {
			jmsQueueTemplate = BeanUtils.getBean("jmsQueueTemplate");
		} catch (Exception e) {
			logger.error(e);
			throw new ActiveMQException();
		}
		logger.info("向队列：" + destination.toString() + "\\r\\n发送了消息：" + msg);
		jmsQueueTemplate.send(destination, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(msg);
			}
		});
	}

	/**
	 * 向默认队列中发送消息
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @param msg
	 * 
	 * @date 2017年7月10日 下午5:47:03
	 */
	public void sendMessage(final String msg) {
		try {
			jmsQueueTemplate = BeanUtils.getBean("jmsQueueTemplate");
			defaultQueueDestination = BeanUtils.getBean("defaultQueueDestination");
		} catch (Exception e) {
			logger.error(e);
			throw new ActiveMQException();
		}
		logger.info("向默认队列：" + defaultQueueDestination.toString() + "\\r\\n发送了消息：" + msg);
		jmsQueueTemplate.send(defaultQueueDestination, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(msg);
			}
		});
	}
}
