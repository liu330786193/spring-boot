package com.yongjun.stock.entity;

/**
 * 平台引流黑名单实体类
 */
public class Blacklist {

  /**
   * 主键
   */
  private Long id;

  /**
   * 来源
   */
  private String platform;

  /**
   * 手机号
   */
  private String phoneNo;

  /**
   * 客户姓名
   */
  private String custName;

  /**
   *
   */
  private Integer deleted;

  /**
   * 主键
   *
   * @return id 主键
   */
  public Long getId() {
    return id;
  }

  /**
   * 主键
   *
   * @param id 主键
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * 来源
   *
   * @return platform 来源
   */
  public String getPlatform() {
    return platform;
  }

  /**
   * 来源
   *
   * @param platform 来源
   */
  public void setPlatform(String platform) {
    this.platform = platform == null ? null : platform.trim();
  }

  /**
   * 手机号
   *
   * @return phone_no 手机号
   */
  public String getPhoneNo() {
    return phoneNo;
  }

  /**
   * 手机号
   *
   * @param phoneNo 手机号
   */
  public void setPhoneNo(String phoneNo) {
    this.phoneNo = phoneNo == null ? null : phoneNo.trim();
  }

  /**
   * 客户姓名
   *
   * @return cust_name 客户姓名
   */
  public String getCustName() {
    return custName;
  }

  /**
   * 客户姓名
   *
   * @param custName 客户姓名
   */
  public void setCustName(String custName) {
    this.custName = custName == null ? null : custName.trim();
  }

  /**
   * @return deleted
   */
  public Integer getDeleted() {
    return deleted;
  }

  /**
   *
   * @param deleted
   */
  public void setDeleted(Integer deleted) {
    this.deleted = deleted;
  }
}