package org.billow.service.system;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.billow.api.system.DictionaryService;
import org.billow.dao.DictionaryDao;
import org.billow.model.expand.DictionaryDto;
import org.billow.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class DictionaryServiceImpl extends BaseServiceImpl<DictionaryDto> implements DictionaryService {

	private DictionaryDao dictionaryDao;

	@Resource
	public void setDictionaryDao(DictionaryDao dictionaryDao) {
		this.dictionaryDao = dictionaryDao;
		super.setBaseDao(dictionaryDao);
	}

	@Override
	public List<DictionaryDto> getDictionary(DictionaryDto dictionary) {
		List<DictionaryDto> list = dictionaryDao.getDictionary(dictionary);
		return list;
	}

	@Override
	public List<DictionaryDto> getModelNameCheckBox() {
		return dictionaryDao.getModelNameCheckBox();
	}

	@Override
	public List<DictionaryDto> getFieldNameCheckBox(String modelCode) {
		return dictionaryDao.getFieldNameCheckBox(modelCode);
	}

	@Override
	public void updateDictionary(DictionaryDto dictionary) {
		dictionaryDao.updateDictionary(dictionary);
	}

	@Override
	public boolean deleteDictionaryIds(List<DictionaryDto> dictionaryList) {
		if (dictionaryList != null && dictionaryList.size() > 0) {
			List<Integer> ids = new ArrayList<>();
			for (DictionaryDto d : dictionaryList) {
				ids.add(d.getId());
			}
			try {
				dictionaryDao.deleteDictionaryIds(ids);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}

	@Override
	public boolean deleteDictionaryModelOrField(DictionaryDto dictionary) {
		try {
			dictionaryDao.deleteDictionaryModelOrField(dictionary);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public long getDictionaryCount(DictionaryDto dictionary) {
		return dictionaryDao.getDictionaryCount(dictionary);
	}
}
