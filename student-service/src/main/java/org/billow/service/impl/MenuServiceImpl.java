package org.billow.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.billow.api.MenuService;
import org.billow.dao.MenuMapper;
import org.billow.model.domain.Menu;
import org.billow.service.impl.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class MenuServiceImpl extends BaseServiceImpl<Menu> implements MenuService {

	private MenuMapper menuMapper;

	@Resource
	public void setMenuMapper(MenuMapper menuMapper) {
		this.menuMapper = menuMapper;
		super.setBaseMapper(menuMapper);
	}

	@Override
	public List<Menu> getMenuChildList(int id) {
		return menuMapper.getMenuChildList(id);
	}

	@Override
	public List<Menu> selectAll(Menu menu) {
		return menuMapper.selectAll(menu);
	}
}
