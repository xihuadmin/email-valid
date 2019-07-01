/**
 * Copyright &copy; 2015-2020 <a href="http://www.100bei.com/">JeePlus</a> All rights reserved.
 */
package com.zjzc.manage.utils.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Txt注解定义
 * @author lijun
 * @version 2017-11-16
 */
@Target({ ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TxtField {

	/**
	 * 导出字段名（默认调用当前字段的“get”方法，如指定导出字段为对象，请填写“对象名.对象属性”，例：“area.name”、“office.name”）
	 */
	String value() default "";

	/**
	 * 字段类型（0：导出导入；1：仅导出；2：仅导入）
	 */
	int type() default 0;

	/**
	 * 导出字段字段排序（升序）
	 */
	int sort() default 0;

	/**
	 * 是否依据该字段拆分
	 */
	boolean flag() default false;
	/**
	 * 反射类型
	 */
	Class<?> fieldType() default Class.class;
}
