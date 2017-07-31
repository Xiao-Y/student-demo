package org.billow.controller.rabbitMQ;

import org.apache.log4j.Logger;
import org.billow.common.rabbitmq.sender.RabbitMQProducer;
import org.billow.model.custom.JsonResult;
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
	@Qualifier("rabbitMQProducer")
	private RabbitMQProducer rabbitMQProducer;

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
			rabbitMQProducer.send(message, "billow", "也没啥");
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
	public JsonResult readDirectMessage(String message) {
		String type = "";
		String messageJ = "";
		try {
			rabbitMQProducer.send(message, "billow", "也没啥");
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
			rabbitMQProducer.send(message, "billow", "也没啥");
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
