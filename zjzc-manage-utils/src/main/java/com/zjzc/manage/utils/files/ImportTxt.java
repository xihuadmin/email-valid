package com.zjzc.manage.utils.files;

import com.google.common.collect.Lists;
import com.zjzc.manage.utils.annotation.TxtField;
import com.zjzc.manage.utils.common.Overall;
import com.zjzc.manage.utils.excel.Reflections;
import com.zjzc.manage.utils.exception.ReportException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by lijun on 2017/11/16 20:39.
 */
@Slf4j
public class ImportTxt {

    /**
     * 只支持字段，不支持方法注解
     * @param cls 注解类
     * @param filePath 文件路径
     * @param <E>
     * @return
     */
    public static <E> List<E> getDataList(Class<E> cls,String filePath){
        Field[] fs = cls.getDeclaredFields();
        List<Object[]> annotationList = Lists.newArrayList();
        TxtField ef;
        for (Field f : fs) {
            ef = f.getAnnotation(TxtField.class);
            if (ef != null && (ef.type() == 0 || ef.type() == 2)) {
                annotationList.add(new Object[]{ef, f});
            }
        }
        Collections.sort(annotationList, new Comparator<Object[]>() {
            public int compare(Object[] o1, Object[] o2) {
                return new Integer(((TxtField) o1[0]).sort()).compareTo(
                        new Integer(((TxtField) o2[0]).sort()));
            }
        });
        List<E> dataList = Lists.newArrayList();
        String encoding = "GBK";
        File file = new File(filePath);
        InputStreamReader read = null;
        FileInputStream fi = null;
        try {
            if (file.isFile() && file.exists()) { // 判断文件是否存在
                fi = new FileInputStream(file);
                read = new InputStreamReader(fi, encoding);// 考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = "";
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    E e = cls.newInstance();
                    int num = 0;
                    String arr[] = lineTxt.split("[|]");
                    for (Object[] os : annotationList){
                        Object val = arr[num];
                        num ++;
                        if(val == null){
                            continue;
                        }
                        Class<?> valType = Class.class;
                        if (os[1] instanceof Field){
                            valType = ((Field)os[1]).getType();
                        }
                        val = getObject(val, valType);
                        // set entity value
                        if (os[1] instanceof Field){
                            Reflections.invokeSetter(e, ((Field)os[1]).getName(), val);
                        }
                    }
                    dataList.add(e);
                }
                bufferedReader.close();
            }else{
                log.warn("【导入txt文件】文件名称：{},找不到指定的文件", filePath);
            }
        }catch (Exception e){
            log.error("【导入txt文件】文件名称：{},读取文件内容出错：{}", filePath, e);
        }finally {
            close(filePath, read, fi);
        }
        return dataList;
    }
    /**
     * 按照注解拆分结果
     * @param cls 注解类
     * @param filePath 文件路径
     * @param <E>
     * @return map 返回拆分后的数据
     */
    public static <E> Map getDataListBySplit(Class<E> cls, String filePath) throws ReportException{
        Field[] fs = cls.getDeclaredFields();
        List<Object[]> annotationList = Lists.newArrayList();
        TxtField ef;
        for (Field f : fs) {
            ef = f.getAnnotation(TxtField.class);
            if (ef != null && (ef.type() == 0 || ef.type() == 2)) {
                annotationList.add(new Object[]{ef, f});
            }
        }
        Collections.sort(annotationList, new Comparator<Object[]>() {
            public int compare(Object[] o1, Object[] o2) {
                return new Integer(((TxtField) o1[0]).sort()).compareTo(
                        new Integer(((TxtField) o2[0]).sort()));
            }
        });
        List<E> successList = Lists.newArrayList();
        List<E> errorList = Lists.newArrayList();
        boolean flag = false;
        String encoding = "GBK";
        File file = new File(filePath);
        InputStreamReader read = null;
        FileInputStream fi = null;
        try {
            if (file.isFile() && file.exists()) { // 判断文件是否存在
                fi = new FileInputStream(file);
                read = new InputStreamReader(fi, encoding);// 考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = "";
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    E e = cls.newInstance();
                    int num = 0;
                    String arr[] = lineTxt.split("[|]");
                    flag = false;
                    for (Object[] os : annotationList){
                        Object val;
                        if(num < arr.length){
                            val = arr[num];
                        }else
                            val = filePath;
                        num ++;
                        if(val == null){
                            continue;
                        }
                        Class<?> valType = Class.class;
                        if (os[1] instanceof Field){
                            valType = ((Field)os[1]).getType();
                        }
                        val = getObject(val, valType);
                        // set entity value
                        if (os[1] instanceof Field){
                            Reflections.invokeSetter(e, ((Field)os[1]).getName(), val);
                        }
                        if(((TxtField)os[0]).flag() && ("0000".equals(val.toString()) || "0003".equals(val.toString()))){
                            flag = true;
                        }
                    }
                    if(flag){
                        successList.add(e);
                    }else
                        errorList.add(e);
                }
                bufferedReader.close();
            }else{
                log.warn("【导入txt文件】文件名称：{},找不到指定的文件", filePath);
            }
        }catch (Exception e){
            log.error("【导入txt文件】文件名称：{},读取文件内容出错：{}", filePath, e);
            throw new ReportException("【导入txt文件】文件名称：{},读取文件内容出错：{}", filePath, e);
        }finally {
            close(filePath, read, fi);
        }
        Map map = new HashMap();
        map.put(Overall.SUCCESS_LIST,successList);
        map.put(Overall.ERROR_LIST,errorList);
        return map;
    }

    private static void close(String fileName, InputStreamReader read, FileInputStream fi) {
        try {
            if (fi != null) {
                fi.close();
            }
            if (read != null) {
                read.close();
            }
        } catch (IOException e) {
            log.error("【导入txt文件】文件名称：{},异常：{}", fileName, e);
        }
    }

    private static Object getObject(Object val, Class<?> valType) {
        try {
            //如果导入的java对象，需要在这里自己进行变换。
            if (valType == String.class){
                String s = String.valueOf(val.toString());
                if(StringUtils.endsWith(s, ".0")){
                    val = StringUtils.substringBefore(s, ".0");
                }else{
                    val = String.valueOf(val.toString());
                }
            }else if (valType == Integer.class){
                val = Double.valueOf(val.toString()).intValue();
            }else if (valType == Long.class){
                val = Double.valueOf(val.toString()).longValue();
            }else if (valType == Double.class){
                val = Double.valueOf(val.toString());
            }else if (valType == Float.class){
                val = Float.valueOf(val.toString());
            }else if (valType == Date.class){
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                val=sdf.parse(val.toString());
            }
        } catch (Exception ex) {
            log.info("Get cell value  error: " + ex.toString());
            val = "";
        }
        return val;
    }
}
