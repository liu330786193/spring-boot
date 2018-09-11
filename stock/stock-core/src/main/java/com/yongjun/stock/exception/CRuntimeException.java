package com.yongjun.stock.exception;

/**
 * Created by lyl on 2016/9/11.
 */
public class CRuntimeException extends RuntimeException {

    public CRuntimeException() {
    }

    public CRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

}
