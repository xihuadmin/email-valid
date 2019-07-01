package com.zjzc.manage.utils.common.enums;

public enum ResponseCode {
	
	/**
	 * 完成-请求成功
	 */
	SUCCESS(200, "请求成功"),

	/**
	 * 完成-请求成功
	 */
	VALID(1, "邮件有效"),

	INVALID(0, "邮件无效"),
	
	/**
	 * 完成-系统处理中
	 */
	DURING(201, "系统处理中"),
	
	/**
	 * 请求失败
	 */
	FAILED(-300, "请求失败"),
	
	/**
	 * 请求失败-签名无效
	 */
	SIGN_FAILED(-301, "签名无效"),
	
	/**
	 * 请求失败-请求超时
	 */
	TIMEOUT_FAILED(-302, "请求超时"),
	
	/**
	 * 接口错误-接口未实现
	 */
	PAGE_NOT_FOUND(-404, "接口未实现"),
	
	/**
	 * 系统异常
	 */
	ERROR(-500, "系统异常"),
	
	/**
	 * 参数错误-参数格式不正确
	 */
	DATA_FORMAT_WRONG(-601, "参数格式不正确"),
	
	/**
	 * 参数错误-手机号错误
	 */
	MOBILE_WRONG(-602, "手机号错误"),
	

	/**
	 * 参数错误-金额错误
	 */
	MOBILE_MONEY(-66604, "金额错误"),

	/**
	 * 参数错误-分页参数错误
	 */
	PAGE_NUM_WRONG(-66605,"分页参数错误"),
	;
	
	private int code;
	private String msg;
	
	private ResponseCode (int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
	
}
