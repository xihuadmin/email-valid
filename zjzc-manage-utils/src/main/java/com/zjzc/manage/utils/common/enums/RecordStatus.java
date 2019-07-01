package com.zjzc.manage.utils.common.enums;

/**
 * 交互记录状态-说明
 * @author qinzihao
 */
public enum RecordStatus {

	/**
	 * 处理中
	 */
	DURING(1, "处理中"),
	
	/**
	 * 回调中
	 */
	CALLBACK(2, "回调中"),
	
	/**
	 * 完成
	 */
	FINISH(3, "完成"),
	
	;
	
	/**	编号		*/
	private int status ;
	
	/** 说明		*/
	private String desc ;
	
	private RecordStatus(int status, String desc){
		this.status = status;
		this.desc = desc;
	}

	/**
	 * 根据类型编号获取相应的说明
	 * @param status
	 * @return
	 */
	public static RecordStatus getRecordStatus(int status){
		if(status == 0){
			return null;
		}
		for(RecordStatus one : RecordStatus.values()){
			if(status == one.getStatus()){
				return one;
			}
		}
		return null;
	}
	
	public int getStatus() {
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
