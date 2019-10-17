package com.zjzc.manage.api.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author JLICC
 * @Package com.zjzc.manage.core.model
 * @Description:
 * @date 2019/06/26 18:13:57
 */
@Data
public class EmailEntity implements Serializable{

    private String inEmail;

    private String outEmail;
}
