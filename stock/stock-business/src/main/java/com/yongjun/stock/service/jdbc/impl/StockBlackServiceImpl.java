package com.yongjun.stock.service.jdbc.impl;

import com.yongjun.stock.dao.StockBlackDao;
import com.yongjun.stock.service.jdbc.StockBlackService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by 姚磊 on 2017/10/26.
 */

@Service
public class StockBlackServiceImpl implements StockBlackService {

  @Resource
  private StockBlackDao stockBlackDao;

  @Value("${companyId}")
  private Long companyId;

  @Override
  public boolean isBlack(String stockCode) {
    return null != stockBlackDao.selectByStockCode(companyId, stockCode);
  }
}
