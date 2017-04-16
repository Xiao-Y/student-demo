package org.billow.api.base;

public interface BaseService<T> {

	/**
	 * 根据主键删除
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @param id
	 *            主键
	 * @return
	 * 
	 * @date 2017年4月14日 下午4:09:45
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * 保存对象所有属性值
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @param record
	 * @return
	 * 
	 * @date 2017年4月14日 下午4:10:00
	 */
	int insert(T record);

	/**
	 * 保存对象中属性值不为空的
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @param record
	 * @return
	 * 
	 * @date 2017年4月14日 下午4:10:46
	 */
	int insertSelective(T record);

	/**
	 * 根据主键查询
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @param id
	 *            主键
	 * @return
	 * 
	 * @date 2017年4月14日 下午4:12:18
	 */
	T selectByPrimaryKey(Integer id);

	/**
	 * 更新对象中属性不为空的
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @param record
	 * @return
	 * 
	 * @date 2017年4月14日 下午4:13:31
	 */
	int updateByPrimaryKeySelective(T record);

	/**
	 * 更新对象所有属性值
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @param record
	 * @return
	 * 
	 * @date 2017年4月14日 下午4:14:23
	 */
	int updateByPrimaryKey(T record);
}
