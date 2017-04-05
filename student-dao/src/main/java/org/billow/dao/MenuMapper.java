package org.billow.dao;

import java.util.List;

import org.billow.dao.base.BaseMapper;
import org.billow.model.domain.Menu;

public interface MenuMapper extends BaseMapper<Menu> {

	public List<Menu> getMenuChildList(int id);

	public List<Menu> selectAll(Menu menu);
}