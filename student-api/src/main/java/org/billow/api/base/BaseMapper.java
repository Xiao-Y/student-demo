package org.billow.api.base;


public interface BaseMapper<T> {
	int deleteByPrimaryKey(Integer userId);

	int insert(T record);

	int insertSelective(T record);

	T selectByPrimaryKey(Integer userId);

	int updateByPrimaryKeySelective(T record);

	int updateByPrimaryKey(T record);

}
