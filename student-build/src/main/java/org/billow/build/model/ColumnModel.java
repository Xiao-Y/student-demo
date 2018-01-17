package org.billow.build.model;

/**
 * 列结构对象
 * 
 * @author XiaoY
 * @date: 2017年6月24日 下午1:59:30
 */
public class ColumnModel {
	// 列名
	private String columnName;
	// 列数据类型
	private String columnType;
	// 列长
	private int datasize;
	// 小数部分的位数
	private int digits;
	// 是否允许为空
	private int nullable;
	// 列注释
	private String remarks;
	// 是否是主键
	private boolean isPk;
	// 对应实体类的名称
	private String fieldName;
	// 对应mybatis类型
	private String mybatisType;

	/**
	 * 列名
	 * 
	 * @return
	 * @author XiaoY
	 * @date: 2017年6月24日 下午1:44:34
	 */
	public String getColumnName() {
		return columnName;
	}

	/**
	 * 列名
	 * 
	 * @param columnName
	 * @author XiaoY
	 * @date: 2017年6月24日 下午1:44:38
	 */
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	/**
	 * 列数据类型
	 * 
	 * @return
	 * @author XiaoY
	 * @date: 2017年6月24日 下午1:44:48
	 */
	public String getColumnType() {
		return columnType;
	}

	/**
	 * 列数据类型
	 * 
	 * @param columnType
	 * @author XiaoY
	 * @date: 2017年6月24日 下午1:44:53
	 */
	public void setColumnType(String columnType) {
		this.columnType = columnType;
	}

	/**
	 * datasize
	 * 
	 * @return
	 * @author XiaoY
	 * @date: 2017年6月24日 下午1:45:01
	 */
	public int getDatasize() {
		return datasize;
	}

	/**
	 * datasize
	 * 
	 * @param datasize
	 * @author XiaoY
	 * @date: 2017年6月24日 下午1:45:04
	 */
	public void setDatasize(int datasize) {
		this.datasize = datasize;
	}

	/**
	 * 小数部分的位数
	 * 
	 * @return
	 * @author XiaoY
	 * @date: 2017年6月24日 下午1:45:16
	 */
	public int getDigits() {
		return digits;
	}

	/**
	 * 小数部分的位数
	 * 
	 * @param digits
	 * @author XiaoY
	 * @date: 2017年6月24日 下午1:45:20
	 */
	public void setDigits(int digits) {
		this.digits = digits;
	}

	/**
	 * 是否允许为空
	 * 
	 * @return
	 * @author XiaoY
	 * @date: 2017年6月24日 下午1:45:31
	 */
	public int getNullable() {
		return nullable;
	}

	/**
	 * 是否允许为空
	 * 
	 * @param nullable
	 * @author XiaoY
	 * @date: 2017年6月24日 下午1:45:34
	 */
	public void setNullable(int nullable) {
		this.nullable = nullable;
	}

	/**
	 * 列注释
	 * 
	 * @return
	 * @author XiaoY
	 * @date: 2017年6月24日 下午1:44:19
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * 列注释
	 * 
	 * @param remarks
	 * @author XiaoY
	 * @date: 2017年6月24日 下午1:44:16
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	/**
	 * 是否是主键
	 * 
	 * @return
	 * @author XiaoY
	 * @date: 2017年6月24日 下午2:26:15
	 */
	public boolean getIsPk() {
		return isPk;
	}

	/**
	 * 是否是主键
	 * 
	 * @param isPk
	 * @author XiaoY
	 * @date: 2017年6月24日 下午2:26:19
	 */
	public void setIsPk(boolean isPk) {
		this.isPk = isPk;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getMybatisType() {
		return mybatisType;
	}

	public void setMybatisType(String mybatisType) {
		this.mybatisType = mybatisType;
	}

	@Override
	public String toString() {
		return "ColumnModel [columnName=" + columnName + ", columnType=" + columnType + ", datasize=" + datasize + ", digits=" + digits
				+ ", nullable=" + nullable + ", remarks=" + remarks + ", isPk=" + isPk + ", fieldName=" + fieldName + ", mybatisType=" + mybatisType
				+ "]";
	}
}
