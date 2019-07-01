package com.zjzc.manage.utils.common;

/**
 * Created by lijun on 2017/8/7 18:10.
 */
public class JsonEntity {
    private String code;

    private String msg;

    private boolean result;

    public JsonEntity(boolean b) {
        this.result = b;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public JsonEntity() {
        super();
    }

    public JsonEntity(String code,String msg,boolean result) {
        super();
        this.code= code;
        this.msg = msg;
        this.result = result;
    }

    public JsonEntity(String msg,boolean result) {
        super();
        this.msg = msg;
        this.result = result;
    }

    public JsonEntity(String msg,String code) {
        this.msg = msg;
        this.code = code;
    }
}
