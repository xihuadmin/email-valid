package com.zjzc.manage.core.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author JLICC
 * @Package com.zjzc.manage.core.model
 * @Description:
 * @date 2019/06/04 11:40:27
 */
@Data
public class CaseBatchInfo {

    private Long caseId;

    private BigDecimal amount;

    private BigDecimal price;

    private String msg;
}
