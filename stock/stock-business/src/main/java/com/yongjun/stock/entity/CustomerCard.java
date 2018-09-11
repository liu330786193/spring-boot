package com.yongjun.stock.entity;

import java.util.Date;
/**
 * 客户银行卡实体类
 */
public class CustomerCard {
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
     * 银行ID
     */
    private Long bankId;

    /**
     * 银行名称
     */
    private String bankName;

    /**
     * 银行卡号
     */
    private String cardNumber;

    /**
     * 提交审核日期
     */
    private Date submitTime;

    /**
     * 审核日期
     */
    private Date auditTime;

    /**
     * 审核人ID
     */
    private Long auditId;

    /**
     * 1审核通过  0未审核 2审核不通过
     */
    private Integer auditStatus;

    /**
     * 审核失败备注
     */
    private String auditRemark;

    /**
     * 0:有效数据；1:历史数据
     */
    private Integer history;

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
     * 银行ID
     * @return bank_id 银行ID
     */
    public Long getBankId() {
        return bankId;
    }

    /**
     * 银行ID
     * @param bankId 银行ID
     */
    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    /**
     * 银行名称
     * @return bank_name 银行名称
     */
    public String getBankName() {
        return bankName;
    }

    /**
     * 银行名称
     * @param bankName 银行名称
     */
    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }

    /**
     * 银行卡号
     * @return card_number 银行卡号
     */
    public String getCardNumber() {
        return cardNumber;
    }

    /**
     * 银行卡号
     * @param cardNumber 银行卡号
     */
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber == null ? null : cardNumber.trim();
    }

    /**
     * 提交审核日期
     * @return submit_time 提交审核日期
     */
    public Date getSubmitTime() {
        return submitTime;
    }

    /**
     * 提交审核日期
     * @param submitTime 提交审核日期
     */
    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    /**
     * 审核日期
     * @return audit_time 审核日期
     */
    public Date getAuditTime() {
        return auditTime;
    }

    /**
     * 审核日期
     * @param auditTime 审核日期
     */
    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    /**
     * 审核人ID
     * @return audit_id 审核人ID
     */
    public Long getAuditId() {
        return auditId;
    }

    /**
     * 审核人ID
     * @param auditId 审核人ID
     */
    public void setAuditId(Long auditId) {
        this.auditId = auditId;
    }

    /**
     * 1审核通过  0未审核 2审核不通过
     * @return audit_status 1审核通过  0未审核 2审核不通过
     */
    public Integer getAuditStatus() {
        return auditStatus;
    }

    /**
     * 1审核通过  0未审核 2审核不通过
     * @param auditStatus 1审核通过  0未审核 2审核不通过
     */
    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    /**
     * 审核失败备注
     * @return audit_remark 审核失败备注
     */
    public String getAuditRemark() {
        return auditRemark;
    }

    /**
     * 审核失败备注
     * @param auditRemark 审核失败备注
     */
    public void setAuditRemark(String auditRemark) {
        this.auditRemark = auditRemark == null ? null : auditRemark.trim();
    }

    /**
     * 0:有效数据；1:历史数据
     * @return history 0:有效数据；1:历史数据
     */
    public Integer getHistory() {
        return history;
    }

    /**
     * 0:有效数据；1:历史数据
     * @param history 0:有效数据；1:历史数据
     */
    public void setHistory(Integer history) {
        this.history = history;
    }
}