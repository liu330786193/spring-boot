package com.yongjun.stock.service.jdbc;

import com.yongjun.stock.exception.CBusinessException;

/**
 * Created by 姚磊 on 2017/10/28.
 */
public interface StockService {

  /**
   * 查询股票是否可购买
   * @param stockCode
   * @return
   */
  boolean canBuy(String stockCode) throws CBusinessException;

}
