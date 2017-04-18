package org.billow.utils.exception;

public class DubboException extends RuntimeException {

	private static final long serialVersionUID = -8421181224573118785L;

	public DubboException() {
		super("Dubbo没有启动...");
	}

	public DubboException(String st) {
		super(st);
	}
}
