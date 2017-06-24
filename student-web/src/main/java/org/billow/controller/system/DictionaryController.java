package org.billow.controller.system;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.billow.api.system.DictionaryService;
import org.billow.common.annotation.SystemControllerLog;
import org.billow.model.custom.JsonResult;
import org.billow.model.expand.DictionaryDto;
import org.billow.utils.RequestUtils;
import org.billow.utils.constant.LogParamType;
import org.billow.utils.constant.MessageTipsCst;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;

/**
 * 数据字典
 * 
 * @author XiaoY
 * @date: 2015年9月19日 下午9:38:33
 */
@Controller
@RequestMapping("/dictionary")
public class DictionaryController {

	@Resource
	private DictionaryService dictionaryService;

	@RequestMapping("/index")
	public String index() {
		return "background/resourcesmsg/dictionary/index";
	}

	@ResponseBody
	@RequestMapping("/getDictionary")
	public JsonResult getDictionary(HttpServletRequest request) {
		String limit = RequestUtils.getStringParameter(request, "limit");
		String start = RequestUtils.getStringParameter(request, "start");

		JsonResult json = new JsonResult();
		DictionaryDto dictionary = new DictionaryDto();
		// dictionary.setLimit(limit);
		// dictionary.setStart(start);

		List<DictionaryDto> list = dictionaryService.getDictionary(dictionary);
		long count = dictionaryService.getDictionaryCount(dictionary);
		json.setRoot(list);
		json.setTotal(count);
		json.setSuccess(true);
		return json;
	}

	/**
	 * 获取模块的下拉列表
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getModelNameCheckBox")
	public JsonResult getModelNameCheckBox() {
		JsonResult json = new JsonResult();
		List<DictionaryDto> list = dictionaryService.getModelNameCheckBox();
		json.setSuccess(true);
		json.setRoot(list);
		return json;
	}

	/**
	 * 获取字段的下拉列表
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getFieldNameCheckBox")
	public JsonResult getFieldNameCheckBox(@RequestParam(value = "modelCode", required = false) String modelCode) {
		JsonResult json = new JsonResult();
		List<DictionaryDto> list = dictionaryService.getFieldNameCheckBox(modelCode);
		json.setSuccess(true);
		json.setRoot(list);
		return json;
	}

	@ResponseBody
	@RequestMapping("/getKeyValue")
	public JsonResult getKeyValue(@RequestParam(value = "modelCode", required = false) String modelCode,
			@RequestParam(value = "fieldCode", required = false) String fieldCode) {
		JsonResult json = new JsonResult();
		json.setSuccess(true);
		if (StringUtils.isEmpty(modelCode) || StringUtils.isEmpty(fieldCode)) {
			json.setRoot(null);
			return json;
		} else {
			DictionaryDto dictionary = new DictionaryDto();
			dictionary.setModelCode(modelCode);
			dictionary.setFieldCode(fieldCode);
			List<DictionaryDto> list = dictionaryService.getDictionary(dictionary);
			json.setRoot(list);
		}
		return json;
	}

	/**
	 * 维护数据字典
	 */
	@ResponseBody
	@RequestMapping("/saveDictionary")
	@SystemControllerLog(module = LogParamType.RESOURCES_MODAL, function = LogParamType.RESOURCES_FUNCTION_DICTIONARY, operation = LogParamType.ADD)
	public JsonResult saveDictionary(HttpServletRequest request) {
		// 模块选中的下拉列表Code
		String modelCodeBox = RequestUtils.getStringParameter(request, "modelCodeBox");
		// 修改的模块Code
		String modelCode = RequestUtils.getStringParameter(request, "modelCode");
		// 选中的模块名称
		String modelName = RequestUtils.getStringParameter(request, "modelName");
		// 新模块名称
		String newModelName = RequestUtils.getStringParameter(request, "newModelName");
		// 选中的字段下拉列表Code
		String fieldCodeBox = RequestUtils.getStringParameter(request, "fieldCodeBox");
		// 修改的字段Code
		String fieldCode = RequestUtils.getStringParameter(request, "fieldCode");
		// 选中的字段名称
		String fieldName = RequestUtils.getStringParameter(request, "fieldName");
		// 新字段名称
		String newFieldName = RequestUtils.getStringParameter(request, "newFieldName");

		// 所有修改和添加新的键值对
		String keyValues = RequestUtils.getStringParameter(request, "keyValues");

		List<DictionaryDto> dictionaryList = JSONArray.parseArray(keyValues, DictionaryDto.class);

		JsonResult json = new JsonResult();
		if (dictionaryList != null && dictionaryList.size() > 0) {// 更改了键值对
			for (DictionaryDto d : dictionaryList) {
				// 判断是否是新添加的
				// if (d.getCreateTime() == null) {
				// d.setId(UUID.randomUUID().toString());
				// d.setCreateTime(new Date());
				// }

				if (StringUtils.isEmpty(modelCodeBox) || !StringUtils.isEmpty(newModelName)) {
					d.setModelName(newModelName);
				} else {
					d.setModelName(modelName);
				}

				if (StringUtils.isEmpty(fieldCodeBox) || !StringUtils.isEmpty(newFieldName)) {
					d.setFieldName(newFieldName);
				} else {
					d.setFieldName(fieldName);
				}

				d.setModelCode(modelCode);
				d.setFieldCode(fieldCode);
				// d.setUpdateTime(new Date());
			}
			try {
				// dictionaryService.saveOrUpdateObjectCollection(dictionaryList);
				json.setMessage(MessageTipsCst.SAVE_SUCCESS);
				json.setSuccess(true);
			} catch (Exception e) {
				e.printStackTrace();
				json.setMessage(MessageTipsCst.SERVICE_ERRER);
			}
		} else {// 没有更改键值对
			DictionaryDto d = new DictionaryDto();

			if (StringUtils.isEmpty(modelCodeBox) || !StringUtils.isEmpty(newModelName)) {
				d.setModelName(newModelName);
			} else {
				d.setModelName(modelName);
			}
			d.setModelCode(modelCode);
			if (StringUtils.isEmpty(fieldCodeBox) || !StringUtils.isEmpty(newFieldName)) {
				d.setFieldName(newFieldName);
			} else {
				d.setFieldName(fieldName);
			}
			d.setFieldCode(fieldCode);

			try {
				dictionaryService.updateDictionary(d);
				json.setMessage(MessageTipsCst.SAVE_SUCCESS);
				json.setSuccess(true);
			} catch (Exception e) {
				e.printStackTrace();
				json.setMessage(MessageTipsCst.SERVICE_ERRER);
			}
		}
		return json;
	}

