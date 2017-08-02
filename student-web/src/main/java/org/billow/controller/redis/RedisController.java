package org.billow.controller.redis;

import org.billow.utils.constant.PagePathCst;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * redis的测试
 * 
 * @author XiaoY
 * @date: 2017年8月2日 下午9:48:07
 */
@Controller
@RequestMapping("redisController")
public class RedisController {

	@RequestMapping("/index")
	public String index() {
		return PagePathCst.BASEPATH_REDIS + "index";
	}

}
