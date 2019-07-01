package com.zjzc.manage.core.mysql;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.*;
import java.text.SimpleDateFormat;

/**
 * 把数据库字段格式化成指定格式的日期
 * 
 */
public abstract class AbstractTimestampTypeHandler implements TypeHandler<String> {
	
	protected abstract SimpleDateFormat getSimpleDateFormat();

	/**
	 * 
	 */
	public void setParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 */
	public String getResult(ResultSet rs, String columnName) throws SQLException {
		Timestamp date = rs.getTimestamp(columnName);
		if (date == null) {
			return null;
		}
		return getSimpleDateFormat().format(date);
	}

	/**
	 * 
	 */
	public String getResult(ResultSet rs, int columnIndex) throws SQLException {
		Timestamp date = rs.getTimestamp(columnIndex);
		if (date == null) {
			return null;
		}
		return getSimpleDateFormat().format(date);
	}

	/**
	 * 
	 */
	public String getResult(CallableStatement cs, int columnIndex) throws SQLException {
		throw new UnsupportedOperationException();
	}
}
