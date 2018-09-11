package com.yongjun.stock.customproperties;

/**
 * Created by lyl on 2017/6/4.
 */
public class HelloService {

    private String msg;

    public String sayHello(){
        return "Hello " + msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
