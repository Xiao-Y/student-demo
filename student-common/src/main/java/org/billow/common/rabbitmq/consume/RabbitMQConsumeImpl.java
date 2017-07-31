package org.billow.common.rabbitmq.consume;

import org.apache.log4j.Logger;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class RabbitMQConsumeImpl implements RabbitMQConsume {

	private static final Logger logger = Logger.getLogger(RabbitMQConsumeImpl.class);

	private RabbitTemplate rabbitTemplate;
	private String exchangeName;
	private String routingKey;
	private String virHostBeanKey;

	public RabbitTemplate getRabbitTemplate() {
		return rabbitTemplate;
	}

	public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	public String getExchangeName() {
		return exchangeName;
	}

	public void setExchangeName(String exchangeName) {
		this.exchangeName = exchangeName;
	}

	public String getRoutingKey() {
		return routingKey;
	}

	public void setRoutingKey(String routingKey) {
		this.routingKey = routingKey;
	}

	public String getVirHostBeanKey() {
		return virHostBeanKey;
	}

	public void setVirHostBeanKey(String virHostBeanKey) {
		this.virHostBeanKey = virHostBeanKey;
	}

}
