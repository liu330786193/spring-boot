package com.yongjun.stock.service.jdbc.impl;

import com.yongjun.stock.dao.CustomerCardDao;
import com.yongjun.stock.service.jdbc.CustomerCardService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by 姚磊 on 2017/10/26.
 */

@Service
public class CustomerCardServiceImpl implements CustomerCardService {

  @Resource
  private CustomerCardDao customerCardDao;
}
