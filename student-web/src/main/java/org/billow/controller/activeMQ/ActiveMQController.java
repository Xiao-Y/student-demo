package org.billow.controller.activeMQ;

import javax.annotation.Resource;
import javax.jms.Destination;

import org.apache.log4j.Logger;
import org.billow.common.mq.provider.queue.QueueProducer;
import org.billow.model.custom.JsonResult;
import org.billow.utils.bean.BeanUtils;
import org.billow.utils.constant.MessageTipsCst;
import org.billow.utils.constant.PagePathCst;
import org.springframework.stereotype.Controller;
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

	@Resource
	private QueueProducer queueProducer;
	private Destination demoQueueDestination;

	@RequestMapping("/index")
	private String index() {
		return PagePathCst.BASEPATH_ACTIVEMQ + "queue";
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
	@RequestMapping("/queueSender")
	public JsonResult queueSender(String message) {
		String type = "";
		String messageJ = "";
		try {
			demoQueueDestination = BeanUtils.getBean("demoQueueDestination");
			queueProducer.sendMessage(demoQueueDestination, message);
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
