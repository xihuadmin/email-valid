package com.zjzc.manage.core.mysql;

import java.text.SimpleDateFormat;


public class DateTypeHandler extends AbstractTimestampTypeHandler {
	
	private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

	protected SimpleDateFormat getSimpleDateFormat() {
		return DATE_FORMAT;
	}

}
