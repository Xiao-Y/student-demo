package org.billow.dao;

import java.util.List;

import com.billow.base.mapper.BaseMapper;
import com.billow.business.model.Menu;

public interface MenuMapper extends BaseMapper<Menu> {

	public List<Menu> getMenuChildList(int id);

	public List<Menu> selectAll(Menu menu);
}