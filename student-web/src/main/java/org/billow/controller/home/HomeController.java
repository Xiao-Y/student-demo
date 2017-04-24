package org.billow.controller.home;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.ejb.SessionContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.billow.api.menu.MenuService;
import org.billow.model.domain.Menu;
import org.billow.utils.ToolsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/home")
public class HomeController implements Comparator<Menu> {

	@Autowired
	private MenuService menuService;

	@RequestMapping("/homeIndex")
	public String homeIndex() {

		return "page/home/index";
	}

	@RequestMapping("/main")
	public String main() {

		return "page/home/main";
	}

	/**
	 * 显示菜单
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @return
	 * 
	 * @date 2017年4月24日 上午9:45:13
	 */
	@ResponseBody
	@RequestMapping("/menu")
	public List<Menu> index(HttpServletRequest request) {
		ServletContext servletContext = request.getServletContext();
		String contextPath = servletContext.getContextPath();
		Menu menu = new Menu();
		menu.setPid(0);
		List<Menu> selectAll = menuService.selectAll(menu);
		Collections.sort(selectAll, this);
		if (ToolsUtils.isNotEmpty(selectAll)) {
			for (Menu temp : selectAll) {
				List<Menu> childList = menuService.getMenuChildList(temp.getId());
				if (ToolsUtils.isNotEmpty(childList)) {
					for(Menu tempChild : childList){
						tempChild.setHref(contextPath + tempChild.getHref());
					}
				}
				Collections.sort(childList, this);
				temp.setChildren(childList);
			}
		}
		return selectAll;
	}

	@Override
	public int compare(Menu m1, Menu m2) {
		return m1.getDisplayno().compareTo(m2.getDisplayno());
	}
}
