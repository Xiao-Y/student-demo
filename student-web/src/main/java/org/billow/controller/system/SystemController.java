package org.billow.controller.system;

import org.billow.api.menu.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/system")
public class SystemController {
	
	public static final String BASEPATH = "page/system/";
	
	@Autowired
	private MenuService menuService;

	/**
	 * 菜单管理
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @return
	 * 
	 * @date 2017年4月24日 上午9:52:46
	 */
	@RequestMapping("/menuManage")
	public String menuManage() {
		menuService.selectAll(null);
		return BASEPATH + "menuManage";
	}

}
