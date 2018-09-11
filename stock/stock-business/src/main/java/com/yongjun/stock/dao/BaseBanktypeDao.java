package com.yongjun.stock.dao;

import com.yongjun.stock.entity.BaseBanktype;
import java.util.List;

public interface BaseBanktypeDao {

    /**
     * 根据查询条件查询银行信息
     * @param baseBanktype
     * @return
     */
    List<BaseBanktype> selectByCondition(BaseBanktype baseBanktype);
}