package org.billow.api.menu;

import java.util.List;

import org.billow.api.base.BaseService;
import org.billow.model.expand.MenuDto;

public interface MenuService extends BaseService<MenuDto> {
	public List<MenuDto> getMenuChildList(Integer id);

	public List<MenuDto> selectAll(MenuDto menu);
}
