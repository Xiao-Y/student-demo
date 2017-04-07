package org.billow.common.mq.consume;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.TextMessage;

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
	@Resource
	private JmsTemplate jmsQueueTemplate;

	public TextMessage receive(Destination destination) {

		TextMessage message = (TextMessage) jmsQueueTemplate.receive(destination);
		try {
			if (message != null) {
				System.out.println("接收到：" + destination.toString() + " 发来的：" + message.getText());
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}
		return message;
	}
}
