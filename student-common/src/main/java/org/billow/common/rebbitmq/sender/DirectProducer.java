package org.billow.common.rebbitmq.sender;

import org.apache.log4j.Logger;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.billow.utils.exception.ActiveMQException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

@Service
public class DirectProducer {

	private static final Logger logger = Logger.getLogger(DirectProducer.class);

	@Autowired(required = false)
	private JmsTemplate directExchangeTest;

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
	public void sendMessage(Destination destination, final String msg) throws Exception {
		if (directExchangeTest == null) {
			throw new ActiveMQException();
		}
		if (destination == null) {
			throw new ActiveMQException("Destination为空！");
		}
		logger.info("\r\n向队列：" + destination.toString() + "\r\n发送了消息：" + msg);
		directExchangeTest.send(destination, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(msg);
			}
		});
	}
}
