package com.yongjun.stock.service.jdbc.impl;

import com.yongjun.stock.dao.CustomerBalanceDao;
import com.yongjun.stock.service.jdbc.CustomerBalanceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by 姚磊 on 2017/10/26.
 */

@Service
public class CustomerBalanceServiceImpl implements CustomerBalanceService {

  @Resource
  private CustomerBalanceDao customerBalanceDao;

}
