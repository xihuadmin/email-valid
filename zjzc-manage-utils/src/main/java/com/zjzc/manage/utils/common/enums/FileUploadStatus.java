package com.zjzc.manage.utils.common.enums;

import org.springframework.util.StringUtils;

/**
 * 文件上传状态回馈-说明
 * @author qinzihao
 */
public enum FileUploadStatus {

	SUCCESS("0000", "检验成功"),
	DURING("1000", "等待另一方"),
	ERROR("0001", "数据基本格式校验失败"),
	INFOERROR("0002", "交易信息未到"),
	REPEAT("0003", "该记录已通过核验"),
	WAIT("1000", "等待另一方"),
	LOGINERR("1001", "login id不匹配"),
	NOTTHR("1002", "第三方支付公司不匹配"),
	NOTUSER("1003", "用户名不匹配"),
	NOTCARD("1004", "用户证件号不匹配"),
	NOTUSERTYPE("1005", "用户证件类型不匹配"),
	NOTORGNAME("1006", "企业名称不匹配"),
	NOTMOBILE("1007", "手机号不匹配"),
	NOTTRADETYPE("2001", "交易类型不匹配"),
	NOTTRADEMONEY("2002", "交易金额不匹配"),
	NOTINLOGIN("2003", "入账login id不匹配"),
	NOTINUSERNAME("2004", "入账平台用户名不匹配"),
	NOTOUTLOGIN("2005", "出账login id不匹配"),
	NOTOUTUSERNAME("2006", "出账平台用户名不匹配"),
	NOTTRADEPRODUCT("2007", "交易找不到对应项目"),
	NOTTRADEITEMINFO("2008", "交易不对应相关项目信息"),
	ADDDATAERROR("5138", "增加该数据异常"),
	LOGISREPEAT("5139", "该记录已存在"),
	;

	/**	编号		*/
	private String status ;

	/** 说明		*/
	private String desc ;

	private FileUploadStatus(String status, String desc){
		this.status = status;
		this.desc = desc;
	}

	/**
	 * 根据类型编号获取相应的说明
	 * @param status
	 * @return
	 */
	public static FileUploadStatus getRecordStatus(String status){
		if(StringUtils.isEmpty(status)){
			return null;
		}
		for(FileUploadStatus one : FileUploadStatus.values()){
			if(status.equals(one.getStatus())){
				return one;
			}
		}
		return null;
	}
	
	public String getStatus() {
		return status;
	}

	public String getDesc() {
		return desc;
	}

	@Override
	public String toString() {
		return "type : " + this.getStatus() + "，desc : " + this.getDesc();
	}

}
