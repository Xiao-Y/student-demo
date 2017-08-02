package org.billow.service.menu;

import java.util.List;

import javax.annotation.Resource;

import org.billow.api.menu.MenuService;
import org.billow.dao.MenuDao;
import org.billow.dao.base.BaseDao;
import org.billow.model.expand.MenuDto;
import org.billow.service.base.BaseServiceImpl;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class MenuServiceImpl extends BaseServiceImpl<MenuDto> implements MenuService {

	@Resource
	private MenuDao menuDao;

	@Resource
	@Override
	public void setBaseDao(BaseDao<MenuDto> baseDao) {
		super.baseDao = this.menuDao;
	}

	@Cacheable(key = "#id", value = "menu")
	@Override
	public List<MenuDto> getMenuChildList(Integer id) {
		return menuDao.getMenuChildList(id);
	}

	@Override
	public List<MenuDto> selectAll(MenuDto menu) {
		return menuDao.selectAll(menu);
	}

	@CachePut(key = "#record.id", value = "menu")
	public int deleteByPrimaryKey(MenuDto record) {
		return menuDao.deleteByPrimaryKey(record);
	}

	@CachePut(key = "#record.id", value = "menu")
	public int insert(MenuDto record) {
		return menuDao.insert(record);
	}

	@CachePut(key = "#record.id", value = "menu")
	public int insertSelective(MenuDto record) {
		return menuDao.insertSelective(record);
	}

	@CachePut(key = "#record.id", value = "menu")
	public MenuDto selectByPrimaryKey(MenuDto record) {
		return menuDao.selectByPrimaryKey(record);
	}

	@CachePut(key = "#record.id", value = "menu")
	public int updateByPrimaryKeySelective(MenuDto record) {
		return menuDao.updateByPrimaryKeySelective(record);
	}

	@CachePut(key = "#record.id", value = "menu")
	public int updateByPrimaryKey(MenuDto record) {
		return menuDao.updateByPrimaryKey(record);
	}
}
