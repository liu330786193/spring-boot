package com.yongjun.stock.entity;

import java.math.BigDecimal;
import java.util.Date;
/**
 * 策略信息实体类
 */
public class Order {

  /**
   *
   */
  private Long id;

  /**
   * 单号
   */
  private String orderNo;

  /**
   * 商户ID
   */
  private Long companyId;

  /**
   * 客户ID
   */
  private Long customerId;

  /**
   * 股票代码
   */
  private String stockCode;

  /**
   * 股票名称
   */
  private String stockName;

  /**
   * 1 T+1 ，2 T+D 预留
   */
  private Integer orderType;

  /**
   * 交易数量
   */
  private Integer tradeNumber;

  /**
   * 交易本金
   */
  private BigDecimal tradeMoney;

  /**
   * 信用金金额
   */
  private BigDecimal money;

  /**
   * 返还信用金
   */
  private BigDecimal moneyBack;

  /**
   * 资方帐号ID
   */
  private Long investorId;

  /**
   * 计划买入价
   */
  private BigDecimal buyPrice;

  /**
   * 实际买入价
   */
  private BigDecimal buyPriceFact;

  /**
   * 计划买出价
   */
  private BigDecimal sellPrice;

  /**
   * 实际买出价
   */
  private BigDecimal sellPriceFact;

  /**
   * 止损价格
   */
  private BigDecimal stopPrice;

  /**
   * 止盈价格
   */
  private BigDecimal targetPrice;

  /**
   * 创建时间
   */
  private Date createTime;

  /**
   * 委托下单时间
   */
  private Date orderBuyTime;

  /**
   * 下单成交时间
   */
  private Date realBuyTime;

  /**
   * 委托卖出时间
   */
  private Date orderSellTime;

  /**
   * 实际卖出时间
   */
  private Date realSellTime;

  /**
   * 设置为废单时间
   */
  private Date cancelTime;

  /**
   * 结算时间
   */
  private Date settleTime;

  /**
   * 持仓结束时间
   */
  private Date holdEndTime;

  /**
   * 买入类型 0 即时买入 1 委托买入 2 虚拟买入 3人工买入
   */
  private Integer buyType;

  /**
   * 卖出类型 0 即时卖出 1 委托卖出 2 虚拟卖出 3人工卖出
   */
  private Integer sellType;

  /**
   * 客户策略盈亏
   */
  private BigDecimal tradeProfit;

  /**
   * 投资人策略盈亏
   */
  private BigDecimal tradeProfitInvestor;

  /**
   * 总手续费
   */
  private BigDecimal poundageTotal;

  /**
   * 买入手续费
   */
  private BigDecimal poundageBuy;

  /**
   * 过户费
   */
  private BigDecimal transferFee;

  /**
   * 递延总额
   */
  private BigDecimal delayMoney;

  /**
   * 卖出手续费
   */
  private BigDecimal poundageSell;

  /**
   * 印花税
   */
  private BigDecimal stampDuty;

  /**
   * 自动递延 1是 0否
   */
  private Integer autoDelay;

  /**
   * 状态：0 委托买入中 1 买入失败 2 持仓中 3 委托卖出中 4 卖出失败 5已卖出 6已结算 7废单
   */
  private Integer orderStatus;

  /**
   * 人工买入ID
   */
  private Long handbuyId;

  /**
   * 人工卖出ID
   */
  private Long handsellId;

  /**
   * 人工废单ID
   */
  private Long handcancelId;

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
   * 单号
   *
   * @return order_no 单号
   */
  public String getOrderNo() {
    return orderNo;
  }

