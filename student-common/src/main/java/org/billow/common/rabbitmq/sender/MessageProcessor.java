package org.billow.common.rabbitmq.sender;

import org.apache.log4j.Logger;
import org.billow.model.custom.MessageObject;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.support.CorrelationData;

public class MessageProcessor implements MessagePostProcessor {

	private static final Logger logger = Logger.getLogger(MessageProcessor.class);

	private CorrelationData data;
	private MessageObject object;

	public MessageProcessor(CorrelationData data, MessageObject object) {
		this.data = null;
		this.data = data;
		this.object = object;
	}

	public Message postProcessMessage(Message message) throws AmqpException {
		MessageProperties messageProperties = message.getMessageProperties();
		if (object.isDurable())
			messageProperties.setDeliveryMode(MessageDeliveryMode.PERSISTENT);
		else
			messageProperties.setDeliveryMode(MessageDeliveryMode.NON_PERSISTENT);
		try {
			messageProperties.setCorrelationIdString(data.getId());
			messageProperties.setMessageId(data.getId());
			messageProperties.setExpiration(object.getExpiration());
			messageProperties.setPriority(object.getPriority());
		} catch (Exception e) {
			logger.error("PostProcessMessage Error:", e);
		}
		return message;
	}
}
