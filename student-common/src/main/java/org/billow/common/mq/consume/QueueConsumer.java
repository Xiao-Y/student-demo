package org.billow.common.mq.consume;

import javax.jms.Destination;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;
import org.billow.utils.exception.ActiveMQException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

	@Autowired(required = false)
	@Qualifier("jmsQueueTemplate")
	private JmsTemplate jmsQueueTemplate;
	@Autowired(required = false)
	@Qualifier("defaultQueueDestination")
	private Destination defaultQueueDestination;

	public TextMessage receive(Destination destination) throws Exception {
		if (jmsQueueTemplate == null) {
			throw new ActiveMQException();
		}
		if (destination == null) {
			throw new ActiveMQException("Destination为空！");
		}
		TextMessage message = (TextMessage) jmsQueueTemplate.receive(destination);
		if (message != null) {
			logger.info("\r\n读取：" + destination.toString() + "\r\n发送的消息：" + message.getText());
		}
		return message;
	}

	/**
	 * 
	 *
	 * <br>added by liuyongtao<br>
	 * @return
	 * @throws Exception
	 * 
	 * @date 2017年7月12日 下午4:27:29
	 */
	public TextMessage receive() throws Exception {
		if (jmsQueueTemplate == null || defaultQueueDestination == null) {
			throw new ActiveMQException();
		}
		if (defaultQueueDestination == null) {
			throw new ActiveMQException("Destination为空！");
		}
		TextMessage message = (TextMessage) jmsQueueTemplate.receive(defaultQueueDestination);
		if (message != null) {
			logger.info("\r\n读取：" + defaultQueueDestination.toString() + "\r\n发送的消息：" + message.getText());
		}
		return message;
	}
}
