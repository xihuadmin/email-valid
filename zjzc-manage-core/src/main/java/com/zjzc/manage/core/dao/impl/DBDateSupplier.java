package com.zjzc.manage.core.dao.impl;

import com.zjzc.manage.core.mapper.download.BusiDateMapper;
import com.zjzc.manage.core.model.schedule.BusiDate;
import com.zjzc.manage.utils.others.DateSupplier;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 优先从数据库中获取时间
 */
public class DBDateSupplier implements DateSupplier {
	
	private BusiDateMapper busiDateMapper;
	
	/**
	 * @param busiDateMapper
	 */
	public DBDateSupplier(BusiDateMapper busiDateMapper) {
		this.busiDateMapper = busiDateMapper;
	}
	
	/**
	 * 获取当前时间（年月日时分秒）
	 * @return
	 */
	@Override
	public LocalDateTime getNowLocalDateTime() {
		BusiDate busiDate = busiDateMapper.queryBusiDate();
		if (busiDate != null && busiDate.isActive()) {
			return LocalDateTime.from(busiDate.getBusiDate());
		}
		
		return LocalDateTime.now();
	}
	
	/**
	 * 获取当前时间（年月日）
	 * @return
	 */
	@Override
	public LocalDate getNowLocalDate() {
		return getNowLocalDateTime().toLocalDate();
	}
}
