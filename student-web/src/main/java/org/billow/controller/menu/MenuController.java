package org.billow.controller.menu;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.billow.api.menu.MenuService;
import org.billow.model.domain.Menu;
import org.billow.utils.ToolsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.support.HttpRequestHandlerServlet;

import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping("/menu")
public class MenuController implements Comparator<Menu> {

	@Autowired
	private MenuService menuService;

	@RequestMapping("/index")
	public void index(HttpRequestHandlerServlet request) {
		Menu menu = new Menu();
		menu.setPid(0);
		List<Menu> selectAll = menuService.selectAll(menu);
		Collections.sort(selectAll, this);
		if (ToolsUtils.isNotEmpty(selectAll)) {
			for (Menu temp : selectAll) {
				List<Menu> childList = menuService.getMenuChildList(temp.getId());
				Collections.sort(childList, this);
				temp.setChildren(childList);
			}
		}
		System.out.println(JSON.toJSONString(selectAll));
	}

	@Override
	public int compare(Menu m1, Menu m2) {
		return m1.getDisplayno().compareTo(m2.getDisplayno());
	}
}