  /**
   * 单号
   *
   * @param orderNo 单号
   */
  public void setOrderNo(String orderNo) {
    this.orderNo = orderNo == null ? null : orderNo.trim();
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
   * 客户ID
   *
   * @return customer_id 客户ID
   */
  public Long getCustomerId() {
    return customerId;
  }

  /**
   * 客户ID
   *
   * @param customerId 客户ID
   */
  public void setCustomerId(Long customerId) {
    this.customerId = customerId;
  }

  /**
   * 股票代码
   *
   * @return stock_code 股票代码
   */
  public String getStockCode() {
    return stockCode;
  }

  /**
   * 股票代码
   *
   * @param stockCode 股票代码
   */
  public void setStockCode(String stockCode) {
    this.stockCode = stockCode == null ? null : stockCode.trim();
  }

  /**
   * 股票名称
   *
   * @return stock_name 股票名称
   */
  public String getStockName() {
    return stockName;
  }

  /**
   * 股票名称
   *
   * @param stockName 股票名称
   */
  public void setStockName(String stockName) {
    this.stockName = stockName == null ? null : stockName.trim();
  }

  /**
   * 1 T+1 ，2 T+D 预留
   *
   * @return order_type 1 T+1 ，2 T+D 预留
   */
  public Integer getOrderType() {
    return orderType;
  }

  /**
   * 1 T+1 ，2 T+D 预留
   *
   * @param orderType 1 T+1 ，2 T+D 预留
   */
  public void setOrderType(Integer orderType) {
    this.orderType = orderType;
  }

  /**
   * 交易数量
   *
   * @return trade_number 交易数量
   */
  public Integer getTradeNumber() {
    return tradeNumber;
  }

  /**
   * 交易数量
   *
   * @param tradeNumber 交易数量
   */
  public void setTradeNumber(Integer tradeNumber) {
    this.tradeNumber = tradeNumber;
  }

  /**
   * 交易本金
   *
   * @return trade_money 交易本金
   */
  public BigDecimal getTradeMoney() {
    return tradeMoney;
  }

  /**
   * 交易本金
   *
   * @param tradeMoney 交易本金
   */
  public void setTradeMoney(BigDecimal tradeMoney) {
    this.tradeMoney = tradeMoney;
  }

  /**
   * 信用金金额
   *
   * @return money 信用金金额
   */
  public BigDecimal getMoney() {
    return money;
  }

  /**
   * 信用金金额
   *
   * @param money 信用金金额
   */
  public void setMoney(BigDecimal money) {
    this.money = money;
  }

  /**
   * 返还信用金
   *
   * @return money_back 返还信用金
   */
  public BigDecimal getMoneyBack() {
    return moneyBack;
  }

  /**
   * 返还信用金
   *
   * @param moneyBack 返还信用金
   */
  public void setMoneyBack(BigDecimal moneyBack) {
    this.moneyBack = moneyBack;
  }

  /**
   * 资方帐号ID
   *
   * @return investor_id 资方帐号ID
   */
  public Long getInvestorId() {
    return investorId;
  }

  /**
   * 资方帐号ID
   *
   * @param investorId 资方帐号ID
   */
  public void setInvestorId(Long investorId) {
    this.investorId = investorId;
  }

  /**
   * 计划买入价
   *
   * @return buy_price 计划买入价
   */
  public BigDecimal getBuyPrice() {
    return buyPrice;
  }

  /**
   * 计划买入价
   *
   * @param buyPrice 计划买入价
   */
  public void setBuyPrice(BigDecimal buyPrice) {
    this.buyPrice = buyPrice;
  }

  /**
   * 实际买入价
   *
   * @return buy_price_fact 实际买入价
   */
  public BigDecimal getBuyPriceFact() {
    return buyPriceFact;
  }

  /**
   * 实际买入价
   *
   * @param buyPriceFact 实际买入价
   */
  public void setBuyPriceFact(BigDecimal buyPriceFact) {
    this.buyPriceFact = buyPriceFact;
  }

  /**
   * 计划买出价
   *
   * @return sell_price 计划买出价
   */
  public BigDecimal getSellPrice() {
    return sellPrice;
  }

  /**
   * 计划买出价
   *
   * @param sellPrice 计划买出价
   */
  public void setSellPrice(BigDecimal sellPrice) {
    this.sellPrice = sellPrice;
  }

  /**
   * 实际买出价
   *
   * @return sell_price_fact 实际买出价
   */
  public BigDecimal getSellPriceFact() {
    return sellPriceFact;
  }

  /**
   * 实际买出价
   *
   * @param sellPriceFact 实际买出价
   */
  public void setSellPriceFact(BigDecimal sellPriceFact) {
    this.sellPriceFact = sellPriceFact;
  }

  /**
   * 止损价格
   *
   * @return stop_price 止损价格
   */
  public BigDecimal getStopPrice() {
    return stopPrice;
  }

  /**
   * 止损价格
   *
   * @param stopPrice 止损价格
   */
  public void setStopPrice(BigDecimal stopPrice) {
    this.stopPrice = stopPrice;
  }

  /**
   * 止盈价格
   *
   * @return target_price 止盈价格
   */
  public BigDecimal getTargetPrice() {
    return targetPrice;
  }

  /**
   * 止盈价格
   *
   * @param targetPrice 止盈价格
   */
  public void setTargetPrice(BigDecimal targetPrice) {
    this.targetPrice = targetPrice;
  }

  /**
   * 创建时间
   *
   * @return create_time 创建时间
   */
  public Date getCreateTime() {
    return createTime;
  }

  /**
   * 创建时间
   *
   * @param createTime 创建时间
   */
  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  /**
   * 委托下单时间
   *
   * @return order_buy_time 委托下单时间
   */
  public Date getOrderBuyTime() {
    return orderBuyTime;
  }

  /**
   * 委托下单时间
   *
   * @param orderBuyTime 委托下单时间
   */
  public void setOrderBuyTime(Date orderBuyTime) {
    this.orderBuyTime = orderBuyTime;
  }

  /**
   * 下单成交时间
   *
   * @return real_buy_time 下单成交时间
   */
  public Date getRealBuyTime() {
    return realBuyTime;
  }

  /**
   * 下单成交时间
   *
   * @param realBuyTime 下单成交时间
   */
  public void setRealBuyTime(Date realBuyTime) {
    this.realBuyTime = realBuyTime;
  }

  /**
   * 委托卖出时间
   *
   * @return order_sell_time 委托卖出时间
   */
  public Date getOrderSellTime() {
    return orderSellTime;
  }

  /**
   * 委托卖出时间
   *
   * @param orderSellTime 委托卖出时间
   */
  public void setOrderSellTime(Date orderSellTime) {
    this.orderSellTime = orderSellTime;
  }

  /**
   * 实际卖出时间
   *
   * @return real_sell_time 实际卖出时间
   */
  public Date getRealSellTime() {
    return realSellTime;
  }

  /**
   * 实际卖出时间
   *
   * @param realSellTime 实际卖出时间
   */
  public void setRealSellTime(Date realSellTime) {
    this.realSellTime = realSellTime;
  }

  /**
   * 设置为废单时间
   *
   * @return cancel_time 设置为废单时间
   */
  public Date getCancelTime() {
    return cancelTime;
  }

  /**
   * 设置为废单时间
   *
   * @param cancelTime 设置为废单时间
   */
  public void setCancelTime(Date cancelTime) {
    this.cancelTime = cancelTime;
  }

  /**
   * 结算时间
   *
   * @return settle_time 结算时间
   */
  public Date getSettleTime() {
    return settleTime;
  }

  /**
   * 结算时间
   *
   * @param settleTime 结算时间
   */
  public void setSettleTime(Date settleTime) {
    this.settleTime = settleTime;
  }

  /**
   * 持仓结束时间
   *
   * @return hold_end_time 持仓结束时间
   */
  public Date getHoldEndTime() {
    return holdEndTime;
  }

  /**
   * 持仓结束时间
   *
   * @param holdEndTime 持仓结束时间
   */
  public void setHoldEndTime(Date holdEndTime) {
    this.holdEndTime = holdEndTime;
  }

  /**
   * 买入类型 0 即时买入 1 委托买入 2 虚拟买入 3人工买入
   *
   * @return buy_type 买入类型 0 即时买入 1 委托买入 2 虚拟买入 3人工买入
   */
  public Integer getBuyType() {
    return buyType;
  }

  /**
   * 买入类型 0 即时买入 1 委托买入 2 虚拟买入 3人工买入
   *
   * @param buyType 买入类型 0 即时买入 1 委托买入 2 虚拟买入 3人工买入
   */
  public void setBuyType(Integer buyType) {
    this.buyType = buyType;
  }

  /**
   * 卖出类型 0 即时卖出 1 委托卖出 2 虚拟卖出 3人工卖出
   *
   * @return sell_type 卖出类型 0 即时卖出 1 委托卖出 2 虚拟卖出 3人工卖出
   */
  public Integer getSellType() {
    return sellType;
  }

  /**
   * 卖出类型 0 即时卖出 1 委托卖出 2 虚拟卖出 3人工卖出
   *
   * @param sellType 卖出类型 0 即时卖出 1 委托卖出 2 虚拟卖出 3人工卖出
   */
  public void setSellType(Integer sellType) {
    this.sellType = sellType;
  }

  /**
   * 客户策略盈亏
   *
   * @return trade_profit 客户策略盈亏
   */
  public BigDecimal getTradeProfit() {
    return tradeProfit;
  }

  /**
   * 客户策略盈亏
   *
   * @param tradeProfit 客户策略盈亏
   */
  public void setTradeProfit(BigDecimal tradeProfit) {
    this.tradeProfit = tradeProfit;
  }

  /**
   * 投资人策略盈亏
   *
   * @return trade_profit_investor 投资人策略盈亏
   */
  public BigDecimal getTradeProfitInvestor() {
    return tradeProfitInvestor;
  }

  /**
   * 投资人策略盈亏
   *
   * @param tradeProfitInvestor 投资人策略盈亏
   */
  public void setTradeProfitInvestor(BigDecimal tradeProfitInvestor) {
    this.tradeProfitInvestor = tradeProfitInvestor;
  }

  /**
   * 总手续费
   *
   * @return poundage_total 总手续费
   */
  public BigDecimal getPoundageTotal() {
    return poundageTotal;
  }

  /**
   * 总手续费
   *
   * @param poundageTotal 总手续费
   */
  public void setPoundageTotal(BigDecimal poundageTotal) {
    this.poundageTotal = poundageTotal;
  }

  /**
   * 买入手续费
   *
   * @return poundage_buy 买入手续费
   */
  public BigDecimal getPoundageBuy() {
    return poundageBuy;
  }

  /**
   * 买入手续费
   *
   * @param poundageBuy 买入手续费
   */
  public void setPoundageBuy(BigDecimal poundageBuy) {
    this.poundageBuy = poundageBuy;
  }

  /**
   * 过户费
   *
   * @return transfer_fee 过户费
   */
  public BigDecimal getTransferFee() {
    return transferFee;
  }

  /**
   * 过户费
   *
   * @param transferFee 过户费
   */
  public void setTransferFee(BigDecimal transferFee) {
    this.transferFee = transferFee;
  }

  /**
   * 递延总额
   *
   * @return delay_money 递延总额
   */
  public BigDecimal getDelayMoney() {
    return delayMoney;
  }

  /**
   * 递延总额
   *
   * @param delayMoney 递延总额
   */
  public void setDelayMoney(BigDecimal delayMoney) {
    this.delayMoney = delayMoney;
  }

  /**
   * 卖出手续费
   *
   * @return poundage_sell 卖出手续费
   */
  public BigDecimal getPoundageSell() {
    return poundageSell;
  }

  /**
   * 卖出手续费
   *
   * @param poundageSell 卖出手续费
   */
  public void setPoundageSell(BigDecimal poundageSell) {
    this.poundageSell = poundageSell;
  }

  /**
   * 印花税
   *
   * @return stamp_duty 印花税
   */
  public BigDecimal getStampDuty() {
    return stampDuty;
  }

  /**
   * 印花税
   *
   * @param stampDuty 印花税
   */
  public void setStampDuty(BigDecimal stampDuty) {
    this.stampDuty = stampDuty;
  }

  /**
   * 自动递延 1是 0否
   *
   * @return auto_delay 自动递延 1是 0否
   */
  public Integer getAutoDelay() {
    return autoDelay;
  }

  /**
   * 自动递延 1是 0否
   *
   * @param autoDelay 自动递延 1是 0否
   */
  public void setAutoDelay(Integer autoDelay) {
    this.autoDelay = autoDelay;
  }

  /**
   * 状态：0 委托买入中 1 买入失败 2 持仓中 3 委托卖出中 4 卖出失败 5已卖出 6已结算 7废单
   *
   * @return order_status 状态：0 委托买入中 1 买入失败 2 持仓中 3 委托卖出中 4 卖出失败 5已卖出 6已结算 7废单
   */
  public Integer getOrderStatus() {
    return orderStatus;
  }

  /**
   * 状态：0 委托买入中 1 买入失败 2 持仓中 3 委托卖出中 4 卖出失败 5已卖出 6已结算 7废单
   *
   * @param orderStatus 状态：0 委托买入中 1 买入失败 2 持仓中 3 委托卖出中 4 卖出失败 5已卖出 6已结算 7废单
   */
  public void setOrderStatus(Integer orderStatus) {
    this.orderStatus = orderStatus;
  }

  /**
   * 人工买入ID
   *
   * @return handbuy_id 人工买入ID
   */
  public Long getHandbuyId() {
    return handbuyId;
  }

  /**
   * 人工买入ID
   *
   * @param handbuyId 人工买入ID
   */
  public void setHandbuyId(Long handbuyId) {
    this.handbuyId = handbuyId;
  }

  /**
   * 人工卖出ID
   *
   * @return handsell_id 人工卖出ID
   */
  public Long getHandsellId() {
    return handsellId;
  }

  /**
   * 人工卖出ID
   *
   * @param handsellId 人工卖出ID
   */
  public void setHandsellId(Long handsellId) {
    this.handsellId = handsellId;
  }

  /**
   * 人工废单ID
   *
   * @return handcancel_id 人工废单ID
   */
  public Long getHandcancelId() {
    return handcancelId;
  }

  /**
   * 人工废单ID
   *
   * @param handcancelId 人工废单ID
   */
  public void setHandcancelId(Long handcancelId) {
    this.handcancelId = handcancelId;
  }
}