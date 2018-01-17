package org.billow.build.model;

import java.util.Map;

/**
 * mapper.xml的model
 * 
 * @author XiaoY
 * @date: 2017年6月25日 下午8:25:39
 */
public class MapperDaoModel extends BaseModel {

	private String namespace;
	private String type;
	private Map<String, ColumnModel> columns;
	private String columnStr;
	private String tableName;

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Map<String, ColumnModel> getColumns() {
		return columns;
	}

	public void setColumns(Map<String, ColumnModel> columns) {
		this.columns = columns;
	}

	public String getColumnStr() {
		return columnStr;
	}

	public void setColumnStr(String columnStr) {
		this.columnStr = columnStr;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

}
