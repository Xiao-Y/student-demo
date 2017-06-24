package org.billow.api.system;

import java.util.List;

import org.billow.api.base.BaseService;
import org.billow.model.expand.DictionaryDto;

public interface DictionaryService extends BaseService<DictionaryDto> {

	/**
	 * 根据条件查询出现所能符合条件的数据字典
	 * 
	 * @param dictionary
	 *            查询条件
	 * @return
	 */
	List<DictionaryDto> getDictionary(DictionaryDto dictionary);

	/**
	 * 获取模块的下拉列表
	 * 
	 * @return
	 */
	List<DictionaryDto> getModelNameCheckBox();

	/**
	 * 通过模块Code获取字段的下拉列表
	 * 
	 * @param modelCode
	 *            模块Code
	 * @return
	 */
	List<DictionaryDto> getFieldNameCheckBox(String modelCode);

	/**
	 * 更新数据字典的模块和字段
	 * 
	 * @param dictionary
	 * <br/>
	 *            更新模块名和Code<br/>
	 *            更新字段名和Code<br/>
	 */
	void updateDictionary(DictionaryDto dictionary);

	/**
	 * 根据一组id删除对应对象
	 * 
	 * @param dictionaryList
	 */
	boolean deleteDictionaryIds(List<DictionaryDto> dictionaryList);

	/**
	 * 跟根据模块codet和fieldCode删除数据字典
	 * 
	 * @param modelCode
	 *            模块code
	 * @param fieldCode
	 *            字段code
	 * @return
	 */
	boolean deleteDictionaryModelOrField(DictionaryDto dictionary);

	/**
	 * 根据条件统计出现记录数
	 * 
	 * @param dictionary
	 * @return
	 */
	long getDictionaryCount(DictionaryDto dictionary);

}
