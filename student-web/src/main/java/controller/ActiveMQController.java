package controller;

import javax.annotation.Resource;
import javax.jms.Destination;

import org.billow.service.mq.consume.QueueConsumer;
import org.billow.service.mq.provider.queue.QueueProducer;
import org.billow.service.mq.provider.topic.TopicProducer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/activeMQ")
public class ActiveMQController {

	@Resource
	private QueueProducer queueProducer;

	@Resource
	private Destination demoQueueDestination;

	@Resource
	private TopicProducer topicProducer;

	@Resource
	private Destination demoTopicDestination;

	@Resource
	private QueueConsumer queueConsumer;

	@RequestMapping("/mqIndex")
	public String mqIndex() {
		return "activemqIndex";
	}

	@ResponseBody
	@RequestMapping("/queueSender")
	public String queueSender(@RequestParam("message") String message) {
		String op = "";
		try {
			queueProducer.sendMessage(demoQueueDestination, message);
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
			topicProducer.sendMessage(demoTopicDestination, message);
			op = "suc";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return op;
	}

	@RequestMapping("/queueConsumer")
	public String queueConsumer() {
		queueConsumer.receive(demoQueueDestination);
		return "activemqIndex";
	}
}
