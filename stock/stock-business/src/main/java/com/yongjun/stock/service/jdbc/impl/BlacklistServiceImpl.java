package com.yongjun.stock.service.jdbc.impl;

import com.yongjun.stock.dao.BlacklistDao;
import com.yongjun.stock.service.jdbc.BlacklistService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by 姚磊 on 2017/10/26.
 */

@Service
public class BlacklistServiceImpl implements BlacklistService {

  @Resource
  private BlacklistDao blacklistDao;

  @Override
  public boolean isBlack(String phone) {
    return CollectionUtils.isNotEmpty(blacklistDao.selectByPhone(phone));
  }
}
