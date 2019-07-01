package com.zjzc.manage.core.job;

import java.lang.reflect.InvocationTargetException;


public interface ExecutionJob {
	
	/**
	 * 
	 * @throws Exception
	 */
	public void execute() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException;
}
