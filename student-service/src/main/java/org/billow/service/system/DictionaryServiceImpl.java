package org.billow.service.system;   

import javax.annotation.Resource;

import org.billow.api.dictionary.DictionaryService;
import org.billow.dao.DictionaryDao;
import org.billow.model.expand.DictionaryDto;
import org.billow.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 
 * 数据字典实现类<br>
 *
 * @version 1.0
 * @author billow<br>
 * @Mail lyongtao123@126.com<br>
 * @date 2017-06-26 10:36:30
 */
@Service
public class DictionaryServiceImpl extends BaseServiceImpl<DictionaryDto> implements DictionaryService { 

	@SuppressWarnings("unused")
	private DictionaryDao dictionaryDao;

	@Resource
	public void setDictionaryDao(DictionaryDao dictionaryDao) {
		this.dictionaryDao = dictionaryDao;
		super.setBaseDao(dictionaryDao);
	}
}    