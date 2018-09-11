package com.yongjun.stock.exception;

/**
 * Created by lyl on 2016/9/11.
 */
public class CBusinessException extends CRuntimeException{
    public static final String DEFAULT_FAULT_CODE = "X0001";
    private String xCode;
    private String message;

    public CBusinessException(String message) {
        this("X0001", message);
    }

    public CBusinessException(String xCode, String message) {
        this(xCode, message, new Throwable());
    }

    public CBusinessException(String xCode, String message, String internalMessage) {
        this(xCode, message, internalMessage, (Throwable)null);
    }

    public CBusinessException(String code, String message, Throwable throwable) {
        this(code, message, throwable.getMessage(), throwable);
    }

    public CBusinessException(String xCode, String message, String internalMessage, Throwable throwable) {
        super("[" + xCode + "] - " + message + internalMessage, throwable);
        this.message = message;
        this.xCode = xCode;
    }

    public String getXCode() {
        return this.xCode;
    }

    public void setXCode(String xCode) {
        this.xCode = xCode;
    }

    public String getMessageWithoutCode() {
        return this.message;
    }

    public String getMessage() {
        return "[" + this.xCode + "]" + " - " + this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
