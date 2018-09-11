package com.yongjun.stock.entity;
/**
 * 商户配置实体类
 */
public class Config {

  /**
   *
   */
  private Long id;

  /**
   * 商户ID
   */
  private Long companyId;

  /**
   *
   */
  private String type;

  /**
   *
   */
  private String value;

  /**
   *
   */
  private Integer isUsed;

  /**
   * 说明
   */
  private String remark;

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
   * 商户ID
   *
   * @return company_id 商户ID
   */
  public Long getCompanyId() {
    return companyId;
  }

  /**
   * 商户ID
   *
   * @param companyId 商户ID
   */
  public void setCompanyId(Long companyId) {
    this.companyId = companyId;
  }

  /**
   * @return type
   */
  public String getType() {
    return type;
  }

  /**
   *
   * @param type
   */
  public void setType(String type) {
    this.type = type == null ? null : type.trim();
  }

  /**
   * @return value
   */
  public String getValue() {
    return value;
  }

  /**
   *
   * @param value
   */
  public void setValue(String value) {
    this.value = value == null ? null : value.trim();
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
   * 说明
   *
   * @return remark 说明
   */
  public String getRemark() {
    return remark;
  }

  /**
   * 说明
   *
   * @param remark 说明
   */
  public void setRemark(String remark) {
    this.remark = remark == null ? null : remark.trim();
  }
}