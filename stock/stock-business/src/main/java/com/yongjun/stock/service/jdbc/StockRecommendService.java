package com.yongjun.stock.service.jdbc;

import com.yongjun.stock.entity.StockRecommend;
import java.util.List;

/**
 * Created by 姚磊 on 2017/10/26.
 */
public interface StockRecommendService {

  /**
   * 查询该商户下的推荐股票
   * @param companyId
   * @return
   */
  List<StockRecommend> getStockRecommend(Long companyId);

}
