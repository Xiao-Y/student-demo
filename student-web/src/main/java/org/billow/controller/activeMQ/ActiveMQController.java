package org.billow.controller.activeMQ;

import javax.jms.Destination;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;
import org.billow.common.mq.consume.QueueReceiver;
import org.billow.common.mq.consume.TopicSubscriber;
import org.billow.common.mq.sender.queue.QueueProducer;
import org.billow.common.mq.sender.topic.TopicPublisher;
import org.billow.model.custom.JsonResult;
import org.billow.utils.constant.MessageTipsCst;
import org.billow.utils.constant.PagePathCst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * activeMQ消息队列
 * 
 * @author liuyongtao
 * 
 * @date 2017年7月10日 上午9:15:41
 */
@Controller
@RequestMapping("/activeMQ")
public class ActiveMQController {

	private static final Logger logger = Logger.getLogger(ActiveMQController.class);

	@Autowired
	private QueueProducer queueProducer;
	@Autowired
	private QueueReceiver queueReceiver;

	@Autowired(required = false)
	@Qualifier("demoQueueDestination")
	private Destination demoQueueDestination;

	@Autowired(required = false)
	@Qualifier("demoQueueDestinationListener")
	private Destination demoQueueDestinationListener;

	@Autowired
	private TopicPublisher topicPublisher;
	@Autowired
	private TopicSubscriber topicSubscriber;

	@Autowired(required = false)
	@Qualifier("demoTopicDestination")
	private Destination demoTopicDestination;

	@RequestMapping("/index/{mq}")
	private String index(@PathVariable("mq") String mq) {
		return PagePathCst.BASEPATH_ACTIVEMQ + mq;
	}

	/**
	 * 点对点：发送消息到队列
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @param message
	 * @return
	 * 
	 * @date 2017年7月10日 上午9:17:33
	 */
	@ResponseBody
	@RequestMapping("/queueSender/{def}")
	public JsonResult queueSender(@PathVariable boolean def, String message) {
		String type = "";
		String messageJ = "";
		try {
			if (def) {// 默认消息队列
				queueProducer.sendMessage(message);
			} else {
				queueProducer.sendMessage(demoQueueDestination, message);
			}
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
	 * 点对点：读取消息队列
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @date 2017年7月11日 上午11:05:52
	 */
	@ResponseBody
	@RequestMapping("/readQueueMessage/{def}")
	public String readQueueMessage(@PathVariable boolean def) throws Exception {
		TextMessage receive = null;
		if (def) {// 默认消息队列
			receive = queueReceiver.receive();
		} else {
			receive = queueReceiver.receive(demoQueueDestination);
		}
		if (receive == null) {
			return null;
		}
		return receive.getText();
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
			queueProducer.sendMessage(demoQueueDestinationListener, message);
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
	 * 主题：发送主题
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @param message
	 * @return
	 * 
	 * @date 2017年7月10日 上午9:17:33
	 */
	@ResponseBody
	@RequestMapping("/topicSender/{def}")
	public JsonResult topicSender(@PathVariable boolean def, String message) {
		String type = "";
		String messageJ = "";
		try {
			if (def) {// 默认消息队列
				topicPublisher.sendMessage(message);
			} else {
				topicPublisher.sendMessage(demoTopicDestination, message);
			}
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
