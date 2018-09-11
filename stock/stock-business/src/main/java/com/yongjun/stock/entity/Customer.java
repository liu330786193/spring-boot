package com.yongjun.stock.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Class is created by lyl on 2017/10/24 下午2:24.
 *
 * 用户实体类
 */
public class Customer {

    /**
     * ID
     */
    private Long id;

    /**
     * 所属商户ID
     */
    private Long companyId;

    /**
     * 手机号码
     */
    private String phoneNo;

    /**
     * 登录密码
     */
    private String password;

    /**
     * 交易密码
     */
    private String tradePassword;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 头像
     */
    private String headimgUrl;

    /**
     * 身份证号码
     */
    private String cardNumber;

    /**
     * 认证状态  2已认证 1认证中 0未认证
     */
    private Integer status;

    /**
     * 注册日期
     */
    private Date regDate;

    /**
     * 激活日期
     */
    private Date activeDate;

    /**
     * 认证通过日期
     */
    private Date authedDate;

    /**
     * 最近登录日期
     */
    private Date lastLoginTime;

    /**
     * 设备ID
     */
    private String deviceId;

    /**
     * 手机型号
     */
    private String phoneModel;

    /**
     * 是否黑名单 1是 0否
     */
    private Integer isBlack;

    /**
     * 所属一级代理商ID
     */
    private Long agentId;

    /**
     * 所属二级代理商ID
     */
    private Long subAgentId;

    /**
     * 总资产
     */
    private BigDecimal moneyTotal;

    /**
     * 冻结资产
     */
    private BigDecimal moneyFrozen;

    /**
     * 可用资产
     */
    private BigDecimal moneyUsable;

    /**
     * 充值金额
     */
    private BigDecimal moneyRecharge;

    /**
     * 提现金额
     */
    private BigDecimal moneyWithdraw;

    /**
     * 是否开放实盘
     */
    private Integer isOpen;

    /**
     * 策略通知开关
     */
    private Integer noticeSwitch;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo == null ? null : phoneNo.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getTradePassword() {
        return tradePassword;
    }

    public void setTradePassword(String tradePassword) {
        this.tradePassword = tradePassword == null ? null : tradePassword.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getHeadimgUrl() {
        return headimgUrl;
    }

    public void setHeadimgUrl(String headimgUrl) {
        this.headimgUrl = headimgUrl == null ? null : headimgUrl.trim();
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber == null ? null : cardNumber.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public Date getActiveDate() {
        return activeDate;
    }

    public void setActiveDate(Date activeDate) {
        this.activeDate = activeDate;
    }

    public Date getAuthedDate() {
        return authedDate;
    }

    public void setAuthedDate(Date authedDate) {
        this.authedDate = authedDate;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getPhoneModel() {
        return phoneModel;
    }

    public void setPhoneModel(String phoneModel) {
        this.phoneModel = phoneModel == null ? null : phoneModel.trim();
    }

    public Integer getIsBlack() {
        return isBlack;
    }

    public void setIsBlack(Integer isBlack) {
        this.isBlack = isBlack;
    }

    public Long getAgentId() {
        return agentId;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }

    public Long getSubAgentId() {
        return subAgentId;
    }

    public void setSubAgentId(Long subAgentId) {
        this.subAgentId = subAgentId;
    }

    public BigDecimal getMoneyTotal() {
        return moneyTotal;
    }

    public void setMoneyTotal(BigDecimal moneyTotal) {
        this.moneyTotal = moneyTotal;
    }

    public BigDecimal getMoneyFrozen() {
        return moneyFrozen;
    }

    public void setMoneyFrozen(BigDecimal moneyFrozen) {
        this.moneyFrozen = moneyFrozen;
    }

    public BigDecimal getMoneyUsable() {
        return moneyUsable;
    }

    public void setMoneyUsable(BigDecimal moneyUsable) {
        this.moneyUsable = moneyUsable;
    }

    public BigDecimal getMoneyRecharge() {
        return moneyRecharge;
    }

    public void setMoneyRecharge(BigDecimal moneyRecharge) {
        this.moneyRecharge = moneyRecharge;
    }

    public BigDecimal getMoneyWithdraw() {
        return moneyWithdraw;
    }

    public void setMoneyWithdraw(BigDecimal moneyWithdraw) {
        this.moneyWithdraw = moneyWithdraw;
    }

    public Integer getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(Integer isOpen) {
        this.isOpen = isOpen;
    }

    public Integer getNoticeSwitch() {
        return noticeSwitch;
    }

    public void setNoticeSwitch(Integer noticeSwitch) {
        this.noticeSwitch = noticeSwitch;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}