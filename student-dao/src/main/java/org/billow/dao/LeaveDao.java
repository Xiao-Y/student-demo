package org.billow.dao;

import org.billow.dao.base.BaseDao;
import org.billow.model.expand.LeaveDto;

public interface LeaveDao extends BaseDao<LeaveDto> {

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
	LeaveDto selectByPrimaryKey(Integer id);
}