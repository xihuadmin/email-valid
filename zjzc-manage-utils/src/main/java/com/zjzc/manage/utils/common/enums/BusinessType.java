package com.zjzc.manage.utils.common.enums;

import org.springframework.util.StringUtils;

/**
 * 业务类型-说明
 * @author qinzihao
 */
public enum BusinessType {

	/**
	 * 投标
	 */
	INVEST("0", "INVEST"),

	/**
	 * 满标放款
	 */
	LOANS("1", "LATTICE"),

	UNFREEZE("1","UNFREEZE"),

	/**
	 * 转让
	 */
	DEBTOR_TRANSFER_CONFIRM("2", "DEBTOR_TRANSFER_CONFIRM"),

	/**
	 * 回款
	 */
	REPAYMENT("3", "REPAYMENT"),
	PAYMENT("3", "PAYMENT"),

	/**
	 * 其他
	 */
	OTHER("4", "OTHER"),
	USRFREEZE("4", "USRFREEZE"),

	/**
	 * 流标
	 */
	OVER_BIDINVEST("5", "OVER_BIDINVEST"),

	/**
	 * 平台手续费
	 */
	BID_FEE("6", "BID_FEE"),

	/**
	 * 平台代偿
	 */
	ENTRUSTWITHDRAW("7", "ENTRUSTWITHDRAW"),

	/**
	 * 平台营销
	 */
	GRANTCPS("8", "GRANTCPS"),

	/**
	 * 充值查询
	 */
	CZ_PW("PW11", "充值"),

	/**
	 * 提现查询
	 */
	TX_PW("PWTX", "提现"),
	/**
	 * 退票查询
	 */
	TP_PW("PWTP", "退票"),
	;

	/**
	 * 类型
	 */
	private String type;

	/** 说明		*/
	private String desc ;

	private BusinessType(String type, String desc){
		this.type = type;
		this.desc = desc;
	}

	/**
	 * 根据类型编号获取相应的说明
	 * @param type
	 * @return
	 */
	public static BusinessType getRecordStatus(String type){
		if(StringUtils.isEmpty(type)){
			return null;
		}
		for(BusinessType one : BusinessType.values()){
			if(type.equals(one.getType())){
				return one;
			}
		}
		return null;
	}
	
	public String getType() {
		return type;
	}

	public String getDesc() {
		return desc;
	}

	@Override
	public String toString() {
		return "type : " + this.getType() + "，desc : " + this.getDesc();
	}

}
