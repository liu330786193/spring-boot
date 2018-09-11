package com.yongjun.stock.service.jdbc.impl;

import com.yongjun.stock.dao.OrderDelayDao;
import com.yongjun.stock.service.jdbc.OrderDelayService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by 姚磊 on 2017/10/26.
 */

@Service
public class OrderDelayServiceImpl implements OrderDelayService {

  @Resource
  private OrderDelayDao orderDelayDao;

}
