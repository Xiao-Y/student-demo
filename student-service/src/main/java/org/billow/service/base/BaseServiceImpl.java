package org.billow.service.base;

import org.billow.api.base.BaseService;
import org.billow.dao.base.BaseDao;

public abstract class BaseServiceImpl<T> implements BaseService<T> {

	private BaseDao<T> baseDao;

	public void setBaseDao(BaseDao<T> baseDao) {
		this.baseDao = baseDao;
	}

	public int deleteByPrimaryKey(Integer TId) {
		return baseDao.deleteByPrimaryKey(TId);
	}

	public int insert(T record) {
		return baseDao.insert(record);
	}

	public int insertSelective(T record) {
		return baseDao.insertSelective(record);
	}

	public T selectByPrimaryKey(Integer TId) {
		return (T) baseDao.selectByPrimaryKey(TId);
	}

	public int updateByPrimaryKeySelective(T record) {
		return baseDao.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(T record) {
		return baseDao.updateByPrimaryKey(record);
	}

}
