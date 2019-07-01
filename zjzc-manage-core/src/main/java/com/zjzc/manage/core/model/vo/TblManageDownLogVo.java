package com.zjzc.manage.core.model.vo;

import lombok.Data;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

@Data
@ToString
public class TblManageDownLogVo extends BaseSearchVo{
    private String fileName;
    private String orderNum;
    private String userMobile;
    private String version;
    private String code;
    private String msg;
    private String fileType;
    private Date addTime;
    private String flag;
}
