package org.billow.build.model;

import java.util.Map;

/**
 * 表结构对象
 * 
 * @author XiaoY
 * @date: 2017年6月24日 下午1:59:12
 */
public class TableModel {
	// 表名称
	private String tableName;
	// 表类型。典型的类型是 "TABLE"、"VIEW"、"SYSTEM TABLE"、"GLOBAL TEMPORARY"、"LOCAL TEMPORARY"、"ALIAS" 和 "SYNONYM"。
	private String tableType;
	// 列信息
	private Map<String, ColumnModel> columnMap;

	/**
	 * 表名称
	 * 
	 * @return
	 * @author XiaoY
	 * @date: 2017年6月24日 下午1:58:18
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * 表名称
	 * 
	 * @param tableName
	 * @author XiaoY
	 * @date: 2017年6月24日 下午1:58:21
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/**
	 * 普通列信息
	 * 
	 * @return
	 * @author XiaoY
	 * @date: 2017年6月24日 下午2:22:20
	 */
	public Map<String, ColumnModel> getColumnMap() {
		return columnMap;
	}

	/**
	 * 普通列信息
	 * 
	 * @param columnMap
	 * @author XiaoY
	 * @date: 2017年6月24日 下午2:22:24
	 */
	public void setColumnMap(Map<String, ColumnModel> columnMap) {
		this.columnMap = columnMap;
	}

	/**
	 * /** 表类型。典型的类型是 "TABLE"、"VIEW"、"SYSTEM TABLE"、"GLOBAL TEMPORARY"、"LOCAL TEMPORARY"、"ALIAS" 和 "SYNONYM"。
	 * 
	 * @return
	 * @author XiaoY
	 * @date: 2017年6月24日 下午2:41:00
	 */
	public String getTableType() {
		return tableType;
	}

	/**
	 * 表类型。典型的类型是 "TABLE"、"VIEW"、"SYSTEM TABLE"、"GLOBAL TEMPORARY"、"LOCAL TEMPORARY"、"ALIAS" 和 "SYNONYM"。
	 * 
	 * @param tableType
	 * @author XiaoY
	 * @date: 2017年6月24日 下午2:41:04
	 */
	public void setTableType(String tableType) {
		this.tableType = tableType;
	}

	@Override
	public String toString() {
		return "TableDto [tableName=" + tableName + ", tableType=" + tableType + ", columnMap=" + columnMap + "]";
	}
}
