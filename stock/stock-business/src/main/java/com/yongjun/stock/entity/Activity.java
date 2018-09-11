package com.yongjun.stock.entity;

import java.util.Date;

/**
 * 活动实体类
 */

public class Activity {

  /**
   *
   */
  private Long id;

  /**
   * 商户ID
   */
  private Long companyId;

  /**
   * 活动标题
   */
  private String title;

  /**
   * 开始时间
   */
  private Date beginTime;

  /**
   * 结束时间
   */
  private Date endTime;

  /**
   * 活动图
   */
  private String imageUrl;

  /**
   * 链接跳转url
   */
  private String linkUrl;

  /**
   * app跳转url
   */
  private String appUrl;

  /**
   * 状态(1:已上架； 0未上架）
   */
  private Byte status;

  /**
   *
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
   * 活动标题
   *
   * @return title 活动标题
   */
  public String getTitle() {
    return title;
  }

  /**
   * 活动标题
   *
   * @param title 活动标题
   */
  public void setTitle(String title) {
    this.title = title == null ? null : title.trim();
  }

  /**
   * 开始时间
   *
   * @return begin_time 开始时间
   */
  public Date getBeginTime() {
    return beginTime;
  }

  /**
   * 开始时间
   *
   * @param beginTime 开始时间
   */
  public void setBeginTime(Date beginTime) {
    this.beginTime = beginTime;
  }

  /**
   * 结束时间
   *
   * @return end_time 结束时间
   */
  public Date getEndTime() {
    return endTime;
  }

  /**
   * 结束时间
   *
   * @param endTime 结束时间
   */
  public void setEndTime(Date endTime) {
    this.endTime = endTime;
  }

  /**
   * 活动图
   *
   * @return image_url 活动图
   */
  public String getImageUrl() {
    return imageUrl;
  }

  /**
   * 活动图
   *
   * @param imageUrl 活动图
   */
  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl == null ? null : imageUrl.trim();
  }

  /**
   * 链接跳转url
   *
   * @return link_url 链接跳转url
   */
  public String getLinkUrl() {
    return linkUrl;
  }

  /**
   * 链接跳转url
   *
   * @param linkUrl 链接跳转url
   */
  public void setLinkUrl(String linkUrl) {
    this.linkUrl = linkUrl == null ? null : linkUrl.trim();
  }

  /**
   * app跳转url
   *
   * @return app_url app跳转url
   */
  public String getAppUrl() {
    return appUrl;
  }

  /**
   * app跳转url
   *
   * @param appUrl app跳转url
   */
  public void setAppUrl(String appUrl) {
    this.appUrl = appUrl == null ? null : appUrl.trim();
  }

  /**
   * 状态(1:已上架； 0未上架）
   *
   * @return status 状态(1:已上架； 0未上架）
   */
  public Byte getStatus() {
    return status;
  }

  /**
   * 状态(1:已上架； 0未上架）
   *
   * @param status 状态(1:已上架； 0未上架）
   */
  public void setStatus(Byte status) {
    this.status = status;
  }

  /**
   * @return remark
   */
  public String getRemark() {
    return remark;
  }

  /**
   *
   * @param remark
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