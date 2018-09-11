package com.yongjun.stock.entity;

import java.math.BigDecimal;
import java.util.Date;
/**
 * 策略递延明细实体类
 */
public class OrderDelay {

  /**
   *
   */
  private Long id;

  /**
   * 策略ID
   */
  private Long orderId;

  /**
   * 递延日期
   */
  private Date delayDate;

  /**
   * 递延金额
   */
  private BigDecimal delayMoney;

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
   * 策略ID
   *
   * @return order_id 策略ID
   */
  public Long getOrderId() {
    return orderId;
  }

  /**
   * 策略ID
   *
   * @param orderId 策略ID
   */
  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }

  /**
   * 递延日期
   *
   * @return delay_date 递延日期
   */
  public Date getDelayDate() {
    return delayDate;
  }

  /**
   * 递延日期
   *
   * @param delayDate 递延日期
   */
  public void setDelayDate(Date delayDate) {
    this.delayDate = delayDate;
  }

  /**
   * 递延金额
   *
   * @return delay_money 递延金额
   */
  public BigDecimal getDelayMoney() {
    return delayMoney;
  }

  /**
   * 递延金额
   *
   * @param delayMoney 递延金额
   */
  public void setDelayMoney(BigDecimal delayMoney) {
    this.delayMoney = delayMoney;
  }
}