package com.hk.common.exception;

public class HandleRuntimeException extends Exception {
	private static final long serialVersionUID = 1L;

	public HandleRuntimeException(String message) {
		super(message);
	}

	public String getMessage() {
		return super.getMessage();
	}
}
