package com.zjzc.manage.core.job;


public final class JobUtil {
	private static Integer id = 0;

	public static synchronized Integer generateJobId() {
		return ++id;
	}
}
