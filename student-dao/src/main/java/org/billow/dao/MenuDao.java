package org.billow.dao;

import java.util.List;

import org.billow.dao.base.BaseDao;
import org.billow.model.domain.Menu;

public interface MenuDao extends BaseDao<Menu> {

	public List<Menu> getMenuChildList(int id);

	public List<Menu> selectAll(Menu menu);
}