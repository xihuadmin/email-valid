package com.zjzc.manage.utils.others;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Object 与 Map&lt;String, Object&gt; 的转换类
 * 
 */
public class ObjectMapper {
    protected static Logger logger = LoggerFactory.getLogger(ObjectMapper.class);
    
    /**
     * 将 Map&lt;String, String&gt; 按key的属性转换为类型为 &lt;T&gt; 的对象     , 支持父类属性的传递
     * 
     * @param <T>
     * @param map
     * @param clazz
     * @return
     * @throws Exception
     */
    public static <T> T toObject(Map<String, String> map, Class<T> clazz,String... excludeFields) {
        try {
            T obj = clazz.newInstance();
            toObject(obj,map,clazz,excludeFields);
            return obj;
        } catch (InstantiationException | IllegalAccessException e) {
            logger.error(e.getMessage());
        }
        return null;
    }
    
    /**
     * 将 Map&lt;String, String&gt; 按key的属性转换为类型为 &lt;T&gt; 的对象
     * 
     * @param <T>
     * @param map
     * @param clazz
     * @param excludeFields--过滤字段，在其中的话不做映射处理
     * @return
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    private static <T> void toObject(T obj,Map<String, String> map, Class clazz,String... excludeFields) {
        try {
            //T obj = clazz.newInstance();
            Field[] field = clazz.getDeclaredFields();
            for (Field f : field) {
            	//判断当前字段是否在excludeFields中，如果在，则不做转换映射
            	boolean exclude=false;
            	if(excludeFields!=null&&excludeFields.length>0){
            		for(String excludeField:excludeFields){
            			if(excludeField.equals(f.getName())){
            				exclude=true;
            				break;
            			}
            		}
            	}
            	if(exclude)continue;
                String type = f.getGenericType().toString();
                String value = "";
                Set<String> set = map.keySet();
                for (String key : set) {
                    if (key.equals(f.getName())) {
                        value = map.get(key);
                        break;
                    }
                }
                if (value.length() == 0)
                    continue;
                f.setAccessible(true);
                if (type.equals(int.class.toString())) {
                    f.setInt(obj, Integer.parseInt(value));
                } else if (type.equals(Integer.class.toString())) {
                    f.set(obj, new Integer(value));
                } else if (type.equals(float.class.toString())) {
                    f.setFloat(obj, Float.parseFloat(value));
                } else if (type.equals(Float.class.toString())) {
                    f.set(obj, new Float(value));
                } else if (type.equals(long.class.toString())) {
                    f.setLong(obj, Long.parseLong(value));
                } else if (type.equals(Long.class.toString())) {
                    f.set(obj, new Long(value));
                } else if (type.equals(double.class.toString())) {
                    f.setDouble(obj, Double.parseDouble(value));
                } else if (type.equals(Double.class.toString())) {
                    f.set(obj, new Double(value));
                } else if (type.equals(boolean.class.toString())) {
                    f.setBoolean(obj, Boolean.parseBoolean(value));
                } else if (type.equals(Boolean.class.toString())) {
                    f.set(obj, new Boolean(value));
                } else if (type.equals(byte.class.toString())) {
                    f.setByte(obj, Byte.parseByte(value));
                } else if (type.equals(Byte.class.toString())) {
                    f.set(obj, new Byte(value));
                } else if (type.equals(short.class.toString())) {
                    f.setShort(obj, Short.parseShort(value));
                } else if (type.equals(Short.class.toString())) {
                    f.set(obj, new Short(value));
                } else if(type.equals(BigDecimal.class.toString())){
                    f.set(obj, new BigDecimal(value));
                }else {
                    f.set(obj, value);
                }
            }
            Class parent=clazz.getSuperclass();
            if(parent!=null){
                toObject(obj,map,parent);
            }
            //return obj;
        } catch (Exception e) {
            logger.error("类型转换失败,{}",e.getMessage());
        }
        //return null;
    }

    /**
     * 将 类型为 &lt;T&gt; 的对象按属性转换为 Map&lt;String, Object&gt;
     * 
     * @param <T>
     * @param obj
     * @param filter
     *            -- 过滤不进行设置的属性
     * @return
     */
    public static <T> Map<String, String> toMapString(T obj, String... filter) {
        return toMapString(obj, false, false, filter);
    }
    
