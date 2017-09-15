package org.billow.service.system;   

import javax.annotation.Resource;

import org.billow.api.system.SysUploadService;
import org.billow.dao.SysUploadDao;
import org.billow.dao.base.BaseDao;
import org.billow.model.expand.SysUploadDto;
import org.billow.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 
 * 数据字典实现类<br>
 *
 * @version 1.0
 * @author billow<br>
 * @Mail lyongtao123@126.com<br>
 * @date 2017-09-15 10:04:27
 */
@Service
public class SysUploadServiceImpl extends BaseServiceImpl<SysUploadDto> implements SysUploadService { 

	@Resource
	private SysUploadDao sysUploadDao;
	
	@Resource
	@Override
	public void setBaseDao(BaseDao<SysUploadDto> baseDao) {
		super.baseDao = this.sysUploadDao;
	}
}    