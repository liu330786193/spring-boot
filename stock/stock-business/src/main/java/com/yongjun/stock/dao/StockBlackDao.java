package com.yongjun.stock.dao;

import com.yongjun.stock.entity.StockBlack;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StockBlackDao {

    /**
     * 查询某只股票是否黑名单
     * @param companyId
     * @param code
     * @return
     */
    StockBlack selectByStockCode(@Param("companyId") Long companyId, @Param("code") String code);
}