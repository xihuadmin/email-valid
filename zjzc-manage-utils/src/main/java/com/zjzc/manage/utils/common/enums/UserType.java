package com.zjzc.manage.utils.common.enums;
import org.apache.commons.lang3.StringUtils;

/**
 * 用户类型说明
 * @author lijun
 */
public enum UserType {

	USER_IN("0","未确定"),
	USER_DZ("1","借款会员"),
	USER_JD("2","投资会员"),
	USER_OH("3","复合会员"),
	;

	/**	类型*/
	private String type ;

	/** 说明*/
	private String desc ;

	UserType(String type, String desc){
		this.type = type;
		this.desc = desc;
	}

	/**
	 * 根据类型获取相应的说明
	 * @param type
	 * @return
	 */
	public static UserType getReportDesc(String type){
		if(StringUtils.isEmpty(type)){
			return null;
		}
		for(UserType one : UserType.values()){
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
