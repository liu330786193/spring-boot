package com.yongjun.stock.request;

import javax.validation.constraints.NotNull;

/**
 * Created by lyl on 2017/10/25.
 */
public class LoginRequest {

    /**
     * 用户名
     */
    @NotNull(message = "BASESTOCK_LOGIN_1206")
    private String username;

    /**
     * 密码
     */
    @NotNull(message = "BASESTOCK_LOGIN_1206")

    private String password;

    private int pageSize;

    private int pageNum;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }
}
