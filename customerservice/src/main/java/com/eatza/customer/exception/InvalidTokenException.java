package com.eatza.customer.exception;

public class InvalidTokenException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public InvalidTokenException() {
		super();
	}
	public InvalidTokenException(String msg) {
		super(msg);
	}
	 public InvalidTokenException(String message, Throwable cause, String errorCode) {
	        super(message, cause);
	    }

}