package com.zjzc.manage.core.model.po;

import com.zjzc.manage.utils.common.enums.UserType;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created by lijun on 2017/9/7 19:38.
 */
@Data
@ToString
public class TblUserBalancePo implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String mobile;        // 用户手机
    private String userName;        // 用户名称
    private String availableAmount;        // 可用余额
    private String totalAmount;              //总余额
    private String frozenAmount;        // 冻结余额
    private String withdrawAmount;        // 可提现余额
    private String withdrawFrozenAmount;        // 提现冻结余额
    private String optTime;        // 记录时间
    private String fyUpdateTime;        // 富友同步时间
    private String userId;        // 用户id
    private String ctBalance;        // 富友账面总余额
    private String caBalance;        // 富友可用余额
    private String cfBalance;        // 富友冻结余额
    private String cuBalance;        // 富友未转结余额
    private String hours;              //时间段
    private String version;             //日期标识
    private String userType;            //用户类型 区分是借款还是投资人 0 未确定 1 借款会员 2 投资会员 3 复合会员

    public String getUserTypeDesc(){
        return UserType.getReportDesc(this.userType).getDesc();
    }
}
