package org.billow.common.rabbitmq.sender;

import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.billow.model.custom.MessageHeadObject;
import org.billow.model.custom.MessageObject;
import org.springframework.amqp.rabbit.connection.SimpleResourceHolder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;

import com.alibaba.fastjson.JSON;

public class RabbitMQProducerImpl implements RabbitMQProducer {

	private static final Logger logger = Logger.getLogger(RabbitMQProducerImpl.class);

	private RabbitTemplate rabbitTemplate;
	private String exchangeName;
	private String routingKey;
	private String virHostBeanKey;

	@Override
	public void send(Object msgContent, String businessKey, String businessKeyDesc) {
		MessageObject obj = genMessageObject(msgContent, businessKey, businessKeyDesc);
		CorrelationData correlationId = new CorrelationData(obj.getMessageId());
		MessageProcessor processor = new MessageProcessor(correlationId, obj);
		String objStr = JSON.toJSONString(obj);
		logger.info("RabbitMQ发送的消息：" + objStr);
		try {
			if (StringUtils.isNotBlank(virHostBeanKey)) {
				SimpleResourceHolder.bind(rabbitTemplate.getConnectionFactory(), virHostBeanKey);
				rabbitTemplate.convertAndSend(exchangeName, routingKey, objStr, processor, correlationId);
				SimpleResourceHolder.unbind(rabbitTemplate.getConnectionFactory());
			} else {
				rabbitTemplate.convertAndSend(exchangeName, routingKey, objStr, processor, correlationId);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("RabbitMQ消息发送失败：", e);
			SimpleResourceHolder.unbindIfPossible(rabbitTemplate.getConnectionFactory());
		}
	}

	/**
	 * 组装消息体
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @param obj
	 *            消息主体
	 * @param businessKey
	 *            业务key
	 * @param businessKeyDesc
	 *            业务key说明
	 * @return
	 * 
	 * @date 2017年7月28日 下午5:27:20
	 */
	private MessageObject genMessageObject(Object obj, String businessKey, String businessKeyDesc) {
		MessageObject message = new MessageObject();
		message.setDurable(true);// 是否需要持久化
		message.setBody(obj);
		MessageHeadObject head = new MessageHeadObject();
		head.setBusinessKey(businessKey);
		head.setBusinessKeyDesc(businessKeyDesc);
		message.setHead(head);
		message.setMessageId(UUID.randomUUID().toString());
		return message;
	}

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