	/**
	 * 删除键值对
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deleteDictionaryKeyValue")
	@SystemControllerLog(module = LogParamType.RESOURCES_MODAL, function = LogParamType.RESOURCES_FUNCTION_DICTIONARY, operation = LogParamType.DELETE, note = LogParamType.DELETE_KEY_VALUE)
	public JsonResult deleteDictionaryKeyValue(HttpServletRequest request) {
		JsonResult json = new JsonResult();
		// 所有要删除的键值对
		String keyValues = RequestUtils.getStringParameter(request, "keyValues");

		List<DictionaryDto> dictionaryList = JSONArray.parseArray(keyValues, DictionaryDto.class);
		boolean flag = dictionaryService.deleteDictionaryIds(dictionaryList);
		json.setSuccess(flag);
		if (flag) {
			json.setMessage(MessageTipsCst.DELETE_SUCCESS);
		} else {
			json.setMessage(MessageTipsCst.DELETE_FAILURE);
		}
		return json;
	}

	/**
	 * 删除字段（跟模块名和字段名）
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deleteDictionaryField")
	@SystemControllerLog(module = LogParamType.RESOURCES_MODAL, function = LogParamType.RESOURCES_FUNCTION_DICTIONARY, operation = LogParamType.DELETE, note = LogParamType.DELETE_KEY_VALUE)
	public JsonResult deleteDictionaryField(HttpServletRequest request) {
		JsonResult json = new JsonResult();
		// 模块选中的下拉列表Code
		String modelCodeBox = RequestUtils.getStringParameter(request, "modelCodeBox");
		// 选中的字段下拉列表Code
		String fieldCodeBox = RequestUtils.getStringParameter(request, "fieldCodeBox");
		DictionaryDto dictionary = new DictionaryDto();
		dictionary.setModelCode(modelCodeBox);
		dictionary.setFieldCode(fieldCodeBox);
		boolean flag = dictionaryService.deleteDictionaryModelOrField(dictionary);
		json.setSuccess(flag);
		if (flag) {
			json.setMessage(MessageTipsCst.DELETE_SUCCESS);
		} else {
			json.setMessage(MessageTipsCst.DELETE_FAILURE);
		}
		return json;
	}

	/**
	 * 删除模块（跟模块名）
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deleteDictionaryModel")
	@SystemControllerLog(module = LogParamType.RESOURCES_MODAL, function = LogParamType.RESOURCES_FUNCTION_DICTIONARY, operation = LogParamType.DELETE, note = LogParamType.DELETE_MODEL)
	public JsonResult deleteDictionaryModel(HttpServletRequest request) {
		JsonResult json = new JsonResult();
		// 模块选中的下拉列表Code
		String modelCodeBox = RequestUtils.getStringParameter(request, "modelCodeBox");
		DictionaryDto dictionary = new DictionaryDto();
		dictionary.setModelCode(modelCodeBox);
		boolean flag = dictionaryService.deleteDictionaryModelOrField(dictionary);
		json.setSuccess(flag);
		if (flag) {
			json.setMessage(MessageTipsCst.DELETE_SUCCESS);
		} else {
			json.setMessage(MessageTipsCst.DELETE_FAILURE);
		}
		return json;
	}
}
