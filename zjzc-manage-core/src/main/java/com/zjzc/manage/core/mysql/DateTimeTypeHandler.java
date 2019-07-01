package com.zjzc.manage.core.mysql;

import java.text.SimpleDateFormat;

/**
 * 日期+时间格式转换
 */
public class DateTimeTypeHandler extends AbstractTimestampTypeHandler {

	private final SimpleDateFormat LOCALDATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	

	@Override
	protected SimpleDateFormat getSimpleDateFormat() {
		return LOCALDATETIME_FORMAT;
	}
}
