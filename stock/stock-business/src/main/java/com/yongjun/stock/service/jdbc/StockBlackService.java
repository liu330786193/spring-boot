package com.yongjun.stock.service.jdbc;

/**
 * Created by 姚磊 on 2017/10/26.
 */
public interface StockBlackService {

  /**
   * 查询股票是否黑名单
   * @param stockCode
   * @return
   */
  boolean isBlack(String stockCode);

}
