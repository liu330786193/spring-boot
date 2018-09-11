package com.yongjun.stock.entity;

import java.math.BigDecimal;
import java.util.Date;
/**
 * 客户充值提现实体类
 */
public class CustomerBalance {
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
     * 银行名称
     */
    private String bankName;

    /**
     * 银行卡号
     */
    private String cardNumber;

    /**
     * 1 充值 2 提现
     */
    private Integer type;

    /**
     * 充值或提现金额
     */
    private BigDecimal money;

    /**
     * 提交日期
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
     * 第三方调用成功或失败时间
     */
    private Date transTime;

    /**
     * 状态 0 初始 1成功 2失败
     */
    private Integer transStatus;

    /**
     * 是否人工处理 1是 0否
     */
    private Integer isHand;

    /**
     * 人工处理员ID
     */
    private Long operUserId;

    /**
     * 人工处理凭证
     */
    private String operImg;

    /**
     * 人工处理时间
     */
    private Date operTime;

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
     * 1 充值 2 提现
     * @return type 1 充值 2 提现
     */
    public Integer getType() {
        return type;
    }

    /**
     * 1 充值 2 提现
     * @param type 1 充值 2 提现
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 充值或提现金额
     * @return money 充值或提现金额
     */
    public BigDecimal getMoney() {
        return money;
    }

    /**
     * 充值或提现金额
     * @param money 充值或提现金额
     */
    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    /**
     * 提交日期
     * @return submit_time 提交日期
     */
    public Date getSubmitTime() {
        return submitTime;
    }

    /**
     * 提交日期
     * @param submitTime 提交日期
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
     * 第三方调用成功或失败时间
     * @return trans_time 第三方调用成功或失败时间
     */
    public Date getTransTime() {
        return transTime;
    }

    /**
     * 第三方调用成功或失败时间
     * @param transTime 第三方调用成功或失败时间
     */
    public void setTransTime(Date transTime) {
        this.transTime = transTime;
    }

    /**
     * 状态 0 初始 1成功 2失败
     * @return trans_status 状态 0 初始 1成功 2失败
     */
    public Integer getTransStatus() {
        return transStatus;
    }

    /**
     * 状态 0 初始 1成功 2失败
     * @param transStatus 状态 0 初始 1成功 2失败
     */
    public void setTransStatus(Integer transStatus) {
        this.transStatus = transStatus;
    }

    /**
     * 是否人工处理 1是 0否
     * @return is_hand 是否人工处理 1是 0否
     */
    public Integer getIsHand() {
        return isHand;
    }

    /**
     * 是否人工处理 1是 0否
     * @param isHand 是否人工处理 1是 0否
     */
    public void setIsHand(Integer isHand) {
        this.isHand = isHand;
    }

    /**
     * 人工处理员ID
     * @return oper_user_id 人工处理员ID
     */
    public Long getOperUserId() {
        return operUserId;
    }

    /**
     * 人工处理员ID
     * @param operUserId 人工处理员ID
     */
    public void setOperUserId(Long operUserId) {
        this.operUserId = operUserId;
    }

    /**
     * 人工处理凭证
     * @return oper_img 人工处理凭证
     */
    public String getOperImg() {
        return operImg;
    }

    /**
     * 人工处理凭证
     * @param operImg 人工处理凭证
     */
    public void setOperImg(String operImg) {
        this.operImg = operImg == null ? null : operImg.trim();
    }

    /**
     * 人工处理时间
     * @return oper_time 人工处理时间
     */
    public Date getOperTime() {
        return operTime;
    }

    /**
     * 人工处理时间
     * @param operTime 人工处理时间
     */
    public void setOperTime(Date operTime) {
        this.operTime = operTime;
    }
}