package org.billow.common.mq.consume;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;
import org.billow.utils.bean.BeanUtils;
import org.billow.utils.exception.ActiveMQException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/**
 * 
 * @author liuyongtao
 * 
 * @date 2017年3月14日 下午12:35:36
 */
@Component
public class QueueConsumer {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(QueueConsumer.class);

	// @Resource
	private JmsTemplate jmsQueueTemplate;

	public TextMessage receive(Destination destination) {

		try {
			jmsQueueTemplate = BeanUtils.getBean("jmsQueueTemplate");
		} catch (Exception e1) {
			logger.error(e1);
			throw new ActiveMQException();
		}
		TextMessage message = (TextMessage) jmsQueueTemplate.receive(destination);
		try {
			if (message != null) {
				logger.info("\r\n读取：" + destination.toString() + "\r\n发送的消息：" + message.getText());
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}
		return message;
	}
}
