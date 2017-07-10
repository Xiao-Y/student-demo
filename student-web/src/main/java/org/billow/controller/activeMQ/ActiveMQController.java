package org.billow.controller.activeMQ;

import org.apache.log4j.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public String queueSender(String message) {
		return null;
	}

}
