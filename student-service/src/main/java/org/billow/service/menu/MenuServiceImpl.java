package org.billow.service.menu;

import org.billow.api.menu.MenuService;
import org.billow.dao.MenuDao;
import org.billow.dao.base.BaseDao;
import org.billow.model.expand.MenuDto;
import org.billow.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MenuServiceImpl extends BaseServiceImpl<MenuDto> implements MenuService {

    @Resource
    private MenuDao menuDao;

    @Resource
    @Override
    public void setBaseDao(BaseDao<MenuDto> baseDao) {
        super.baseDao = this.menuDao;
    }

    @Override
    public List<MenuDto> getMenuChildList(Integer id) {
        return menuDao.getMenuChildList(id);
    }

    @Override
    public List<MenuDto> selectAll(MenuDto menu) {
        return menuDao.selectAll(menu);
    }

    public int deleteByPrimaryKey(MenuDto record) {
        return menuDao.deleteByPrimaryKey(record);
    }

    public int insert(MenuDto record) {
        return menuDao.insert(record);
    }

    public int insertSelective(MenuDto record) {
        return menuDao.insertSelective(record);
    }

    public MenuDto selectByPrimaryKey(MenuDto record) {
        return menuDao.selectByPrimaryKey(record);
    }

    public int updateByPrimaryKeySelective(MenuDto record) {
        return menuDao.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(MenuDto record) {
        return menuDao.updateByPrimaryKey(record);
    }
}
