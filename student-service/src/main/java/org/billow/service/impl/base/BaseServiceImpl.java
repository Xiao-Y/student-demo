package org.billow.service.impl.base;

import com.billow.base.mapper.BaseMapper;
import com.billow.base.service.BaseService;

public abstract class BaseServiceImpl<T> implements BaseService<T> {

	private BaseMapper<T> baseMapper;

	public void setBaseMapper(BaseMapper<T> baseMapper) {
		this.baseMapper = baseMapper;
	}

	public int deleteByPrimaryKey(Integer TId) {
		return baseMapper.deleteByPrimaryKey(TId);
	}

	public int insert(T record) {
		return baseMapper.insert(record);
	}

	public int insertSelective(T record) {
		return baseMapper.insertSelective(record);
	}

	public T selectByPrimaryKey(Integer TId) {
		return (T) baseMapper.selectByPrimaryKey(TId);
	}

	public int updateByPrimaryKeySelective(T record) {
		return baseMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(T record) {
		return baseMapper.updateByPrimaryKey(record);
	}

}
