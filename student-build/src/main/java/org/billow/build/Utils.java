package org.billow.build;

import java.io.InputStream;
import java.util.Properties;

public class Utils {

	static Properties properties = new Properties();

	/**
	 * 加载资源文件
	 * 
	 * @param filename
	 * 
	 * @date 2015年8月25日上午9:23:04
	 */
	public static Properties readPropertiesFile() {
		if (properties == null || properties.isEmpty()) {
			try {
				InputStream in = Utils.class.getResourceAsStream("/build/config.properties");
				properties.load(in);
				in.close(); // 关闭流
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("读取config.properties");
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
	 * 获取flt的路径的路径
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
		String fltPath = (String) properties.get("FtlPath");
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
	@Deprecated
	public static String getOutPath() {
		Properties properties = Utils.readPropertiesFile();
		String outPath = (String) properties.get("outPath") + "\\";
		System.out.println("outPath的路径：" + outPath);
		return outPath;
	}

	/**
	 * 获取ServiceOutPath的路径的路径
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @return
	 * 
	 * @date 2017年6月26日 上午9:10:21
	 */
	public static String getServiceOutPath() {
		Properties properties = Utils.readPropertiesFile();
		String ServiceOutPath = (String) properties.get("ServiceOutPath");
		System.out.println("ServiceOutPath的路径：" + ServiceOutPath);
		return ServiceOutPath;
	}

	/**
	 * 获取ApiOutPath的路径的路径
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @return
	 * 
	 * @date 2017年6月26日 上午9:10:21
	 */
	public static String getApiOutPath() {
		Properties properties = Utils.readPropertiesFile();
		String ApiOutPath = (String) properties.get("ApiOutPath");
		System.out.println("ApiOutPath的路径：" + ApiOutPath);
		return ApiOutPath;
	}

	/**
	 * 获取ModelOutPath的路径的路径
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @return
	 * 
	 * @date 2017年6月26日 上午9:10:21
	 */
	public static String getModelOutPath() {
		Properties properties = Utils.readPropertiesFile();
		String ModelOutPath = (String) properties.get("ModelOutPath");
		System.out.println("ModelOutPath的路径：" + ModelOutPath);
		return ModelOutPath;
	}

	/**
	 * 获取DaoOutPath的路径的路径
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @return
	 * 
	 * @date 2017年6月26日 上午9:10:21
	 */
	public static String getDaoOutPath() {
		Properties properties = Utils.readPropertiesFile();
		String DaoOutPath = (String) properties.get("DaoOutPath");
		System.out.println("DaoOutPath的路径：" + DaoOutPath);
		return DaoOutPath;
	}

	/**
	 * 获取MapperOutPath的路径的路径
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @return
	 * 
	 * @date 2017年6月26日 上午9:10:21
	 */
	public static String getMapperOutPath() {
		Properties properties = Utils.readPropertiesFile();
		String MapperOutPath = (String) properties.get("MapperOutPath");
		System.out.println("MapperOutPath的路径：" + MapperOutPath);
		return MapperOutPath;
	}

	/**
	 * 获取ControllerOutPath的路径的路径
	 * 
	 * <br>
	 * added by liuyongtao<br>
	 * 
	 * @return
	 * 
	 * @date 2017年6月26日 上午9:10:21
	 */
	public static String getControllerOutPath() {
		Properties properties = Utils.readPropertiesFile();
		String ControllerOutPath = (String) properties.get("ControllerOutPath");
		System.out.println("ControllerOutPath的路径：" + ControllerOutPath);
		return ControllerOutPath;
	}

	public static void main(String[] args) {
		Utils.getApiOutPath();
		Utils.getConfigPath();
		Utils.getDaoOutPath();
		Utils.getFltPath();
		Utils.getMapperOutPath();
	}
}
