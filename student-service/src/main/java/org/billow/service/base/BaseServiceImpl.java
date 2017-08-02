package org.billow.service.base;

import org.billow.api.base.BaseService;
import org.billow.dao.base.BaseDao;

public abstract class BaseServiceImpl<T> implements BaseService<T> {

	protected BaseDao<T> baseDao;

	public abstract void setBaseDao(BaseDao<T> baseDao);

	public int deleteByPrimaryKey(T record) {
		return baseDao.deleteByPrimaryKey(record);
	}

	public int insert(T record) {
		return baseDao.insert(record);
	}

	public int insertSelective(T record) {
		return baseDao.insertSelective(record);
	}

	public T selectByPrimaryKey(T record) {
		return (T) baseDao.selectByPrimaryKey(record);
	}

	public int updateByPrimaryKeySelective(T record) {
		return baseDao.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(T record) {
		return baseDao.updateByPrimaryKey(record);
	}

}
