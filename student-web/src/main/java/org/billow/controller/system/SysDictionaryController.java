package org.billow.controller.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 数据字典
 * 
 * @author liuyongtao
 * 
 * @date 2017年6月23日 上午8:39:58
 */
@Controller
@RequestMapping("/sysDictionary")
public class SysDictionaryController {

	@RequestMapping("/findDictionary")
	public ModelAndView findDictionary() {
		return null;
	}
}
