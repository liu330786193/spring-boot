package com.yongjun.stock.dao;

import com.yongjun.stock.entity.CustomerBalance;

public interface CustomerBalanceDao {
    /**
     *
     * generated by  mybatis generator 2017-10-26
     */
    int deleteByPrimaryKey(Long id);

    /**
     *
     * generated by  mybatis generator 2017-10-26
     */
    int insert(CustomerBalance record);

    /**
     *
     * generated by  mybatis generator 2017-10-26
     */
    int insertSelective(CustomerBalance record);

    /**
     *
     * generated by  mybatis generator 2017-10-26
     */
    CustomerBalance selectByPrimaryKey(Long id);

    /**
     *
     * generated by  mybatis generator 2017-10-26
     */
    int updateByPrimaryKeySelective(CustomerBalance record);

    /**
     *
     * generated by  mybatis generator 2017-10-26
     */
    int updateByPrimaryKey(CustomerBalance record);
}