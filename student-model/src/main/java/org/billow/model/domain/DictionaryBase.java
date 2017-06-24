package org.billow.model.domain;

import java.io.Serializable;

import org.billow.model.base.BaseModel;

/**
 * 数据字典
 * 
 * @author XiaoY
 * @date: 2015年9月20日 下午1:23:15
 */
public class DictionaryBase extends BaseModel implements Serializable {

	private static final long serialVersionUID = 8608033491914451977L;

	private Integer id;
	// 模块的名称
	private String modelName;
	// 模块的Code
	private String modelCode;

	// 字段名称
	private String fieldName;
	// 字段Code
	private String fieldCode;

	// 存储的值
	private String valueField;

	// 显示的值
	private String displayField;

	// 备注
	private String notice;

	public DictionaryBase(String modelName, String modelCode) {
		this.modelName = modelName;
		this.modelCode = modelCode;
	}

	public DictionaryBase(String fieldName, String fieldCode, String modelCode) {
		super();
		this.fieldName = fieldName;
		this.fieldCode = fieldCode;
		this.modelCode = modelCode;
	}

	public DictionaryBase() {
	}

//	@Column(name = "MODEL_NAME", nullable = false, length = 100)
	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

//	@Column(name = "FIELD_NAME", nullable = false, length = 100)
	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

//	@Column(name = "VALUE_FIELD", nullable = false, length = 100)
	public String getValueField() {
		return valueField;
	}

	public void setValueField(String valueField) {
		this.valueField = valueField;
	}

//	@Column(name = "DISPLAY_FIELD", length = 100)
	public String getDisplayField() {
		return displayField;
	}

	public void setDisplayField(String displayField) {
		this.displayField = displayField;
	}

//	@Column(name = "NOTICE", length = 200)
	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

//	@Column(name = "MODEL_CODE", nullable = false, length = 100)
	public String getModelCode() {
		return modelCode;
	}

	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
	}

//	@Column(name = "FIELD_CODE", nullable = false, length = 100)
	public String getFieldCode() {
		return fieldCode;
	}

	public void setFieldCode(String fieldCode) {
		this.fieldCode = fieldCode;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Dictionary [modelName=" + modelName + ", modelCode=" + modelCode + ", fieldName=" + fieldName
				+ ", fieldCode=" + fieldCode + ", valueField=" + valueField + ", displayField=" + displayField
				+ ", notice=" + notice + ", toString()=" + super.toString() + "]";
	}
}
