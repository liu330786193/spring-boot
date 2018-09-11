package com.yongjun.stock.entity;

/**
 * 基础数据：银行类型实体类
 */
public class BaseBanktype {

  /**
   *
   */
  private Long id;

  /**
   * 类型编号
   */
  private String typeId;

  /**
   * 类型名
   */
  private String typeName;

  /**
   * 类型图片
   */
  private String typeLogo;

  /**
   * 0 无效 1 有效
   */
  private Integer isUsed;

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
   * 类型编号
   *
   * @return type_id 类型编号
   */
  public String getTypeId() {
    return typeId;
  }

  /**
   * 类型编号
   *
   * @param typeId 类型编号
   */
  public void setTypeId(String typeId) {
    this.typeId = typeId == null ? null : typeId.trim();
  }

  /**
   * 类型名
   *
   * @return type_name 类型名
   */
  public String getTypeName() {
    return typeName;
  }

  /**
   * 类型名
   *
   * @param typeName 类型名
   */
  public void setTypeName(String typeName) {
    this.typeName = typeName == null ? null : typeName.trim();
  }

  /**
   * 类型图片
   *
   * @return type_logo 类型图片
   */
  public String getTypeLogo() {
    return typeLogo;
  }

  /**
   * 类型图片
   *
   * @param typeLogo 类型图片
   */
  public void setTypeLogo(String typeLogo) {
    this.typeLogo = typeLogo == null ? null : typeLogo.trim();
  }

  /**
   * 0 无效 1 有效
   *
   * @return is_used 0 无效 1 有效
   */
  public Integer getIsUsed() {
    return isUsed;
  }

  /**
   * 0 无效 1 有效
   *
   * @param isUsed 0 无效 1 有效
   */
  public void setIsUsed(Integer isUsed) {
    this.isUsed = isUsed;
  }
}