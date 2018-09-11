package com.yongjun.stock.service.jdbc.impl;

import com.yongjun.stock.constants.StockConstants;
import com.yongjun.stock.entity.StockBlack;
import com.yongjun.stock.exception.CBusinessException;
import com.yongjun.stock.exception.CExceptionFactory;
import com.yongjun.stock.service.jdbc.StockBlackService;
import com.yongjun.stock.service.jdbc.StockService;
import com.yongjun.stock.util.RedisUtils;
import com.yongjun.stock.util.StringUtils;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * Created by 姚磊 on 2017/10/28.
 */
@Service
public class StockServiceImpl implements StockService {

  @Resource
  private StockBlackService stockBlackService;

  @Override
  public boolean canBuy(String stockCode) throws CBusinessException {
    //todo redis获取当前购买策略开关(即每日开市和休市时间点quartz刷新开市开关)
    String flag = RedisUtils.get(StockConstants.STOCK_FLAG, String.class);
    if (!StringUtils.equals(flag, StockConstants.OPEN)) {
      //todo 休市 error code
      throw CExceptionFactory.create("****");
    }

    if (stockBlackService.isBlack(stockCode)) {
      //todo error code 黑名单
      throw CExceptionFactory.create("****");
    }
    return true;
  }
}
