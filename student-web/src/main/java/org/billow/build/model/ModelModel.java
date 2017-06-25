package org.billow.build.model;

import java.util.List;

/**
 * model层摸model
 * 
 * @author Liuyongtao
 * @version $Id: BeanModel.java 2016年1月13日 上午10:36:42 $
 */
public class ModelModel extends BaseModel {

	/**
	 * 表名
	 */
	private String tableName;

	private String modelBasePackageName;
	private String modelBaseClazzName;

	/**
	 * 字段集合
	 */
	private List<FieldModel> fields;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public List<FieldModel> getFields() {
		return fields;
	}

	public void setFields(List<FieldModel> fields) {
		this.fields = fields;
	}

	public String getModelBasePackageName() {
		return modelBasePackageName;
	}

	public void setModelBasePackageName(String modelBasePackageName) {
		this.modelBasePackageName = modelBasePackageName;
	}

	public String getModelBaseClazzName() {
		return modelBaseClazzName;
	}

	public void setModelBaseClazzName(String modelBaseClazzName) {
		this.modelBaseClazzName = modelBaseClazzName;
	}
}
