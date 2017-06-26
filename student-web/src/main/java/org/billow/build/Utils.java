package org.billow.build;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Utils {

	/**
	 * 加载资源文件
	 * 
	 * @param filename
	 * 
	 * @date 2015年8月25日上午9:23:04
	 */
	public static Properties readPropertiesFile() {
		Properties properties = new Properties();
		try {
			InputStream in = Utils.class.getClassLoader().getResourceAsStream("org/billow/build/config.properties");
			properties.load(in);
			in.close(); // 关闭流
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}

	/**
	 * 获取config.xml的路径
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @return
	 * 
	 * @date 2017年6月26日 上午9:10:21
	 */
	public static String getConfigPath() {
		Properties properties = Utils.readPropertiesFile();
		String configPath = (String) properties.get("configPath");
		System.out.println("config.xml的路径：" + configPath);
		return configPath;
	}

	/**
	 * 获取config.xml的路径
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @return
	 * 
	 * @date 2017年6月26日 上午9:10:21
	 */
	public static String getFltPath() {
		Properties properties = Utils.readPropertiesFile();
		String fltPath = (String) properties.get("ftlPath");
		System.out.println("flt的路径：" + fltPath);
		return fltPath;
	}

	/**
	 * 获取生成文件的基路径
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @return
	 * 
	 * @date 2017年6月26日 上午9:10:21
	 */
	public static String getOutPath() {
		Properties properties = Utils.readPropertiesFile();
		String outPath = (String) properties.get("outPath") + "\\";
		System.out.println("outPath的路径：" + outPath);
		return outPath;
	}
}
