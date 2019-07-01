package com.zjzc.manage.utils.common.enums;
import org.apache.commons.lang3.StringUtils;

/**
 * 下载类型说明
 * @author lijun
 */
public enum DownloadType {

	DOWNLOAD_WAIT("0","待下载"),
	DOWNLOAD_SUCCESS("1","下载完成"),
	DOWNLOAD_ERROR("2","下载失败"),
	;

	/**	类型*/
	private String type ;

	/** 说明*/
	private String desc ;

	DownloadType(String type, String desc){
		this.type = type;
		this.desc = desc;
	}

	/**
	 * 根据类型获取相应的说明
	 * @param type
	 * @return
	 */
	public static DownloadType getReportDesc(String type){
		if(StringUtils.isEmpty(type)){
			return null;
		}
		for(DownloadType one : DownloadType.values()){
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
