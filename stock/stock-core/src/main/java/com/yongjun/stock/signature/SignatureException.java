package com.yongjun.stock.signature;

/**
 * Created by lyl on 2017/10/27.
 */
public class SignatureException extends RuntimeException{

    public SignatureException() {
        super();
    }

    public SignatureException(String message) {
        super(message);
    }

    public SignatureException(Throwable cause) {
        super(cause);
    }

    public SignatureException(String message, Throwable cause) {
        super(message, cause);
    }

}
