package org.billow.api;

import java.util.List;

import org.billow.api.base.BaseService;
import org.billow.model.domain.Menu;

public interface MenuService extends BaseService<Menu> {
	public List<Menu> getMenuChildList(int id);

	public List<Menu> selectAll(Menu menu);
}
