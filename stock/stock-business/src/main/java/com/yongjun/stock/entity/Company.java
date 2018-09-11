package com.yongjun.stock.entity;

import java.util.Date;

/**
 * 商户实体类
 */
public class Company {

  /**
   *
   */
  private Long id;

  /**
   * 商户名
   */
  private String name;

  /**
   * 商户简称-登录使用
   */
  private String shortName;

  /**
   * 分润说明
   */
  private String moneyDesc;

  /**
   * 商户联系人电话
   */
  private String phoneNo;

  /**
   * 商户联系人名称
   */
  private String linkMan;

  /**
   * 商户logo
   */
  private String logo;

  /**
   * 银行名称
   */
  private String bankName;

  /**
   * 银行帐号
   */
  private String bankNumber;

  /**
   * 商户备注`
   */
  private String remark;

  /**
   *
   */
  private Long createUserId;

  /**
   *
   */
  private Date createTime;

  /**
   *
   */
  private Long modifyUserId;

  /**
   *
   */
  private Date modifyTime;

  /**
   *
   */
  private Integer isUsed;

  /**
   * 1合作，2不合作 0已删除
   */
  private Integer status;

  /**
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
   * 商户名
   *
   * @return name 商户名
   */
  public String getName() {
    return name;
  }

  /**
   * 商户名
   *
   * @param name 商户名
   */
  public void setName(String name) {
    this.name = name == null ? null : name.trim();
  }

  /**
   * 商户简称-登录使用
   *
   * @return short_name 商户简称-登录使用
   */
  public String getShortName() {
    return shortName;
  }

  /**
   * 商户简称-登录使用
   *
   * @param shortName 商户简称-登录使用
   */
  public void setShortName(String shortName) {
    this.shortName = shortName == null ? null : shortName.trim();
  }

  /**
   * 分润说明
   *
   * @return money_desc 分润说明
   */
  public String getMoneyDesc() {
    return moneyDesc;
  }

  /**
   * 分润说明
   *
   * @param moneyDesc 分润说明
   */
  public void setMoneyDesc(String moneyDesc) {
    this.moneyDesc = moneyDesc == null ? null : moneyDesc.trim();
  }

  /**
   * 商户联系人电话
   *
   * @return phone_no 商户联系人电话
   */
  public String getPhoneNo() {
    return phoneNo;
  }

  /**
   * 商户联系人电话
   *
   * @param phoneNo 商户联系人电话
   */
  public void setPhoneNo(String phoneNo) {
    this.phoneNo = phoneNo == null ? null : phoneNo.trim();
  }

  /**
   * 商户联系人名称
   *
   * @return link_man 商户联系人名称
   */
  public String getLinkMan() {
    return linkMan;
  }

  /**
   * 商户联系人名称
   *
   * @param linkMan 商户联系人名称
   */
  public void setLinkMan(String linkMan) {
    this.linkMan = linkMan == null ? null : linkMan.trim();
  }

  /**
   * 商户logo
   *
   * @return logo 商户logo
   */
  public String getLogo() {
    return logo;
  }

  /**
   * 商户logo
   *
   * @param logo 商户logo
   */
  public void setLogo(String logo) {
    this.logo = logo == null ? null : logo.trim();
  }

  /**
   * 银行名称
   *
   * @return bank_name 银行名称
   */
  public String getBankName() {
    return bankName;
  }

  /**
   * 银行名称
   *
   * @param bankName 银行名称
   */
  public void setBankName(String bankName) {
    this.bankName = bankName == null ? null : bankName.trim();
  }

  /**
   * 银行帐号
   *
   * @return bank_number 银行帐号
   */
  public String getBankNumber() {
    return bankNumber;
  }

  /**
   * 银行帐号
   *
   * @param bankNumber 银行帐号
   */
  public void setBankNumber(String bankNumber) {
    this.bankNumber = bankNumber == null ? null : bankNumber.trim();
  }

  /**
   * 商户备注`
   *
   * @return remark 商户备注`
   */
  public String getRemark() {
    return remark;
  }

  /**
   * 商户备注`
   *
   * @param remark 商户备注`
   */
  public void setRemark(String remark) {
    this.remark = remark == null ? null : remark.trim();
  }

  /**
   * @return create_user_id
   */
  public Long getCreateUserId() {
    return createUserId;
  }

  /**
   *
   * @param createUserId
   */
  public void setCreateUserId(Long createUserId) {
    this.createUserId = createUserId;
  }

  /**
   * @return create_time
   */
  public Date getCreateTime() {
    return createTime;
  }

  /**
   *
   * @param createTime
   */
  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  /**
   * @return modify_user_id
   */
  public Long getModifyUserId() {
    return modifyUserId;
  }

  /**
   *
   * @param modifyUserId
   */
  public void setModifyUserId(Long modifyUserId) {
    this.modifyUserId = modifyUserId;
  }

  /**
   * @return modify_time
   */
  public Date getModifyTime() {
    return modifyTime;
  }

  /**
   *
   * @param modifyTime
   */
  public void setModifyTime(Date modifyTime) {
    this.modifyTime = modifyTime;
  }

  /**
   * @return is_used
   */
  public Integer getIsUsed() {
    return isUsed;
  }

  /**
   *
   * @param isUsed
   */
  public void setIsUsed(Integer isUsed) {
    this.isUsed = isUsed;
  }

  /**
   * 1合作，2不合作 0已删除
   *
   * @return status 1合作，2不合作 0已删除
   */
  public Integer getStatus() {
    return status;
  }

  /**
   * 1合作，2不合作 0已删除
   *
   * @param status 1合作，2不合作 0已删除
   */
  public void setStatus(Integer status) {
    this.status = status;
  }
}