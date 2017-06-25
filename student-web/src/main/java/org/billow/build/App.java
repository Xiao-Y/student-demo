package org.billow.build;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.billow.build.model.TableModel;
import org.billow.build.table.ReadDB;

/**
 * 自动代码生成器
 * 
 * @author liuyongtao
 * 
 * @date 2017年6月23日 上午8:57:56
 */
public class App {

	public void run() throws Exception {
		InputStream in = getClass().getClassLoader().getResourceAsStream("druid.properties");
		Properties properties = new Properties();
		properties.load(in);
		String url = properties.getProperty("spring.datasource.url");
		String username = properties.getProperty("spring.datasource.username");
		String password = properties.getProperty("spring.datasource.password");
		String driverClassName = properties.getProperty("spring.datasource.driver-class-name");

		Class.forName(driverClassName);
		Connection connection = DriverManager.getConnection(url, username, password);
		ReadDB readDB = new ReadDB();
		DatabaseMetaData metaData = connection.getMetaData();
		Map<String, TableModel> tables = readDB.getTables(metaData);
		Set<String> keySet = tables.keySet();
		for (String key : keySet) {
			TableModel tableDto = tables.get(key);
			System.out.println(tableDto);
		}
	}

	public static void main(String[] args) throws Exception {
		App app = new App();
		app.run();
	}
}
