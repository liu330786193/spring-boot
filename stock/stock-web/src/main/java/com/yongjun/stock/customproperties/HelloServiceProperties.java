package com.yongjun.stock.customproperties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by lyl on 2017/6/4.
 */
@ConfigurationProperties(prefix = "custom.hello")
public class HelloServiceProperties {

    private static final String MSG = "World";

    private String msg = MSG;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
