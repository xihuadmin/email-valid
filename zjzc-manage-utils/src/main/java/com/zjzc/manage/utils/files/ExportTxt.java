package com.zjzc.manage.utils.files;

import com.google.common.collect.Lists;
import com.zjzc.manage.utils.annotation.TxtField;
import com.zjzc.manage.utils.common.Overall;
import com.zjzc.manage.utils.excel.Reflections;
import com.zjzc.manage.utils.sftp.FileUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by lijun on 2017/11/16 20:39.
 */
@Data
@Slf4j
public class ExportTxt {

    /**
     * 注解列表（Object[]{ TxtField, Field/Method }）
     */
    List<Object[]> annotationList = Lists.newArrayList();

    /**
     * 文件名称
     */

    String fileName;

    public ExportTxt() {

    }

    /**
     * 初始文件属性
     *
     * @param fileName 文件名称
     * @param cls      实体，带自定义注解
     */
    public ExportTxt(String fileName, Class<?> cls) {
        Field[] fs = cls.getDeclaredFields();
        this.fileName = fileName;
        TxtField ef;
        for (Field f : fs) {
            ef = f.getAnnotation(TxtField.class);
            if (ef != null && (ef.type() == 0 || ef.type() == 1)) {
                annotationList.add(new Object[]{ef, f});
            }
        }
        Collections.sort(annotationList, new Comparator<Object[]>() {
            public int compare(Object[] o1, Object[] o2) {
                return new Integer(((TxtField) o1[0]).sort()).compareTo(
                        new Integer(((TxtField) o2[0]).sort()));
            }

            ;
        });
    }

    /**
     * 生成txt文件
     *
     * @param list     数据集
     * @param filePath 导出路径
     * @param <E>      实体类型
     */
    public <E> void setDataList(List<E> list, String filePath) throws Exception {
        for (E e : list) {
            StringBuilder sb = new StringBuilder();
            for (Object[] os : annotationList) {
                TxtField txtField = (TxtField) os[0];
                Object val = null;
                if (StringUtils.isNotBlank(txtField.value())) {
                    val = Reflections.invokeGetter(e, txtField.value());
                } else {
                    if (os[1] instanceof Field) {
                        val = Reflections.invokeGetter(e, ((Field) os[1]).getName());
                    }
                }
                sb.append(val).append(Overall.TXT_SYMBOL);//拼接分隔符
            }
            //拼接公司id与换行符号
            sb.deleteCharAt(sb.length()-1);
            String txt = sb.toString().replaceAll("\r|\n","").concat(Overall.WARP_SYMBOL);
            log.info("Write success: {}", txt);
            FileUtils.writeToFile(filePath, fileName, txt, Overall.TXT_ENCODING);//循环写入数据，导出txt格式
        }
    }
}
