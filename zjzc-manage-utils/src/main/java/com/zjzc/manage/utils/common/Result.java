package com.zjzc.manage.utils.common;

import java.io.Serializable;

import lombok.Data;

import com.zjzc.manage.utils.common.enums.ResponseCode;

/**
 * 接口请求返回值对象
 * @author qinzihao
 *
 * @param <T>
 */
@Data
public class Result<T> implements Serializable {

	private static final long serialVersionUID = -2782986515474548614L;

	/**
     * 返回代码
     */
    private int code ;

    /**
     * 返回信息
     */
    private String msg ;

    /**
     * 返回对象
     */
    private T data;

    public Result(){
    	super();
    }
    
    public Result(int code, String msg, T data){
    	super();
    	this.code = code;
    	this.msg = msg;
    	this.data = data;
    }
    
    public Result(ResponseCode reCode, T data){
    	super();
    	this.code = reCode.getCode();
    	this.msg = reCode.getMsg();
    	this.data = data;
    }
    
    public Result(ResponseCode reCode, T data, String msg){
    	super();
    	this.code = reCode.getCode();
    	this.msg = msg;
    	this.data = data;
    }

	public void setResponseCode(ResponseCode reCode, T data) {
		this.code = reCode.getCode();
    	this.msg = reCode.getMsg();
    	this.data = data;
	}
}
