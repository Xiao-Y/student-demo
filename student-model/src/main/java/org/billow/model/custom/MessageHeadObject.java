package org.billow.model.custom;

import java.io.Serializable;

public class MessageHeadObject implements Serializable {

	private static final long serialVersionUID = 1104417962623689555L;

	private String sysCode;
	private String businessKey;
	private String routtingKey;
	private String queueName;
	private String exchangeName;
	private String virtualHost;
	private String businessKeyDesc;

	public MessageHeadObject() {
	}

	public String getBusinessKeyDesc() {
		return businessKeyDesc;
	}

	public void setBusinessKeyDesc(String businessKeyDesc) {
		this.businessKeyDesc = businessKeyDesc;
	}

	public String getSysCode() {
		return sysCode;
	}

	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}

	public String getBusinessKey() {
		return businessKey;
	}

	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}

	public String getRouttingKey() {
		return routtingKey;
	}

	public void setRouttingKey(String routtingKey) {
		this.routtingKey = routtingKey;
	}

	public String getQueueName() {
		return queueName;
	}

	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}

	public String getExchangeName() {
		return exchangeName;
	}

	public void setExchangeName(String exchangeName) {
		this.exchangeName = exchangeName;
	}

	public String getVirtualHost() {
		return virtualHost;
	}

	public void setVirtualHost(String virtualHost) {
		this.virtualHost = virtualHost;
	}
}
