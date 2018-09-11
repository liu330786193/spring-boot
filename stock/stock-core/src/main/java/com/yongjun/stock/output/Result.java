package com.yongjun.stock.output;

/**
 * Created by lyl on 2016/9/11.
 */
public class Result {
    private String status;
    private String msg;
    private Object body;

    public Result() {
    }

    public Result(String status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public Result(String status, String msg, Object body) {
        this.status = status;
        this.msg = msg;
        this.body = body;
    }

    public String getCode() {
        return this.status;
    }

    public void setCode(String status) {
        this.status = status;
    }

    public String getDescription() {
        return this.msg;
    }

    public void setDescription(String msg) {
        this.msg = msg;
    }

    public Object getResult() {
        return this.body;
    }

    public void setResult(Object body) {
        this.body = body;
    }
}
