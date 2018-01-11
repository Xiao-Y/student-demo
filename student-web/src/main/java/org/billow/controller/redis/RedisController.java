package org.billow.controller.redis;

import org.apache.log4j.Logger;
import org.billow.model.custom.JsonResult;
import org.billow.utils.constant.MessageTipsCst;
import org.billow.utils.constant.PagePathCst;
import org.billow.utils.redis.RedisUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * redis的测试
 *
 * @author XiaoY
 * @date: 2017年8月2日 下午9:48:07
 */
@Controller
@RequestMapping("/redisController")
public class RedisController {

    private static final Logger logger = Logger.getLogger(RedisController.class);

    @RequestMapping("/index")
    public String index() {
        return PagePathCst.BASEPATH_REDIS + "index";
    }

    @ResponseBody
    @RequestMapping("/addValue")
    public JsonResult addValue(String key, String value) {
        JsonResult json = new JsonResult();
        String message = "";
        String type = "";
        try {
            RedisUtil.addObject(key, value);
            message = MessageTipsCst.SUBMIT_SUCCESS;
            type = MessageTipsCst.TYPE_SUCCES;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            message = MessageTipsCst.SUBMIT_FAILURE;
            type = MessageTipsCst.TYPE_ERROR;
        }
        json.setMessage(message);
        json.setType(type);
        json.setRoot("/redisController/index");
        return json;
    }

    @ResponseBody
    @RequestMapping("/getValue")
    public String getValue(String key) {
        String menu = RedisUtil.get(key);
        return menu;
    }
}
