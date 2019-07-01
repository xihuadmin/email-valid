package com.zjzc.manage.utils.exception;

public class NetworkException extends RuntimeException{
	
	private static final long serialVersionUID = -2107911255354718686L;
	
	private String message="网络错误";
    
    public NetworkException(String message){
        this.message=message;
    }
    
    public NetworkException(){}

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
