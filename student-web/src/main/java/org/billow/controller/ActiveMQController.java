package org.billow.controller;

import javax.annotation.Resource;
import javax.jms.Destination;

import org.apache.log4j.Logger;
import org.billow.common.mq.consume.QueueReceiver;
import org.billow.common.mq.sender.queue.QueueProducer;
import org.billow.common.mq.sender.topic.TopicPublisher;
import org.billow.utils.bean.BeanUtils;
import org.billow.utils.exception.ActiveMQException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("ActiveMQController1")
@RequestMapping("/activeMQ1")
public class ActiveMQController {

	private static final Logger logger = Logger.getLogger(ActiveMQController.class);

	@Resource
	private QueueProducer queueSender;

	// @Resource
	private Destination demoQueueDestination;

	@Resource
	private TopicPublisher topicSender;

	// @Resource
	private Destination demoTopicDestination;

	@Resource
	private QueueReceiver queueConsumer;

	@RequestMapping("/mqIndex")
	public String mqIndex() {
		return "activemqIndex";
	}

	@ResponseBody
	@RequestMapping("/queueSender")
	public String queueSender(@RequestParam("message") String message) {
		try {
			demoQueueDestination = BeanUtils.getBean("demoQueueDestination");
		} catch (Exception e1) {
			logger.error(e1);
			throw new ActiveMQException();
		}
		String op = "";
		try {
			queueSender.sendMessage(demoQueueDestination, message);
			op = "suc";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return op;
	}

	@ResponseBody
	@RequestMapping("/topicSender")
	public String topicSender(@RequestParam("message") String message) {
		String op = "";
		try {
			topicSender.sendMessage(demoTopicDestination, message);
			op = "suc";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return op;
	}

	@RequestMapping("/queueConsumer")
	public String queueConsumer() throws Exception {
		try {
			demoQueueDestination = BeanUtils.getBean("demoQueueDestination");
		} catch (Exception e) {
			logger.error(e);
			throw new ActiveMQException();
		}
		queueConsumer.receive(demoQueueDestination);
		return "activemqIndex";
	}
}
