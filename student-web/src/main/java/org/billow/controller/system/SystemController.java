package org.billow.controller.system;

import javax.servlet.http.HttpServletRequest;

import org.billow.api.menu.MenuService;
import org.billow.utils.RequestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	@ResponseBody
	@RequestMapping("/menuManage")
	public String menuManage(HttpServletRequest request) {
		Integer pageSize = RequestUtils.getPageSize(request);
		Integer targetPage = RequestUtils.getTargetPage(request);
		
		menuService.selectAll(null);
		return BASEPATH + "menuManage";
	}

}
