package org.billow.build.table;

import org.billow.build.Utils;
import org.billow.build.model.ColumnModel;
import org.billow.build.model.TableModel;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class ReadDB {

	// ----------单例模式：懒汉式，线程安全----start---------
	private static ReadDB DB;

	public static synchronized ReadDB getInstance() {
		if (DB == null) {
			DB = new ReadDB();
		}
		return DB;
	}

	// 表的前缀
	static List<String> tablePrefix = new ArrayList<>();
	static {
		tablePrefix.add("t_");
	}

	/**
	 * 获取指定表的列信息
	 * 
	 * @param metaData
	 * @param tableName
	 *            表名
	 * @param tablePK
	 *            主键列表信息
	 * @param columnMap
	 *            普通列信息
	 * @param columnPKMap
	 *            主键列信息
	 * @return
	 * @throws Exception
	 * @author XiaoY
	 * @date: 2017年6月24日 下午2:16:26
	 */
	public static Map<String, ColumnModel> getColumns(DatabaseMetaData metaData, String tableName) throws Exception {
		if (metaData == null) {
			metaData = ReadDB.getInstance().run();
		}
		// 根据表名提前表里面信息：
		List<String> pk = new ArrayList<>();
		// 查询主键
		ResultSet pkRSet = metaData.getPrimaryKeys(null, null, tableName);
		while (pkRSet.next()) {
			// 主键列
			pk.add(pkRSet.getString("COLUMN_NAME"));
		}
		// 获取表中列表信息
		Map<String, ColumnModel> columnMap = new HashMap<>();
		// 查询列
		ResultSet colRet = metaData.getColumns(null, "%", tableName, "%");
		while (colRet.next()) {
			ColumnModel column = new ColumnModel();
			String columnName = colRet.getString("COLUMN_NAME");
			column.setColumnName(columnName);
			column.setColumnType(colRet.getString("TYPE_NAME"));
			column.setDatasize(colRet.getInt("COLUMN_SIZE"));
			column.setDigits(colRet.getInt("DECIMAL_DIGITS"));
			column.setNullable(colRet.getInt("NULLABLE"));
			column.setRemarks(colRet.getString("REMARKS"));
			if (pk.contains(columnName)) {
				column.setIsPk(true);
			} else {
				column.setIsPk(false);
			}
			columnMap.put(columnName, column);
		}
		return columnMap;
	}

	/**
	 * 获取表结构（包含列结构）
	 * 
	 * @param metaData
	 * @return
	 * @throws Exception
	 * @author XiaoY
	 * @date: 2017年6月24日 下午2:46:56
	 */
	public Map<String, TableModel> getTables(DatabaseMetaData metaData) throws Exception {
		if (metaData == null) {
			metaData = ReadDB.getInstance().run();
		}
		Map<String, TableModel> tables = new HashMap<>();
		ResultSet rs = metaData.getTables(null, convertDatabaseCharsetType("root", "mysql"), null, new String[] { "TABLE", "VIEW" });
		while (rs.next()) {
			TableModel tableDto = new TableModel();
			String tableType = rs.getString("TABLE_TYPE");
			tableDto.setTableType(tableType);
			if (tableType != null && (tableType.equalsIgnoreCase("TABLE") || tableType.equalsIgnoreCase("VIEW"))) {
				String tableName = rs.getString("TABLE_NAME").toLowerCase();
				tableDto.setTableName(tableName);
				// 获取指定前缀的表结构
				if (!checkTable(tableName)) {
					continue;
				}
				// 根据表名提取表里面信息：
				Map<String, ColumnModel> columns = getColumns(metaData, tableName);
				tableDto.setColumnMap(columns);
				tables.put(tableName, tableDto);
			}
		}
		return tables;
	}

	/**
	 * 获取指定前缀的表结构
	 * 
	 * @param tableName
	 * @return
	 * @author XiaoY
	 * @date: 2017年6月24日 下午2:46:39
	 */
	public static boolean checkTable(String tableName) {
		boolean flag = false;
		for (String prefix : tablePrefix) {
			if (tableName.startsWith(prefix)) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	public static String convertDatabaseCharsetType(String in, String type) {
		String dbUser;
		if (in != null) {
			if (type.equals("oracle")) {
				dbUser = in.toUpperCase();
			} else if (type.equals("postgresql")) {
				dbUser = "public";
			} else if (type.equals("mysql")) {
				dbUser = null;
			} else if (type.equals("mssqlserver")) {
				dbUser = null;
			} else if (type.equals("db2")) {
				dbUser = in.toUpperCase();
			} else {
				dbUser = in;
			}
		} else {
			dbUser = "public";
		}
		return dbUser;
	}

	public DatabaseMetaData run() throws Exception {
		Properties config = Utils.readPropertiesFile();
		String druid = (String) config.get("druid");
		FileInputStream in = new FileInputStream(druid);
		Properties properties = new Properties();
		properties.load(in);
		String url = properties.getProperty("spring.datasource.url");
		String username = properties.getProperty("spring.datasource.username");
		String password = properties.getProperty("spring.datasource.password");
		String driverClassName = properties.getProperty("spring.datasource.driver-class-name");

		Class.forName(driverClassName);
		Connection connection = DriverManager.getConnection(url, username, password);
		return connection.getMetaData();
	}
}
