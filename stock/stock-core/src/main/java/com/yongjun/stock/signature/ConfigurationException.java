package com.yongjun.stock.signature;

/**
 * Created by lyl on 2017/10/27.
 */
public class ConfigurationException extends SignatureException {


    public ConfigurationException() {
        super();
    }

    public ConfigurationException(String message) {
        super(message);
    }

    public ConfigurationException(Throwable cause) {
        super(cause);
    }

    public ConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }

}
