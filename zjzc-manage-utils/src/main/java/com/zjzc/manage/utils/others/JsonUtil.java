package com.zjzc.manage.utils.others;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zjzc.manage.utils.exception.NestedException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class JsonUtil {
	private static final ObjectMapper objectMapper = new ObjectMapper();
	
	private JsonUtil() {
	    throw new IllegalAccessError("Utility class");
	  }

	/**
	 * 
	 * @param value
	 * @return
	 */
	public static String toJson(Object value) {
		try {
			return objectMapper.writeValueAsString(value);
		} catch (JsonProcessingException e) {
			throw new NestedException(e);
		}
	}

	/**
	 * 
	 * @param value
	 * @param valueType
	 * @return
	 */
	public static <T> T from(Object value, Class<T> valueType) {
		try {
			if(null == value){
				return null;
			}
			return objectMapper.readValue(value.toString(), valueType);
		} catch (IOException e) {
			throw new NestedException(e);
		}
	}

	/**
	 * 
	 * @param sJson
	 * @param param
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> jsonStringToMap(String sJson, String param) {
		if (StringUtils.isNotBlank(sJson)) {
			Map<String, Object> map = JsonUtil.from(sJson, Map.class);
			Object paramObj = map.get(param);
			if (paramObj != null && StringUtils.isNotBlank(paramObj.toString())) {
				map.clear();
				map.put(param, paramObj);
				return map;
			}
		}
		return new HashMap<>();
	}

	/**
	 * 
	 * @param map
	 * @return
	 */
	public static JSONObject map2JsonObject(Map map){
		return JSONObject.parseObject(JSONObject.toJSONString(map));
	}

	/**
	 * 
	 * @param json
	 * @return
	 */
	public static JSONObject json2JsonObject(String json){
		return JSONObject.parseObject(json);
	}

	/**
	 * 
	 * @param obj
	 * @return
	 */
	public static String obj2Json(Object obj){
		return JSONObject.toJSONString(obj);
	}

	/**
	 * 将Json转换成Object对象
	 *
	 * @param json
	 *            Json字符串
	 * @param cls
	 *            转换成的对象类型
	 * @return 转换之后的对象
	 */
	public static <T> T convertJson2Object(String json, Class<T> cls) {
		try {
			return objectMapper.readValue(json, cls);
		} catch (Exception e) {
			log.info("将Json转换成Object对象出现异常！");
		}
		return null;
	}
}
