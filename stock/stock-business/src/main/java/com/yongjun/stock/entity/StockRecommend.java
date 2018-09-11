package com.yongjun.stock.entity;

import java.util.Date;
/**
 * 推荐股票信息实体类
 */
public class StockRecommend {
    /**
     * 
     */
    private Long id;

    /**
     * 商户ID
     */
    private Long companyId;

    /**
     * 股票代码
     */
    private String code;

    /**
     * 股票名
     */
    private String name;

    /**
     * 1 有效 0 无效
     */
    private Integer status;

    /**
     * 添加日期
     */
    private Date addTime;

    /**
     * 
     * @return id 
     */
    public Long getId() {
        return id;
    }

    /**
     * 
     * @param id 
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 商户ID
     * @return company_id 商户ID
     */
    public Long getCompanyId() {
        return companyId;
    }

    /**
     * 商户ID
     * @param companyId 商户ID
     */
    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    /**
     * 股票代码
     * @return code 股票代码
     */
    public String getCode() {
        return code;
    }

    /**
     * 股票代码
     * @param code 股票代码
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 股票名
     * @return name 股票名
     */
    public String getName() {
        return name;
    }

    /**
     * 股票名
     * @param name 股票名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 1 有效 0 无效
     * @return status 1 有效 0 无效
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 1 有效 0 无效
     * @param status 1 有效 0 无效
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 添加日期
     * @return add_time 添加日期
     */
    public Date getAddTime() {
        return addTime;
    }

    /**
     * 添加日期
     * @param addTime 添加日期
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}