package org.billow.service.menu;

import java.util.List;

import javax.annotation.Resource;

import org.billow.api.menu.MenuService;
import org.billow.dao.MenuDao;
import org.billow.model.expand.MenuDto;
import org.billow.service.base.BaseServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class MenuServiceImpl extends BaseServiceImpl<MenuDto> implements MenuService {

	private MenuDao menuDao;

	@Resource
	public void setMenuDao(MenuDao menuDao) {
		this.menuDao = menuDao;
		super.setBaseDao(menuDao);
	}

	@Override
	@Cacheable(keyGenerator = "key", value = "getMenuChildList")
	public List<MenuDto> getMenuChildList(Integer id) {
		return menuDao.getMenuChildList(id);
	}

	@Override
	@Cacheable(keyGenerator = "key", value = "selectAll")
	public List<MenuDto> selectAll(MenuDto menu) {
		return menuDao.selectAll(menu);
	}
}
