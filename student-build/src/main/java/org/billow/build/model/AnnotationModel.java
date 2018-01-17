package org.billow.build.model;

/**
 * 注释
 * 
 * @author Liuyongtao
 * @version $Id: Annotation.java 2016年1月13日 上午10:17:44 $
 */
public class AnnotationModel {

	// 作者名称
	private String authorName;

	// 作者邮箱
	private String authorMail;

	// 日期
	private String date;

	// 版本
	private String version;

	// 类说明
	private String explain;

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getAuthorMail() {
		return authorMail;
	}

	public void setAuthorMail(String authorMail) {
		this.authorMail = authorMail;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getExplain() {
		return explain;
	}

	public void setExplain(String explain) {
		this.explain = explain;
	}

}