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
	// 主键构造器参数
	private String constructor;
	// 主键构造器参数(不带类型的)
	private String constructorNo;
	// 主键toString
	private String pkToString;

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

	/**
	 * 主键构造器参数
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @return
	 * 
	 * @date 2017年6月27日 上午10:31:12
	 */
	public String getConstructor() {
		return constructor;
	}

	/**
	 * 主键构造器参数
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @param constructor
	 * 
	 * @date 2017年6月27日 上午10:31:15
	 */
	public void setConstructor(String constructor) {
		this.constructor = constructor;
	}

	/**
	 * 主键构造器参数(不带类型的)
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @return
	 * 
	 * @date 2017年6月27日 上午11:14:02
	 */
	public String getConstructorNo() {
		return constructorNo;
	}

	/**
	 * 主键构造器参数(不带类型的)
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @param constructorNo
	 * 
	 * @date 2017年6月27日 上午11:14:05
	 */
	public void setConstructorNo(String constructorNo) {
		this.constructorNo = constructorNo;
	}

	/**
	 * 主键toString
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @return
	 * 
	 * @date 2017年7月20日 上午10:32:49
	 */
	public String getPkToString() {
		return pkToString;
	}

	/**
	 * 主键toString
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @param pkToString
	 * 
	 * @date 2017年7月20日 上午10:32:52
	 */
	public void setPkToString(String pkToString) {
		this.pkToString = pkToString;
	}
}
