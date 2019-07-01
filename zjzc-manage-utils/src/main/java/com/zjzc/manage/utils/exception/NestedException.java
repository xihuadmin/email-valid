package com.zjzc.manage.utils.exception;

public class NestedException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param message
	 */
	public NestedException(String message) {
		super(message);
	}
	
	/**
	 * 
	 * @param message
	 * @param cause
	 */
	public NestedException(String message, Throwable cause) {
		super(message, cause);
	}
	
	/**
	 * 
	 * @param cause
	 */
	public NestedException(Throwable cause) {
		super(cause);
	}
}
