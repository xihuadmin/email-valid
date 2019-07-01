package com.zjzc.manage.utils.others;

import com.zjzc.manage.utils.common.enums.BusinessType;

/**
 * Created by lijun on 2017/8/11 9:38.
 */
public class CommonTools {

    public static String BusinessUtils(String type){
        String desc = "";
        if(type.equals(BusinessType.ENTRUSTWITHDRAW.getDesc())){
            desc = BusinessType.ENTRUSTWITHDRAW.getType();
        }else if(type.equals(BusinessType.BID_FEE.getDesc())){
            desc = BusinessType.BID_FEE.getType();
        }else if(type.equals(BusinessType.DEBTOR_TRANSFER_CONFIRM.getDesc())){
            desc = BusinessType.DEBTOR_TRANSFER_CONFIRM.getType();
        }else if(type.equals(BusinessType.GRANTCPS.getDesc())){
            desc = BusinessType.GRANTCPS.getType();
        }else if(type.equals(BusinessType.INVEST.getDesc())){
            desc = BusinessType.INVEST.getType();
        }else if(type.equals(BusinessType.OVER_BIDINVEST.getDesc())){
            desc = BusinessType.OVER_BIDINVEST.getType();
        }else if(type.equals(BusinessType.LOANS.getDesc())){
            desc = BusinessType.LOANS.getType();
        }else if(type.equals(BusinessType.PAYMENT.getDesc()) || type.equals(BusinessType.REPAYMENT.getDesc())){
            desc = BusinessType.PAYMENT.getType();
        }else if(type.equals(BusinessType.UNFREEZE.getDesc())){
            desc = BusinessType.UNFREEZE.getType();
        }else if(type.equals(BusinessType.USRFREEZE.getDesc())){
            desc = BusinessType.USRFREEZE.getType();
        }else{
            desc = BusinessType.OTHER.getType();
        }
        return desc;
    }
    public static void main (String [] s){
        System.out.println(BusinessType.ENTRUSTWITHDRAW.getDesc());
    }
}
