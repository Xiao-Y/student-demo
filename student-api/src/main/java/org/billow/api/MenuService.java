package org.billow.api;

import java.util.List;

import com.billow.base.service.BaseService;
import com.billow.business.model.Menu;

public interface MenuService extends BaseService<Menu> {
	public List<Menu> getMenuChildList(int id);

	public List<Menu> selectAll(Menu menu);
}
