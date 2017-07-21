package org.billow.model.domain;

import java.io.Serializable;
import java.util.Date;

import org.billow.model.base.BaseModel;

/**
 * 
 * 数据字典数据库模型<br>
 * 
 * 对应的表名：t_dictionary
 * 
 * @version 1.0
 * @author billow<br>
 * @Mail lyongtao123@126.com<br>
 * @date 2017-06-26 10:47:42
 */
public class DictionaryBase extends BaseModel implements Serializable {

	public DictionaryBase(String id) {
		super();
		this.id = id;
	}

	public DictionaryBase() {
		super();
	}

	private static final long serialVersionUID = -8923236551714644125L;
	//
	private String notice;
	//
	private String fieldCode;
	//
	private String displayField;
	//
	private String fieldName;
	//
	private Date createTime;
	//
	private String id;
	//
	private String modelCode;
	//
	private String valueField;
	//
	private Date updateTime;
	//
	private String modelName;

	/**
	 * 
	 * 
	 * @return
	 * @author billow<br>
	 * @date: 2017-06-26 10:47:42
	 */
	public String getNotice() {
		return this.notice;
	}

	/**
	 * 
	 * 
	 * @param notice
	 * @author billow<br>
	 * @date: 2017-06-26 10:47:42
	 */
	public void setNotice(String notice) {
		this.notice = notice;
	}

	/**
	 * 
	 * 
	 * @return
	 * @author billow<br>
	 * @date: 2017-06-26 10:47:42
	 */
	public String getFieldCode() {
		return this.fieldCode;
	}

	/**
	 * 
	 * 
	 * @param fieldCode
	 * @author billow<br>
	 * @date: 2017-06-26 10:47:42
	 */
	public void setFieldCode(String fieldCode) {
		this.fieldCode = fieldCode;
	}

	/**
	 * 
	 * 
	 * @return
	 * @author billow<br>
	 * @date: 2017-06-26 10:47:42
	 */
	public String getDisplayField() {
		return this.displayField;
	}

	/**
	 * 
	 * 
	 * @param displayField
	 * @author billow<br>
	 * @date: 2017-06-26 10:47:42
	 */
	public void setDisplayField(String displayField) {
		this.displayField = displayField;
	}

	/**
	 * 
	 * 
	 * @return
	 * @author billow<br>
	 * @date: 2017-06-26 10:47:42
	 */
	public String getFieldName() {
		return this.fieldName;
	}

	/**
	 * 
	 * 
	 * @param fieldName
	 * @author billow<br>
	 * @date: 2017-06-26 10:47:42
	 */
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	/**
	 * 
	 * 
	 * @return
	 * @author billow<br>
	 * @date: 2017-06-26 10:47:42
	 */
	public Date getCreateTime() {
		return this.createTime;
	}

	/**
	 * 
	 * 
	 * @param createTime
	 * @author billow<br>
	 * @date: 2017-06-26 10:47:42
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 
	 * 
	 * @return
	 * @author billow<br>
	 * @date: 2017-06-26 10:47:42
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * 
	 * 
	 * @param id
	 * @author billow<br>
	 * @date: 2017-06-26 10:47:42
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 
	 * 
	 * @return
	 * @author billow<br>
	 * @date: 2017-06-26 10:47:42
	 */
	public String getModelCode() {
		return this.modelCode;
	}

	/**
	 * 
	 * 
	 * @param modelCode
	 * @author billow<br>
	 * @date: 2017-06-26 10:47:42
	 */
	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
	}

	/**
	 * 
	 * 
	 * @return
	 * @author billow<br>
	 * @date: 2017-06-26 10:47:42
	 */
	public String getValueField() {
		return this.valueField;
	}

	/**
	 * 
	 * 
	 * @param valueField
	 * @author billow<br>
	 * @date: 2017-06-26 10:47:42
	 */
	public void setValueField(String valueField) {
		this.valueField = valueField;
	}

	/**
	 * 
	 * 
	 * @return
	 * @author billow<br>
	 * @date: 2017-06-26 10:47:42
	 */
	public Date getUpdateTime() {
		return this.updateTime;
	}

	/**
	 * 
	 * 
	 * @param updateTime
	 * @author billow<br>
	 * @date: 2017-06-26 10:47:42
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * 
	 * 
	 * @return
	 * @author billow<br>
	 * @date: 2017-06-26 10:47:42
	 */
	public String getModelName() {
		return this.modelName;
	}

	/**
	 * 
	 * 
	 * @param modelName
	 * @author billow<br>
	 * @date: 2017-06-26 10:47:42
	 */
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	/**
	 * 主键toString 非主键不允许添加
	 */
	@Override
	public String toString() {
		return "PK[id = " + id + "]";
	}

}