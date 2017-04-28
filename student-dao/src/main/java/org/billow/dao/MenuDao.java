package org.billow.dao;

import java.util.List;

import org.billow.dao.base.BaseDao;
import org.billow.model.expand.MenuDto;

public interface MenuDao extends BaseDao<MenuDto> {

	public List<MenuDto> getMenuChildList(int id);

	public List<MenuDto> selectAll(MenuDto menu);
}