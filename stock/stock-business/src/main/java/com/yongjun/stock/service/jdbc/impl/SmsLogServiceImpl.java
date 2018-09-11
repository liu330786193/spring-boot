package com.yongjun.stock.service.jdbc.impl;

import com.yongjun.stock.dao.SmsLogDao;
import com.yongjun.stock.service.jdbc.SmsLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by 姚磊 on 2017/10/26.
 */

@Service
public class SmsLogServiceImpl implements SmsLogService {

  @Resource
  private SmsLogDao smsLogDao;

}
