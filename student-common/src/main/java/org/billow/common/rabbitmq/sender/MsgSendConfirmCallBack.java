package org.billow.common.rabbitmq.sender;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.stereotype.Component;

/**
 * 消息发到RabbitMQ服务是否成功的确认回调方法
 * 
 * @author liuyongtao
 * 
 * @date 2017年8月1日 上午10:47:07
 */
@Component
public class MsgSendConfirmCallBack implements ConfirmCallback {

	private static final Logger logger = Logger.getLogger(MsgSendConfirmCallBack.class);

	@Override
	public void confirm(CorrelationData correlationData, boolean ack, String cause) {
		if (ack) {
			logger.info("消息确认成功");
		} else {
			// 处理丢失的消息
			logger.info("消息确认失败," + cause);
		}
	}
}
