package com.yongjun.stock.service.jdbc.impl;

import com.yongjun.stock.dao.StockRecommendDao;
import com.yongjun.stock.entity.StockRecommend;
import com.yongjun.stock.service.jdbc.StockRecommendService;
import java.util.List;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by 姚磊 on 2017/10/26.
 */

@Service
public class StockRecommendServiceImpl implements StockRecommendService {

  @Resource
  private StockRecommendDao stockRecommendDao;

  @Override
  public List<StockRecommend> getStockRecommend(Long companyId) {
    return stockRecommendDao.selectByCompany(companyId);
  }
}
