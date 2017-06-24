package org.billow.common.dictionary;

import java.util.ArrayList;
import java.util.List;

import org.billow.api.system.DictionaryService;
import org.billow.model.custom.CheckBox;
import org.billow.model.expand.DictionaryDto;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 数据字典工具类
 * 
 * @author XiaoY
 * @date: 2017年6月24日 上午10:54:31
 */
public class DictionaryUtils {

	@Autowired
	private DictionaryService dictionaryService;

	/**
	 * 从数据库中的数据字典表中，通过模块名称和字段名称获取下拉列表对象
	 * 
	 * @param modelCode
	 *            模块名称
	 * @param fieldCode
	 *            字段名称
	 * @return
	 * @date 2015年10月1日 下午10:29:05
	 */
	public List<CheckBox> getCheckBox(String modelCode, String fieldCode) {
		DictionaryDto dictionary = new DictionaryDto();
		dictionary.setModelCode(modelCode);
		dictionary.setFieldCode(fieldCode);
		List<DictionaryDto> list = dictionaryService.getDictionary(dictionary);
		List<CheckBox> checkBoxs = new ArrayList<CheckBox>();
		for (DictionaryDto di : list) {
			CheckBox checkBox = new CheckBox();
			checkBox.setDisplayField(di.getDisplayField());
			checkBox.setValueField(di.getValueField());

			checkBoxs.add(checkBox);
		}
		return checkBoxs;
	}

	/**
	 * 通过模块code、字段CODE、关键字查询出现一个数据字典对象
	 * 
	 * @param modelCode
	 *            模块CODE
	 * @param fieldCode
	 *            字段CODE
	 * @param valueField
	 *            关键字
	 * @return
	 * @date 2015年10月1日 下午11:42:30
	 */
	public DictionaryDto getDictionaryByModelCodeAndFieldCodeAndValueFiel(String modelCode, String fieldCode,
			String valueField) {
		DictionaryDto dictionary = new DictionaryDto();
		dictionary.setModelCode(modelCode);
		dictionary.setFieldCode(fieldCode);
		dictionary.setValueField(valueField);
		List<DictionaryDto> list = dictionaryService.getDictionary(dictionary);
		if (list != null && list.size() > 0) {
			if (list.size() > 1) {
				RuntimeException exception = new RuntimeException("查询出多个数据字典对象，无法判断具体是那个对象！");
				exception.printStackTrace();
			} else {
				return list.get(0);
			}
		}
		return null;
	}
}
