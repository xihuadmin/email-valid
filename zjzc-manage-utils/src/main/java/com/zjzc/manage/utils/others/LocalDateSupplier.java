package com.zjzc.manage.utils.others;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 */
public class LocalDateSupplier implements DateSupplier {
	
	/**
	 * 获取当前时间（年月日时分秒）
	 * @return
	 */
	@Override
	public LocalDateTime getNowLocalDateTime() {
		return LocalDateTime.now();
	}
	
	/**
	 * 获取当前时间（年月日）
	 * @return
	 */
	@Override
	public LocalDate getNowLocalDate() {
		return LocalDate.now();
	}
}
