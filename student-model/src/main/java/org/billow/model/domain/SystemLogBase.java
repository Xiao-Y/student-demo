package org.billow.model.domain;

import java.io.Serializable;
import java.util.Date;

public class SystemLogBase implements Serializable {

	private static final long serialVersionUID = -4788551421806088982L;

	private Integer id;

	private String module;

	private String function;

	private String operation;

	private String note;

	private String content;

	private String runClass;

	private Date createTime;

	private Integer userId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module == null ? null : module.trim();
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function == null ? null : function.trim();
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation == null ? null : operation.trim();
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note == null ? null : note.trim();
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}

	public String getRunClass() {
		return runClass;
	}

	public void setRunClass(String runClass) {
		this.runClass = runClass == null ? null : runClass.trim();
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * 主键toString 非主键不允许添加
	 */
	@Override
	public String toString() {
		return "PK[id = " + id + "]";
	}
}