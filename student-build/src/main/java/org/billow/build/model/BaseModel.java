package org.billow.build.model;

/**
 * 公用基础类
 * 
 * @author Liuyongtao
 * @version $Id: BaseModel.java 2016年1月13日 上午10:27:29 $
 */
public class BaseModel extends AnnotationModel {
	/**
	 * 类名
	 */
	private String clazzName;
	/**
	 * 包名称
	 */
	private String packageName;

	/**
	 * 文件输出路径
	 */
	private String outPath;

	public String getClazzName() {
		return clazzName;
	}

	public void setClazzName(String clazzName) {
		this.clazzName = clazzName;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getOutPath() {
		return outPath;
	}

	public void setOutPath(String outPath) {
		this.outPath = outPath;
	}
}