    /**
     * 将 类型为 &lt;T&gt; 的对象按属性转换为 Map&lt;String, Object&gt;
     * 
     * @param <T>
     * @param obj
     * @param filter
     *            -- 过滤不进行设置的属性
     * @return
     */
    public static <T> Map<String, Object> toMap(T obj, String... filter) {
    	return toMap(obj, false, false, filter);
    }

    /**
     * 将 类型为 &lt;T&gt; 的对象（父类）按属性转换为 Map&lt;String, Object&gt;
     * 
     * @param <T>
     * @param obj
     * @param withParent
     *            -- 是否获取父类属性
     * @param filter
     *            -- 过滤不进行设置的属性
     * @return
     */
    public static <T> Map<String, Object> toMap(T obj, boolean withParent, boolean filterNull,
            String... filter) {
        List<String> list = toList(filter);
        Map<String, Object> map = new HashMap<String, Object>();
        Field[] field = obj.getClass().getDeclaredFields();
        List<Field> fields = toList(field);
        // 父类属性
        if (withParent) {
            Field[] parent_field = obj.getClass().getSuperclass().getDeclaredFields();
            fields.addAll(toList(parent_field));
        }
        try {
            for (Field f : fields) {
                String key = f.getName();
                if (list.size() > 0 && list.contains(key))
                    continue;
                f.setAccessible(true);
                Object value = f.get(obj);
                if (!filterNull || value != null) {
                	map.put(key, value);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return map;
    }
    
    
    /**
     * 将 类型为 &lt;T&gt; 的对象（父类）按属性转换为 Map&lt;String, Object&gt;
     * 
     * @param <T>
     * @param obj
     * @param withParent
     *            -- 是否获取父类属性
     * @param filterNull
     *            -- 是否过滤null值
     * @param filter
     *            -- 过滤不进行设置的属性
     * @return
     */
    public static <T> Map<String, String> toMapString(T obj, boolean withParent, boolean filterNull, String... filter) {
        List<String> list = toList(filter);
        Map<String, String> map = new HashMap<String, String>();
        Field[] field = obj.getClass().getDeclaredFields();
        List<Field> fields = toList(field);
        // 父类属性
        if (withParent) {
            Field[] parent_field = obj.getClass().getSuperclass().getDeclaredFields();
            fields.addAll(toList(parent_field));
        }
        try {
            for (Field f : fields) {
                String key = f.getName();
                if (list.size() > 0 && list.contains(key)) {
                    continue;
                }
                f.setAccessible(true);
                if (f.get(obj) instanceof String) {
	                String value = (String)f.get(obj);
	                if (!filterNull || value != null) {
	                	map.put(key, value);
	                }
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return map;
    }
    

    /**
     * 将父类对象转换为子类对象（包含属性值），可以理解为：少属性对象 转为 多属性对象
     * 
     * @param <T>
     *            -- 子类类型
     * @param <S>
     *            -- 父类类型
     * @param obj
     * @return
     */
    public static <T, S> T convert(S parent, Class<T> clazz) {
        try {
            T obj = clazz.newInstance();
            for (Field p : parent.getClass().getDeclaredFields()) {
                p.setAccessible(true);
                String key = p.getName();
                Object value = p.get(parent);
                if (key.equals("serialVersionUID"))
                    continue;

                Field f = clazz.getSuperclass().getDeclaredField(key);
                f.setAccessible(true);
                f.set(obj, value);
            }
            return obj;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
	 * 将数组转换为{@link List}，空数组返回空的{@link ArrayList}（非null）
	 * @param <T>
	 * @param array
	 * @return
	 */
	public static <T> List<T> toList(T[] array) {
		if(array == null || array.length == 0) return new ArrayList<T>();
		List<T> list = new ArrayList<T>();
		for(T obj : array) {
			list.add(obj);
		}
		return list;
	}
	
}
