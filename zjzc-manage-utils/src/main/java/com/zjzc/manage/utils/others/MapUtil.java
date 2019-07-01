package com.zjzc.manage.utils.others;

import org.apache.commons.lang3.StringUtils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 这里的map对象，是对应数据库的一条记录
 */
public final class MapUtil {
	
	/**
	 * 
	 * @param map
	 * @param key
	 * @param value
	 */
	public static void addStringToMap(Map<String, Object> map, String key, String value) {
		if (StringUtils.isNotBlank(value)) {
			map.put(key, value);
		}
	}

	/**
	 * 
	 * @param map
	 * @param key
	 * @param value
	 */
	public static void addValueToMap(Map<String, Object> map, String key, Object value) {
		if (value != null) {
			map.put(key, value);
		}
	}

	/**
	 * 
	 * @param map
	 * @param key
	 * @return
	 */
	public static Double getDoubleValue(Map<String, Object> map, String key) {
		BigDecimal decimal = getMapValue(map, key, BigDecimal.class);
		return decimal == null ? null : decimal.doubleValue();
	}

	/**
	 * 
	 * @param map
	 * @param key
	 * @return
	 */
	public static Date getDateValue(Map<String, Object> map, String key) {
		return getMapValue(map, key, Date.class);
	}
	
	/**
	 * 
	 * @param map
	 * @param key
	 * @return
	 */
	public static LocalDate getLocalDateValue(Map<String, Object> map, String key) {
		java.sql.Date date = getMapValue(map, key, java.sql.Date.class);
		return date == null ? null : date.toLocalDate();
	}

	/**
	 *
	 * @param map
	 * @param key
	 * @return
	 */
	public static LocalDateTime getDateLocalDateTimeValue(Map<String, Object> map, String key) {
		java.sql.Timestamp timestamp = getMapValue(map, key, java.sql.Timestamp.class);
		return timestamp == null ? null : timestamp.toLocalDateTime();
	}

	/**
	 * 
	 * @param map
	 * @param key
	 * @return
	 */
	public static Integer getIntegerValue(Map<String, Object> map, String key) {
		return getMapValue(map, key, Integer.class);
	}

	/**
	 * 
	 * @param map
	 * @param key
	 * @return
	 */
	public static Long getLongValue(Map<String, Object> map, String key) {
		return getMapValue(map, key, Long.class);
	}
	
	/**
	 * 
	 * @param map
	 * @param key
	 * @return
	 */
	public static String getStringValue(Map<String, Object> map, String key) {
		return getMapValue(map, key, String.class);
	}

	/**
	 * 
	 * @param map
	 * @param key
	 * @return
	 */
	public static Boolean getBooleanValue(Map<String, Object> map, String key) {
		return getMapValue(map, key, Boolean.class);
	}

	/**
	 * 
	 * @param map
	 * @param key
	 * @param clazz
	 * @return
	 */
	public static <T> T getMapValue(Map<String, Object> map, String key, Class<T> clazz) {
		Object value = map.get(key);
		if (value == null) {
			return null;
		}
		return clazz.cast(value);
	}

	/**
	 * 
	 * @param data
	 * @param key
	 * @param defaultVal
	 * @return
	 */
	public static Object getMapValueWithDefault(Map<String, Object> data, String key, Object defaultVal) {
		Object value = data.get(key);
		if (value == null || StringUtils.isBlank(value.toString())) {
			return defaultVal;
		}
		return value;
	}
	
	/**
	 * 
	 * @param object
	 * @return
	 * @throws IntrospectionException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public static Map<String, String> describeObject(Object object) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Map<String,String> map = new HashMap<String,String>();
		BeanInfo beanInfo = Introspector.getBeanInfo(object.getClass());
		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		if (propertyDescriptors != null && propertyDescriptors.length > 0) {
            String propertyName = null; // object属性名
            Object propertyValue = null; // object属性值
            for (PropertyDescriptor pd : propertyDescriptors) {
                propertyName = pd.getName();
                if (!propertyName.equals("class")) {
                    Method readMethod = pd.getReadMethod();
                    propertyValue = readMethod.invoke(object, new Object[0]);
                    map.put(propertyName.toString(), propertyValue.toString());
                }
            }
        }
		return map;
	}
	
	/**
	 * 
	 * @param json
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> extractMapInfo(String json) {
		Map<String, Object> detail = JsonUtil.from(json, Map.class);
		return detail;
	}
}