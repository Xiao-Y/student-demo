package org.billow.model.custom;

import java.io.Serializable;

public class MessageObject implements Serializable {

	private static final long serialVersionUID = -6891556232488611003L;

	private String requestId;
	private String messageId;
	private boolean durable;
	private Integer priority;
	private String expiration;
	private Object body;
	private MessageHeadObject head;

	public MessageHeadObject getHead() {
		return head;
	}

	public void setHead(MessageHeadObject head) {
		this.head = head;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public Object getBody() {
		return body;
	}

	public void setBody(Object body) {
		this.body = body;
	}

	public boolean isDurable() {
		return durable;
	}

	public void setDurable(boolean durable) {
		this.durable = durable;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public String getExpiration() {
		return expiration;
	}

	public void setExpiration(String expiration) {
		this.expiration = expiration;
	}

	@Override
	public String toString() {
		return "MessageObject [requestId=" + requestId + ", messageId=" + messageId + ", durable=" + durable + ", priority=" + priority
				+ ", expiration=" + expiration + ", body=" + body + ", head=" + head + "]";
	}
}
