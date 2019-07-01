package com.zjzc.manage.utils.exception;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Email异常
 */
public class EmailException extends Exception {

    private static final long serialVersionUID = 7816030825588523704L;
    // 错误码
    private final int errorCode;

    /**
     * 异常
     *
     * @param cause
     */
    public EmailException(Throwable cause) {
        super(cause);
        this.errorCode = -1;
    }

    /**
     * 异常
     *
     * @param message
     * @param cause
     */
    public EmailException(String message, Throwable cause) {
        super(message, cause);
        this.errorCode = -1;
    }

    /**
     * 异常
     *
     * @param errorCode
     * @param message
     * @param cause
     */
    public EmailException(int errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }


    /**
     * 异常
     *
     * @param message
     */
    public EmailException(String message) {
        super(message);
        this.errorCode = -1;
    }

    public EmailException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    /**
     * 异常
     *
     * @return
     */
    public String getStackTrack() {
        ByteArrayOutputStream os = new ByteArrayOutputStream(1000);
        this.printStackTrace(new PrintStream(os));
        return os.toString();
    }

    /**
     *
     * @return
     */
    public int getErrorCode() {
        return errorCode;
    }

}
