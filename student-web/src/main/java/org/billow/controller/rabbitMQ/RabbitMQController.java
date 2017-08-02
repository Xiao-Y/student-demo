package org.billow.controller.rabbitMQ;

import org.apache.log4j.Logger;
import org.billow.common.rabbitmq.consume.RabbitMQConsume;
import org.billow.common.rabbitmq.sender.RabbitMQProducer;
import org.billow.model.custom.JsonResult;
import org.billow.model.custom.MessageObject;
import org.billow.utils.constant.MessageTipsCst;
import org.billow.utils.constant.PagePathCst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/rabbitMQ")
public class RabbitMQController {

	private static final Logger logger = Logger.getLogger(RabbitMQController.class);

	@Autowired(required = false)
	@Qualifier("rabbitMQDirectProducer")
	private RabbitMQProducer rabbitMQDirectProducer;

	@Autowired(required = false)
	@Qualifier("rabbitMQDirectProducerListenter")
	private RabbitMQProducer rabbitMQDirectProducerListenter;

	@Autowired(required = false)
	@Qualifier("rabbitMQConsume")
	private RabbitMQConsume rabbitMQConsume;

	@Autowired(required = false)
	@Qualifier("rabbitMQDirectProducerConfirmListenter")
	private RabbitMQProducer rabbitMQDirectProducerConfirmListenter;

	@Autowired(required = false)
	@Qualifier("rabbitMQTopicProducer")
	private RabbitMQProducer rabbitMQTopicProducer;
	@Autowired(required = false)
	@Qualifier("rabbitMQTopicProducer1")
	private RabbitMQProducer rabbitMQTopicProducer1;
	@Autowired(required = false)
	@Qualifier("rabbitMQTopicProducer2")
	private RabbitMQProducer rabbitMQTopicProducer2;
	@Autowired(required = false)
	@Qualifier("rabbitMQTopicProducer3")
	private RabbitMQProducer rabbitMQTopicProducer3;
	@Autowired(required = false)
	@Qualifier("rabbitMQTopicProducer4")
	private RabbitMQProducer rabbitMQTopicProducer4;
	@Autowired(required = false)
	@Qualifier("rabbitMQTopicProducer5")
	private RabbitMQProducer rabbitMQTopicProducer5;

	/**
	 * 
	 * @return
	 * @author XiaoY
	 * @date: 2017年7月27日 下午10:01:43
	 */
	@RequestMapping("/index/{mq}")
	private String index(@PathVariable("mq") String mq) {
		return PagePathCst.BASEPATH_RABBITMQ + mq;
	}

	/**
	 * 发送消息direct
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @param message
	 * @return
	 * 
	 * @date 2017年7月17日 上午10:52:42
	 */
	@ResponseBody
	@RequestMapping("/directSender")
	public JsonResult directSender(String message) {
		String type = "";
		String messageJ = "";
		try {
			rabbitMQDirectProducer.send(message, "billow", "也没啥要确认");
			type = MessageTipsCst.TYPE_SUCCES;
			messageJ = MessageTipsCst.SUBMIT_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			type = MessageTipsCst.TYPE_ERROR;
			messageJ = MessageTipsCst.SUBMIT_FAILURE;
			logger.error(e);
		}
		JsonResult json = new JsonResult();
		json.setType(type);
		json.setMessage(messageJ);
		return json;
	}

	/**
	 * 读取消息direct
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @param message
	 * @return
	 * 
	 * @date 2017年7月17日 上午10:52:42
	 */
	@ResponseBody
	@RequestMapping("/readDirectMessage")
	public Object readQueueMessage() throws Exception {
		MessageObject msg = rabbitMQConsume.receiveToMessageObject();
		return msg.getBody();
	}

	/**
	 * 消息队列监听器形式的
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @param message
	 * @return
	 * 
	 * @date 2017年7月17日 上午10:52:42
	 */
	@ResponseBody
	@RequestMapping("/queueListenerSender")
	public JsonResult queueListenerSender(String message) {
		String type = "";
		String messageJ = "";
		try {
			rabbitMQDirectProducerListenter.send(message, "billow,Listenter", "也没啥，监听的方式");
			type = MessageTipsCst.TYPE_SUCCES;
			messageJ = MessageTipsCst.SUBMIT_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			type = MessageTipsCst.TYPE_ERROR;
			messageJ = MessageTipsCst.SUBMIT_FAILURE;
			logger.error(e);
		}
		JsonResult json = new JsonResult();
		json.setType(type);
		json.setMessage(messageJ);
		return json;
	}

	/**
	 * 消息队列监听器形式的（需要消息确认）
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @param message
	 * @return
	 * 
	 * @date 2017年7月17日 上午10:52:42
	 */
	@ResponseBody
	@RequestMapping("/queueConfirmListenerSender")
	public JsonResult queueConfirmListenerSender(String message) {
		String type = "";
		String messageJ = "";
		try {
			rabbitMQDirectProducerConfirmListenter.send(message, "billow", "也没啥要确认");
			type = MessageTipsCst.TYPE_SUCCES;
			messageJ = MessageTipsCst.SUBMIT_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			type = MessageTipsCst.TYPE_ERROR;
			messageJ = MessageTipsCst.SUBMIT_FAILURE;
			logger.error(e);
		}
		JsonResult json = new JsonResult();
		json.setType(type);
		json.setMessage(messageJ);
		return json;
	}

	/**
	 * 发送消息Topic
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @param message
	 * @return
	 * 
	 * @date 2017年7月17日 上午10:52:42
	 */
	@ResponseBody
	@RequestMapping("/topicSender")
	public JsonResult topicSender(String message) {
		String type = "";
		String messageJ = "";
		try {
			rabbitMQTopicProducer.send(message, "billow", "也没啥要确认");
			rabbitMQTopicProducer2.send(message, "billow2", "也没啥要确认2");
			rabbitMQTopicProducer3.send(message, "billow3", "也没啥要确认3");
			rabbitMQTopicProducer4.send(message, "billow4", "也没啥要确认4");
			rabbitMQTopicProducer5.send(message, "billow5", "也没啥要确认5");
			type = MessageTipsCst.TYPE_SUCCES;
			messageJ = MessageTipsCst.SUBMIT_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			type = MessageTipsCst.TYPE_ERROR;
			messageJ = MessageTipsCst.SUBMIT_FAILURE;
			logger.error(e);
		}
		JsonResult json = new JsonResult();
		json.setType(type);
		json.setMessage(messageJ);
		return json;
	}
}
