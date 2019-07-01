package com.zjzc.manage.core.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author JLICC
 * @Package com.zjzc.manage.core.model
 * @Description:
 * @date 2019/06/04 11:15:39
 */
@Data
public class FinanceSupplement implements Serializable{


    private static final long serialVersionUID = -4501711972401435444L;

    //违约时间
    private String breakTime;

    //借款协议签订时间
    private String agreementTime;

    //借款金额
    private BigDecimal debtAmount;

    //借款时长
    private Integer borrowPeriod;

    //借款时间单位 0年 1月 2日
    private Integer borrowPeriodUnit;

    //实际到账时间（放款时间）
    private String actualBorrowTime;

    //还款本息
    private BigDecimal totalInterestAmount;

    //还款本金
    private BigDecimal repaymentAmount;

    //还款利息
    private BigDecimal repaymentInterest;

    //咨询费
    private BigDecimal advisoryAmount;

    //借款年利率
    private BigDecimal borrowRate;

    //合同约定借款金额
    private BigDecimal contactAmount;

    //放款金额
    private BigDecimal loanAmount;

    //合同签订时间
    private String contractTime;

    //借款开始时间
    private String borrowStartTime;

    //借款结束时间
    private String borrowEndTime;

    //尚欠本金
    private BigDecimal borrowAmount;

    //尚欠利息
    private BigDecimal interestAmount;

    //是否分批/分期 1是  0否
    private Integer partialFlag;

    //居间方
    private String intermediary;

    //借款用途
    private String borrowPurpose;

    //还款方式
    private String repayWay;

    //红包
    private BigDecimal redPacketMoney;

    //出借人数
    private BigDecimal lendNumber;

    //备注
    private String remark;
}
