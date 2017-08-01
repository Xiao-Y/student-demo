package org.billow.common.rabbitmq.sender;

import org.apache.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ReturnCallback;
import org.springframework.stereotype.Component;

/**
 * 消息发送失败回调处理
 * 
 * @author liuyongtao
 * 
 * @date 2017年8月1日 上午10:55:17
 */
@Component
public class MsgSendReturnCallback implements ReturnCallback {

	private static final Logger logger = Logger.getLogger(MsgSendReturnCallback.class);

	@Override
	public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
		logger.info("消息发送失败回调处理");
		logger.info("replyCode：" + replyCode + "replyText：" + replyText + "exchange：" + exchange + "routingKey：" + routingKey);
		logger.info("Body：" + new String(message.getBody()));
	}
}