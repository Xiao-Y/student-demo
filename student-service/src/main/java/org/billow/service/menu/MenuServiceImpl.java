package org.billow.service.menu;

import java.util.List;

import javax.annotation.Resource;

import org.billow.api.menu.MenuService;
import org.billow.dao.MenuDao;
import org.billow.model.domain.Menu;
import org.billow.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class MenuServiceImpl extends BaseServiceImpl<Menu> implements MenuService {

	private MenuDao menuDao;

	@Resource
	public void setMenuDao(MenuDao menuDao) {
		this.menuDao = menuDao;
		super.setBaseDao(menuDao);
	}

	@Override
	public List<Menu> getMenuChildList(int id) {
		return menuDao.getMenuChildList(id);
	}

	@Override
	public List<Menu> selectAll(Menu menu) {
		return menuDao.selectAll(menu);
	}
}
