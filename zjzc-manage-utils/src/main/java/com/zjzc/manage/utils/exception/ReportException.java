package com.zjzc.manage.utils.exception;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * 交易定义异常
 */
public class ReportException extends Exception {

	private static final long serialVersionUID = 2517586869695353413L;
	// 错误码
	private final String errorCode;

	/**
	 * 异常
	 *
	 * @param cause
	 */
	public ReportException(Throwable cause) {
		super(cause);
		this.errorCode = "";
	}

	/**
	 * 异常
	 *
	 * @param message
	 * @param cause
	 */
	public ReportException(String message, Throwable cause) {
		super(message, cause);
		this.errorCode = "";
	}

	/**
	 * 异常
	 *
	 * @param errorCode
	 * @param message
	 * @param cause
	 */
	public ReportException(String errorCode, String message, Throwable cause) {
		super(message, cause);
		this.errorCode = errorCode;
	}

	/**
	 * 异常
	 *
	 * @param message
	 */
	public ReportException(String message) {
		super(message);
		this.errorCode = "";
	}

	public ReportException(String errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}

	/**
	 * 异常
	 * 
	 * @return
	 */
	public String getStackTrack() {
		ByteArrayOutputStream os = new ByteArrayOutputStream(1000);
		this.printStackTrace(new PrintStream(os));
		return os.toString();
	}

	/**
	 * 
	 * @return
	 */
	public String getErrorCode() {
		return errorCode;
	}

}
