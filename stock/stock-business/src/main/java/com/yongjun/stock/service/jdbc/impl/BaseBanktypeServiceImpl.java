package com.yongjun.stock.service.jdbc.impl;

import com.yongjun.stock.dao.BaseBanktypeDao;
import com.yongjun.stock.service.jdbc.BaseBanktypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by 姚磊 on 2017/10/26.
 */

@Service
public class BaseBanktypeServiceImpl implements BaseBanktypeService {

  @Resource
  private BaseBanktypeDao baseBanktypeDao;

}
