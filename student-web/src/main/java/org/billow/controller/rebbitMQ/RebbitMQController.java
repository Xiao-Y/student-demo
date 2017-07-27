package org.billow.controller.rebbitMQ;

import org.apache.log4j.Logger;
import org.billow.common.rebbitmq.sender.DirectProducer;
import org.billow.model.custom.JsonResult;
import org.billow.utils.constant.MessageTipsCst;
import org.billow.utils.constant.PagePathCst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rebbitMQ")
public class RebbitMQController {

	private static final Logger logger = Logger.getLogger(RebbitMQController.class);

	@Autowired
	private DirectProducer directProducer;

	/**
	 * 
	 * @return
	 * @author XiaoY
	 * @date: 2017年7月27日 下午10:01:43
	 */
	@RequestMapping("/index/{mq}")
	private String index(@PathVariable("mq") String mq) {
		return PagePathCst.BASEPATH_REBBITMQ + mq;
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
			directProducer.sendMessage(null, message);
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
