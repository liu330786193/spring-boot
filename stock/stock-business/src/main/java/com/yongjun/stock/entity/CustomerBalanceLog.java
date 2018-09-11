package com.yongjun.stock.entity;

import java.math.BigDecimal;
import java.util.Date;
/**
 * 客户资金帐户操作日志实体类
 */
public class CustomerBalanceLog {
    /**
     * 主键
     */
    private Long id;

    /**
     * 所属商户ID
     */
    private Long companyId;

    /**
     * 客户ID
     */
    private Long customerId;

    /**
     * 1 充值 2 提现 3冻结 4解冻 5策略结算
     */
    private Integer type;

    /**
     * 金额
     */
    private BigDecimal money;

    /**
     * 备注
     */
    private String remark;

    /**
     * 日期
     */
    private Date createTime;

    /**
     * 主键
     * @return id 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 主键
     * @param id 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 所属商户ID
     * @return company_id 所属商户ID
     */
    public Long getCompanyId() {
        return companyId;
    }

    /**
     * 所属商户ID
     * @param companyId 所属商户ID
     */
    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    /**
     * 客户ID
     * @return customer_id 客户ID
     */
    public Long getCustomerId() {
        return customerId;
    }

    /**
     * 客户ID
     * @param customerId 客户ID
     */
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    /**
     * 1 充值 2 提现 3冻结 4解冻 5策略结算
     * @return type 1 充值 2 提现 3冻结 4解冻 5策略结算
     */
    public Integer getType() {
        return type;
    }

    /**
     * 1 充值 2 提现 3冻结 4解冻 5策略结算
     * @param type 1 充值 2 提现 3冻结 4解冻 5策略结算
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 金额
     * @return money 金额
     */
    public BigDecimal getMoney() {
        return money;
    }

    /**
     * 金额
     * @param money 金额
     */
    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    /**
     * 备注
     * @return remark 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 日期
     * @return create_time 日期
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 日期
     * @param createTime 日期
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}