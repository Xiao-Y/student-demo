package org.billow.controller.menu;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.billow.api.menu.MenuService;
import org.billow.model.domain.MenuBase;
import org.billow.model.expand.MenuDto;
import org.billow.utils.ToolsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.support.HttpRequestHandlerServlet;

import com.alibaba.fastjson.JSON;

@Controller
@RequestMapping("/menu")
public class MenuController implements Comparator<MenuBase> {

	@Autowired
	private MenuService menuService;

	@RequestMapping("/index")
	public void index(HttpRequestHandlerServlet request) {
		MenuDto menu = new MenuDto();
		menu.setPid(0);
		List<MenuDto> selectAll = menuService.selectAll(menu);
		Collections.sort(selectAll, this);
		if (ToolsUtils.isNotEmpty(selectAll)) {
			for (MenuBase temp : selectAll) {
				List<MenuDto> childList = menuService.getMenuChildList(temp.getId());
				Collections.sort(childList, this);
				temp.setChildren(childList);
			}
		}
		System.out.println(JSON.toJSONString(selectAll));
	}

	@Override
	public int compare(MenuBase m1, MenuBase m2) {
		return m1.getDisplayno().compareTo(m2.getDisplayno());
	}
}
