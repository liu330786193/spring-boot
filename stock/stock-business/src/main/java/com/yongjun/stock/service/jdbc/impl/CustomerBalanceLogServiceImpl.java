package com.yongjun.stock.service.jdbc.impl;

import com.yongjun.stock.dao.CustomerBalanceLogDao;
import com.yongjun.stock.service.jdbc.CustomerBalanceLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by 姚磊 on 2017/10/26.
 */

@Service
public class CustomerBalanceLogServiceImpl implements CustomerBalanceLogService {

  @Resource
  private CustomerBalanceLogDao customerBalanceLogDao;

}
