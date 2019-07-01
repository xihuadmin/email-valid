package com.zjzc.manage.utils.others;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface DateSupplier {
	
	/**
	 * 获取当前时间（年月日时分秒）
	 * @return
	 */
	LocalDateTime getNowLocalDateTime();
	
	/**
	 * 获取当前时间（只含有年月日）
	 * @return
	 */
	LocalDate getNowLocalDate();
}
