package org.billow.build.model;

/**
 * 字段model
 * 
 * @author Liuyongtao
 * @version $Id: FieldBean.java 2016年1月13日 上午10:18:53 $
 */
public class FieldModel {

	public FieldModel() {
		super();
	}

	public FieldModel(String fieldName, String fieldType, String fieldLength, String nullable) {
		super();
		this.fieldName = fieldName;
		this.fieldType = fieldType;
		this.fieldLength = fieldLength;
		this.nullable = nullable;
	}

	/**
	 * 字段名
	 */
	private String fieldName;
	/**
	 * 字段类型
	 */
	private String fieldType;
	/**
	 * 字段长度
	 */
	private String fieldLength;
	/**
	 * 默认为空
	 */
	private String nullable = "true";
	private String remarks;
	private boolean isPK;

	/**
	 * 数据库中字段名
	 */
	private String dbFieldName;

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldType() {
		return fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	public String getFieldLength() {
		return fieldLength;
	}

	public void setFieldLength(String fieldLength) {
		this.fieldLength = fieldLength;
	}

	public String getDbFieldName() {
		return dbFieldName;
	}

	public void setDbFieldName(String dbFieldName) {
		this.dbFieldName = dbFieldName;
	}

	public String getNullable() {
		return nullable;
	}

	public void setNullable(String nullable) {
		this.nullable = nullable;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public boolean getIsPK() {
		return isPK;
	}

	public void setIsPK(boolean isPK) {
		this.isPK = isPK;
	}
}
